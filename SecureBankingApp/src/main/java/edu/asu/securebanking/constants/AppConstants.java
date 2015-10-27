package edu.asu.securebanking.constants;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vikranth
 */
public final class AppConstants {

    private AppConstants() {
    }

    /**
     * Default DOC title
     */
    public static final String DOC_TITLE = "XYZ Bank";

    public static final String ROLE_NORMAL = "ROLE_NORMAL";

    public static final String ROLE_MERCHANT = "ROLE_MERCHANT";

    public static final String ROLE_EMP = "ROLE_EMPLOYEE";

    public static final String ROLE_MANAGER = "ROLE_MANAGER";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String GENDER_MALE = "MALE";

    public static final String GENDER_FEMALE = "FEMALE";

    public static final String GENDER_OTHER = "OTHER";

    public static final String USER_ACTIVE = "ACTIVE";

    public static final String USER_LOCKED = "LOCKED";

    public static final Map<String, String> GENDERS =
            new HashMap<String, String>();

    public static final Map<String, String> ROLE_URL_MAP =
            new HashMap<String, String>();

    public static final Map<String, String> EXTERNAL_USERS_ROLES =
            new HashMap<String, String>();

    public static final Map<String, String> INTERNAL_USERS_ROLES =
            new HashMap<String, String>();

    public static final Map<String, String> USER_STATUS = new HashMap<String, String>();

    public static final int MIN_AGE = 15;

    public static final int MAX_EMAIL_LEN = 50;

    public static final String BANK_FROM_ADDR = "customerService@secureonlinebanking";

    public static final String LOGGEDIN_USER = "loggedInUser";

    public static final String DEFAULT_ERROR_MSG =
            "Something is wrong. Please try again";

    public static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd");

    public static final String ACCOUNT_SAVINGS = "SAVINGS";

    public static final String ACCOUNT_CHECKIN = "CHECKIN";

    public static final String ACCOUNT_MERCHANT = "MERCHANT";

    public static final Map<String, String> ACCOUNT_TYPES =
            new HashMap<String, String>();

    public static final Map<String, String> ACCOUNT_TYPES_NORMAL =
            new HashMap<String, String>();

    public static final Map<String, String> ACCOUNT_TYPES_MERCHANT =
            new HashMap<String, String>();

    public static final double MIN_BALANCE = 0;

    public static final double MAX_BALANCE = 1000000;


    public static final Map<String, String> TRANSACTION_TYPES =
            new HashMap<String, String>();


    static {
        GENDERS.put(GENDER_MALE, "Male");
        GENDERS.put(GENDER_FEMALE, "Female");
        GENDERS.put(GENDER_OTHER, "Other");

        ROLE_URL_MAP.put(ROLE_NORMAL, "user");
        ROLE_URL_MAP.put(ROLE_EMP, "emp");
        ROLE_URL_MAP.put(ROLE_MANAGER, "manage");
        ROLE_URL_MAP.put(ROLE_ADMIN, "admin");
        ROLE_URL_MAP.put(ROLE_MERCHANT, "merch");

        // External users role map
        EXTERNAL_USERS_ROLES.put(ROLE_NORMAL, "Individual");
        EXTERNAL_USERS_ROLES.put(ROLE_MERCHANT, "Merchant/Org");

        // Internal users role map
        INTERNAL_USERS_ROLES.put(ROLE_EMP, "Employee");
        INTERNAL_USERS_ROLES.put(ROLE_MANAGER, "System Manager");
        INTERNAL_USERS_ROLES.put(ROLE_ADMIN, "System Administrator");

        // User status
        USER_STATUS.put(USER_ACTIVE, "Active");
        USER_STATUS.put(USER_LOCKED, "Locked");

        // Account types
        ACCOUNT_TYPES.put(ACCOUNT_SAVINGS, "Savings");
        ACCOUNT_TYPES.put(ACCOUNT_CHECKIN, "Checkin");
        ACCOUNT_TYPES.put(ACCOUNT_MERCHANT, "Merchant");

        // Account types normal
        ACCOUNT_TYPES_NORMAL.put(ACCOUNT_SAVINGS, "Savings");
        ACCOUNT_TYPES_NORMAL.put(ACCOUNT_CHECKIN, "Checkin");

        // Account type merchant
        ACCOUNT_TYPES_MERCHANT.put(ACCOUNT_CHECKIN, "Merchant");
        
        TRANSACTION_TYPES.put("TRANSFER", "Transfer");
        TRANSACTION_TYPES.put("PAYMENT", "Payment");

    }
}
