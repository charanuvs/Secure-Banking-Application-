package edu.asu.securebanking.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Vikranth on 10/20/2015.
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @Column(name = "ACC_NO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountNum;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private AppUser user;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "TYPE", columnDefinition = "enum('SAVINGS', 'CHECKIN', 'MERCHANT')")
    private String accountType;

    @Column(name = "OPEN_DATE")
    @Temporal(TemporalType.DATE)
    private Date openingDate;

    @Column(name = "CLOSING_DATE")
    @Temporal(TemporalType.DATE)
    private Date closingDate;

    @Transient
    private String balanceString;

    public Integer getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Integer accountNum) {
        this.accountNum = accountNum;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getBalanceString() {
        return balanceString;
    }

    public void setBalanceString(String balanceString) {
        this.balanceString = balanceString;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNum=" + accountNum +
                ", user=" + user +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                ", openingDate=" + openingDate +
                ", closingDate=" + closingDate +
                '}';

    }
}
