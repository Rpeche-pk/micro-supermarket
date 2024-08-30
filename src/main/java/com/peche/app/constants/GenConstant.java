package com.peche.app.constants;

public class GenConstant {
    private GenConstant() {
    }

    /**
     * CONSTANTS FOR THE API
     */
    public static final String VERSION_API = "/v1/api";
    public static final String VERSION_API_PRODUCTS = VERSION_API + "/products";
    public static final String ALL_PRODUCTS = "/all";
    public static final String PRODUCT_BY_BRAND = "/{brand}";
    public static final String PRODUCT_BY_CATEGORY = "/category/{category}";
    public static final String PRODUCT_BY_SUPERMARKET = "/supermarket/{supermarket}";

    /*
     * CONSTANTS FOR THE EXCEPTIONS
     */
    public static final String EXCEPTION_ILLEGAL_BRAND = "brand is number or empty";
    public static final String EXCEPTION_ILLEGAL_ARG = "name supermarket is number or empty";
    public static final String EXCEPTION_ILLEGAL_CATEGORY = "category is number or empty";
    public static final String EXCEPTION_ILLEGAL_SUPERMARKET= "The supermarket entered does not exist. You can only enter [TOTTUS, METRO AND PLAZA VEA]";



    /**
     * CONSTANTS FOR THE LOGGER
     */
    public static final String LOGGER_APP_INIT = "[Controller] - << Execution initialized >>";
    public static final String LOGGER_APP_INIT_NAME = "[Controller] - << Execution initialized >> --> : %1$s";
    public static final String LOGGER_APP_COMPLETED = "[Controller] - << Execution completed >>";
    public static final String LOGGER_APP_COMPLETED_NAME = "[Controller] - << Execution completed >> --> : %1$s";
    public static final String LOGGER_APP_FAIL = "[Controller] - << Execution failed >>";
    public static final String LOGGER_APP_FAIL_NAME = "[Controller] - << Execution failed >> --> : %1$s";
    public static final String LOGGER_APP_FAIL_NAME_ERROR = "[Controller] - << Execution failed >> --> : %1$s --> Message: %2$s";
    public static final String LOGGER_SRV_INIT = "[Services] - << Execution initialized >>";
    public static final String LOGGER_SRV_INIT_NAME = "[Services] - << Execution initialized >> --> : %1$s";
    public static final String LOGGER_SRV_COMPLETED = "[Services] - << Execution completed >>";
    public static final String LOGGER_SRV_COMPLETED_NAME = "[Services] - << Execution completed >> --> : %1$s";
    public static final String LOGGER_SRV_FAIL = "[Services] - << Execution failed >>";
    public static final String LOGGER_SRV_FAIL_NAME = "[Services] - << Execution failed >> --> : %1$s";
    public static final String LOGGER_SRV_FAIL_NAME_ERROR = "[Services] - << Execution failed >> --> : %1$s --> Message: %2$s";
    public static final String LOGGER_REP_INIT = "[Repository] - << Execution initialized >>";
    public static final String LOGGER_REP_INIT_NAME = "[Repository] - << Execution initialized >> --> : %1$s";
    public static final String LOGGER_REP_COMP = "[Repository] - << Execution completed >>";
    public static final String LOGGER_REP_COMPLETED_NAME = "[Repository] - << Execution completed >> --> : %1$s";
    public static final String LOGGER_REP_FAIL = "[Repository] - << Execution failed >>";
    public static final String LOGGER_REP_FAIL_NAME = "[Repository] - << Execution failed >> --> : %1$s";
    public static final String LOGGER_REP_FAIL_NAME_ERROR = "[Repository] - << Execution failed >> --> : %1$s --> Message: %2$s";
}
