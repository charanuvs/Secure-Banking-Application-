package edu.asu.securebanking.controller;


import edu.asu.securebanking.beans.Transaction;
import edu.asu.securebanking.beans.PageViewBean;
import edu.asu.securebanking.beans.PasswordBean;
import edu.asu.securebanking.constants.AppConstants;
import edu.asu.securebanking.dao.TransactionDAO;
import edu.asu.securebanking.exceptions.AppBusinessException;
import edu.asu.securebanking.util.AppUtil;
import edu.asu.securebanking.service.AccountService;
import edu.asu.securebanking.service.EmailService;
import edu.asu.securebanking.service.TransactionService;
import edu.asu.securebanking.beans.Account;
import edu.asu.securebanking.beans.AppUser;
import edu.asu.securebanking.dao.UserDAO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.*;

import edu.asu.securebanking.service.OTPService;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Rishabh
 *         <p>
 *         Login controller
 */

@Controller
public class PaymentController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private TransactionService transactionService;


    @RequestMapping(value = "/user/payment", method = RequestMethod.GET)
    public String payment(@ModelAttribute("transaction") Transaction transaction, final Model model) {
		PageViewBean page = new PageViewBean();
		model.addAttribute("page", page);
		
		model.addAttribute("types", AppConstants.TRANSACTION_TYPES);
		
		return "user/payment";
	}
    
    
    @RequestMapping(value = "/user/payment/validate",
            method = RequestMethod.POST)
    public String addTransaction(@ModelAttribute("transaction") Transaction transaction,
                          Model model,
                          BindingResult result,
                          HttpSession session) throws AppBusinessException {
    	
    	AppUser loggedInUser = (AppUser)
                session.getAttribute(AppConstants.LOGGEDIN_USER);
    	
        // check fields
        if (!isNumeric(transaction.getToAccountNumber()) 
        		|| !isNumeric(transaction.getFromAccountNumber())
        		|| !isNumeric(transaction.getAmountString())
        		|| Double.parseDouble(transaction.getAmountString()) <= 0){
        	session.setAttribute("transaction.err", "Your transaction was not processed. \nAll fields must be positive numbers");
        	return "/user/payment-deny";
        }
    	
    	LOGGER.info(transaction);

        PageViewBean page = new PageViewBean();
        model.addAttribute("page", page);

        Integer toAccountNumberInt = Integer.parseInt(transaction.getToAccountNumber());
        Integer fromAccountNumberInt = Integer.parseInt(transaction.getFromAccountNumber());
	
        Account toAccount = accountService.getAccount(toAccountNumberInt);
        Account fromAccount = accountService.getAccount(fromAccountNumberInt);
        
        String transType = transaction.getTransactionType();
        BigDecimal amount = new BigDecimal(Double.parseDouble(transaction.getAmountString()),MathContext.DECIMAL64);
        
        
        // check if both accounts exist and are distinct
        if (toAccount == null || fromAccount == null || toAccountNumberInt == fromAccountNumberInt) {
        	session.setAttribute("transaction.err", "Your transaction was not processed. \nPlease provide two valid and distinct account numbers.");
        	return "/user/payment-deny";
        	
        }
        // check if user owns the from account
        else if (!fromAccount.getUser().getUserId().equals(((AppUser) session.getAttribute(AppConstants.LOGGEDIN_USER)).getUserId())) {
        	session.setAttribute("transaction.err", "Your transaction was not processed. \nYou must be the owner of the source acount.");
        	return "/user/payment-deny";
        }
        
        // Check if account types are appropriate for payment or transfer
        else if (!(transType.equals("PAYMENT") 
        		&& toAccount.getAccountType().equals("MERCHANT")
        		|| transType.equals("TRANSFER") 
        		&& !(toAccount.getAccountType().equals("MERCHANT")))) {
        	session.setAttribute("transaction.err", "Your transaction was not processed. \nPayment transactions must be to a merchant acount, and transfers must be to user acounts.");
        	return "/user/payment-deny";
        } 
        // check if user has sufficient funds
        else if (fromAccount.getBalance().compareTo(amount) == -1) {
        	session.setAttribute("transaction.err", "Your transaction was not processed. \nReason: Insufficient funds.");
        	return "/user/payment-deny";
        }
        // check for critical transaction
        else if (amount.compareTo(new BigDecimal("10000")) >= 0) {
        	session.setAttribute("transaction.critical.amount", amount);
        	session.setAttribute("transaction.critical.fromAccount", fromAccount);
        	session.setAttribute("transaction.critical.toAccount", toAccount);
        	session.setAttribute("transaction.critical.transactionType", transType);
        	session.setAttribute("transaction.critical.transactionType", transType);
        	
        	
        	
        	session.setAttribute("transaction.critical.postUrl", "/user/payment/confirm-critical");
        	
        	// OTP and send the message
            String otp = otpService.generateOTP();
            session.setAttribute("transaction.critical.otp", otp);
            // send email
            emailService.sendEmail(loggedInUser.getEmail(), "OTP to submit your transaction",
                    "The OTP to submit your transaction: " + otp);
        	
            return "user/submit-critical-transaction-otp";
        }
        // process the transaction
        else {

    		transaction.setToAccount(toAccount);
    		transaction.setFromAccount(fromAccount);
    		
            transaction.setAmount(amount);      
        	transaction.setTransactionType(transType);            
            transaction.setStatus(new String("PENDING"));
            //transaction.setAuthEmployee(userDAO.getUser(loggedInUser.getUserId()));
            
            Date currentDate = new Date();
            
            //Date Format needs to be like yyyy-mm-dd
            transaction.setDate(currentDate);
            
            session.setAttribute("user.payment", transaction);
            
            session.setAttribute("user.payment", transaction);
            LOGGER.info("Transnew: " + transaction);
            transactionService.addTransaction(transaction);

            
            return "/user/payment-confirm";
        }
    }
    
 
    
    @RequestMapping(value = "/user/payment/confirm-critical", method = RequestMethod.POST)
	public String addCriticalTransaction(/*@ModelAttribute("transaction") Transaction transaction,*/
			          @RequestParam("otp") String otp,
	                  Model model,
	                  //BindingResult result,
	                  HttpSession session) throws AppBusinessException {
    	
    	LOGGER.info("OTP: " + otp);
 
	    // validate OTP
	    if (!otp.equals(session.getAttribute("transaction.critical.otp").toString())) {
	    	session.setAttribute("transaction.err", "Your transaction was not processed. \nReason: Incorrect OTP.");
	    	return "/user/payment-deny";
	    }
 

	    Transaction transaction = new Transaction();

		PageViewBean page = new PageViewBean();
		model.addAttribute("page", page);
		model.addAttribute("page", page);

		
		Account toAccount = (Account) session.getAttribute("transaction.critical.toAccount");
		Account fromAccount = (Account) session.getAttribute("transaction.critical.fromAccount");
		
	    String transType = (String) session.getAttribute("transaction.critical.transactionType");
	    BigDecimal amount = (BigDecimal) session.getAttribute("transaction.critical.amount");

	    //paymentValidator.validate(transaction, arg1);
		transaction.setToAccount(toAccount);
		transaction.setFromAccount(fromAccount);
	    transaction.setAmount(amount);
	    
	    
		transaction.setTransactionType(transType);            
	    transaction.setStatus(new String("PENDING"));
	    
	    
	    Date currentDate = new Date();
	    
	    //Date Format needs to be like yyyy-mm-dd
	    transaction.setDate(currentDate);
	    
	    session.setAttribute("user.payment", transaction);
	
	    AppUser loggedInUser = (AppUser)
	            session.getAttribute(AppConstants.LOGGEDIN_USER);
	    
	    //transaction.setAuthEmployee(userDAO.getUser(loggedInUser.getUserId()));
	    
	    session.setAttribute("user.payment", transaction);
	    LOGGER.info("Transnew: " + transaction);
	    transactionService.addTransaction(transaction);

	    
	    return "/user/payment-confirm";
	
	}
    
    public static boolean isNumeric(String str)  
    {  
      try  
      {  
        double d = Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
    
}
