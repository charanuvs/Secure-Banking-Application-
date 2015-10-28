package edu.asu.securebanking.dao;

import edu.asu.securebanking.beans.Transaction;
import java.util.List;

public interface TransactionDAO {

    /**
     * @param Transaction
     * @return added
     */
    public void addTransaction(Transaction transaction);
    
    public List<Transaction> getPendingCriticalTransactions();
    
    public List<Transaction> getPendingNonCriticalTransactions();
    
    public Transaction getTransaction(String transactionID);
    
    public void updateTransaction(Transaction t);
    
    public void deleteTransaction(Transaction t);

}
