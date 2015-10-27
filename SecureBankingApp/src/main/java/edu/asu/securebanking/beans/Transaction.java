package edu.asu.securebanking.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vikranth on 10/21/2015.
 */
@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @Column(name = "T_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "TO_ACCOUNT")
    private Account toAccount;

    @ManyToOne
    @JoinColumn(name = "FROM_ACCOUNT")
    private Account fromAccount;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "T_TYPE", columnDefinition = "enum('TRANSFER', 'PAYMENT')")
    private String transactionType;

    @Column(name = "T_STATUS", columnDefinition = "enum('PENDING', 'COMPLETE')")
    private String status;

    @Column(name = "T_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "AUTHORISED_EMP")
    private AppUser authEmployee;

    @Transient
    private String toAccountNumber;

    @Transient
    private String fromAccountNumber;

    @Transient
    private String transactionTypeString;

    @Transient
    private String amountString;

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getTransactionTypeString() {
        return transactionTypeString;
    }

    public void setTransactionTypeString(String transactionTypeString) {
        this.transactionTypeString = transactionTypeString;
    }

    public String getAmountString() {
        return amountString;
    }

    public void setAmountString(String amountString) {
        this.amountString = amountString;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AppUser getAuthEmployee() {
        return authEmployee;
    }

    public void setAuthEmployee(AppUser authEmployee) {
        this.authEmployee = authEmployee;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", toAccount=" + toAccount +
                ", fromAccount=" + fromAccount +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", authEmployee=" + authEmployee +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                ", fromAccountNumber='" + fromAccountNumber + '\'' +
                ", transactionTypeString='" + transactionTypeString + '\'' +
                ", amountString='" + amountString + '\'' +
                '}';
    }
}
