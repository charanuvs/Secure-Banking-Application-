package edu.asu.securebanking.beans;

import edu.asu.securebanking.constants.AppConstants;
import edu.asu.securebanking.util.AppUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Vikranth
 *         <p>
 *         Application User
 */
@Entity
@Table(name = "user")
public class AppUser implements Serializable {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GENDER",
            columnDefinition = "enum('MALE', 'FEMALE', 'OTHER')")
    private String gender;

    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "PHONE_NO")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "SSN")
    private String ssn;

    @Column(name = "USER_TYPE",
            columnDefinition = "enum('ROLE_NORMAL', 'ROLE_MERCHANT'," +
                    " 'ROLE_EMPLOYEE'," +
                    "'ROLE_ADMIN', 'ROLE_MANAGER')")
    private String userType;

    @Column(name = "GOV_CERT")
    private String govCert;

    @Column(name = "STATUS", columnDefinition = "enum('LOCKED', 'ACTIVE')")
    private String status;

    @Column(name = "PASSWORD")
    private String password;

    @Transient
    private String tempPassword;

    @Transient
    private String confirmPassword;

    @Transient
    private String dateString;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGovCert() {
        return govCert;
    }

    public void setGovCert(String govCert) {
        this.govCert = govCert;
    }

    public String getDateString() {
        if (null == this.dateString) {
            // Convert the date object to String
            this.dateString = AppUtil.convertDateToString(this.dob);
        }

        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", ssn='" + ssn + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }

    /**
     * Check if a user is active
     *
     * @return isActive
     */
    public boolean isActive() {
        return AppConstants.USER_ACTIVE.equalsIgnoreCase(this.status);
    }
}
