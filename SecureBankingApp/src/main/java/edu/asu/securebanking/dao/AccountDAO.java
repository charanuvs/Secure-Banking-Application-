package edu.asu.securebanking.dao;

import edu.asu.securebanking.beans.Account;

import java.util.List;

/**
 * Created by Vikranth on 10/20/2015.
 * Updated by Rishabh on 10/25/2015.
 */
public interface AccountDAO {

    /**
     * Get accounts for a User
     *
     * @param username
     * @return accounts
     */
    public List<Account> getAccounts(String username);

    /**
     * Get account for an Account Number
     *
     * @param accountNum
     * @return account
     */
    public Account getAccount(Integer accountNum);

    /**
     * Add new account to the system
     *
     * @param account
     */
    public void addAccount(Account account);
    
    public void updateAccount(Account account);
}
