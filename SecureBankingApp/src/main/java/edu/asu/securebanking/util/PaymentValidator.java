package edu.asu.securebanking.util;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.asu.securebanking.beans.Account;
import edu.asu.securebanking.beans.Transaction;
import edu.asu.securebanking.constants.AppConstants;
import edu.asu.securebanking.exceptions.AppBusinessException;
import edu.asu.securebanking.service.AccountService;

public class PaymentValidator implements Validator{

	private AccountService accountService = new AccountService();
	
    @Override
    public boolean supports(Class<?> clazz) {
        if (clazz == Account.class) {
            return true;
        }
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Transaction transaction = (Transaction) target;
       
        Double amount;
        Integer toAccount;
        Integer fromAccount;

        try {
            amount = Double.parseDouble(transaction.getAmountString());
            
        } catch (Exception e) {
            errors.rejectValue("amount", "payment.amount.format.error", "");
            return;
        }
        
        try {
            toAccount = Integer.parseInt(transaction.getToAccountNumber());
            
        } catch (Exception e) {
            errors.rejectValue("toAccount", "payment.toAccount.error", "");
            return;
        }
        
        try {
            fromAccount = Integer.parseInt(transaction.getFromAccountNumber());
            
        } catch (Exception e) {
            errors.rejectValue("fromAccount", "payment.fromAccount.error", "");
            return;
        }

        if (amount <= 0) {
            errors.rejectValue("amount", "payment.amount.positiveNumber.error","");
        }


        if (!errors.hasErrors())
            transaction.setAmount(new BigDecimal(amount));
        	try {
				transaction.setFromAccount(accountService.getAccount(fromAccount));
				transaction.setToAccount(accountService.getAccount(toAccount));
			} catch (AppBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
            
    }

}
