package edu.asu.securebanking.controller;

import edu.asu.securebanking.beans.Account;
import edu.asu.securebanking.beans.AppUser;
import edu.asu.securebanking.beans.PageViewBean;
import edu.asu.securebanking.beans.Transaction;
import edu.asu.securebanking.constants.AppConstants;
import edu.asu.securebanking.exceptions.AppBusinessException;
import edu.asu.securebanking.service.AccountService;
import edu.asu.securebanking.service.LoginService;
import edu.asu.securebanking.service.UserService;
import edu.asu.securebanking.util.LoginFormValidator;
import edu.asu.securebanking.view.AccountPdfView;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Zahed
 *         <p>
 *         Account Summary controller
 */

@Controller
public class AccountSummaryController {

    private static final Logger LOGGER = Logger.getLogger(AccountSummaryController.class);

    @Autowired
    @Qualifier("loginFormValidator")
    private LoginFormValidator loginFormValidator;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    @Qualifier("accountExcelView")
    private View accountExcelView;
    
    @Resource(name = "accountPdfView")
    private View accountPdfView;

    /**
     * @param session
     * @param model
     * @param accountId
     * @return
     */
    @RequestMapping(value = {"/user/statements/{accountId}",
            "/merch/statements/{accountId}"}, method = RequestMethod.GET)
    public ModelAndView viewStatement(HttpSession session,
                                      Model model,
                                      @PathVariable("accountId") Integer accountId,
                                      @RequestParam(value = "view",
                                              required = false) String view) {

        PageViewBean page = new PageViewBean();
        model.addAttribute("page", page);

        try {

            AppUser user = (AppUser) session.getAttribute(AppConstants.LOGGEDIN_USER);
            boolean isAuth = accountService.isAuthorizedAccountHolder(user.getUserId(),
                    accountId);

            if (!isAuth)
                throw new AppBusinessException("Invalid account number");

            // get the account summary and transactions
            Account account = accountService.getAccount(accountId);

            // get the transactions
            List<Transaction> transactions = accountService.getTransactions(accountId);

            model.addAttribute("appUser", user);
            model.addAttribute("transactions", transactions);
            model.addAttribute("account", account);
            model.addAttribute("user", user.getUserId());

            if (StringUtils.hasText(view) &&
                    view.equalsIgnoreCase("excel")) {
                return new ModelAndView(accountPdfView);
            }

            if (user.getUserType().equals(AppConstants.ROLE_MERCHANT)) {
                return new ModelAndView("merch/view-statements");
            }
        } catch (AppBusinessException e) {
            page.setValid(false);
            page.setMessage(e.getMessage());
            LOGGER.error(e);
            return new ModelAndView("message");
        } catch (Exception e) {
            page.setValid(false);
            page.setMessage(AppConstants.DEFAULT_ERROR_MSG);
            LOGGER.error(e);
            return new ModelAndView("message");
        }

        return new ModelAndView("user/view-statements");
    }

}
