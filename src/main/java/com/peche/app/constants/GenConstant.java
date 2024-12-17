package com.peche.app.constants;

public class GenConstant {
    private GenConstant() {
    }

    /*
     * CONSTANTS FOR THE NAME COLLECTIONS
     */
    public static final String COLLECTION_PLAZA_VEA = "PLAZAVEA";
    public static final String COLLECTION_TOTTUS = "TOTTUSS";
    public static final String COLLECTION_METRO = "METRO";

    public static final String COLLECTION_ELASTIC_PLAZA_VEA = "elastic_plazavea";
    public static final String COLLECTION_ELASTIC_TOTTUS = "elastic_tottuss";
    public static final String COLLECTION_ELASTIC_METRO = "elastic_metro";

    /**
     * CONSTANTS FOR THE API
     */
    public static final String VERSION_API = "/v1/api";
    public static final String VERSION_API_PRODUCTS = VERSION_API + "/products";
    public static final String ALL_PRODUCTS = "/all/{supermarket}";
    public static final String PRODUCT_BY_CATEGORY = "/category/{category}";
    public static final String ALL_PRODUCTS_SUPERMARKET = "/supermarket/";
    public static final String CATEGORIES_BY_SUPERMARKET = "/categories";
    public static final String BRAND_BY_CATEGORY = "/brand/{supermarket}/{category}";
    public static final String SEARCH_PRODUCT_BY_BRAND= "/brand-category/{brand}/{category}";

    public static final String API_ELASTIC_SEARCH = VERSION_API + "/elastic-data";
    public static final String API_URI_SYNC = "/sync";
    public static final String SEARCH_PRODUCT_VEA = "/plazavea";
    public static final String SEARCH_PRODUCT_TOTTUS = "/tottus";
    public static final String SEARCH_PRODUCT_METRO = "/metro";

    /*
     * CONSTANTS FOR THE EXCEPTIONS
     */
    public static final String EXCEPTION_ILLEGAL_BRAND = "brand is number or empty";
    public static final String EXCEPTION_ILLEGAL_NAME_CATEGORY = "category or supermarket is number or empty";
    public static final String EXCEPTION_ILLEGAL_NAME_BRAND = "category or brand is number or empty";
    public static final String EXCEPTION_ILLEGAL_PARAMETERS = "The product name or brand must not be empty or contain white spaces.";
    public static final String EXCEPTION_ILLEGAL_ARG = "name supermarket is number or empty";
    public static final String EXCEPTION_ILLEGAL_CATEGORY = "category is number or empty";
    public static final String EXCEPTION_ILLEGAL_SUPERMARKET= "The supermarket entered does not exist. You can only enter [TOTTUS, METRO AND PLAZAVEA]";



    /**
     * CONSTANTS FOR THE LOGGER
     */
    public static final String LOGGER_APP_INIT = "[Controller] - << Execution initialized >> {}";
    public static final String LOGGER_APP_INIT_NAME = "[Controller] - << Execution initialized >> --> : {}";
    public static final String LOGGER_APP_COMPLETED = "[Controller] - << Execution completed >> {}";
    public static final String LOGGER_APP_COMPLETED_NAME = "[Controller] - << Execution completed >> --> : %1$s";
    public static final String LOGGER_APP_FAIL = "[Controller] - << Execution failed >> {}";
    public static final String LOGGER_APP_FAIL_NAME = "[Controller] - << Execution failed >> --> : %1$s";
    public static final String LOGGER_APP_FAIL_NAME_ERROR = "[Controller] - << Execution failed >> --> : {} --> Message: {}";
    public static final String LOGGER_SRV_INIT = "[Services] - << Execution initialized >>";
    public static final String LOGGER_SRV_INIT_NAME = "[Services] - << Execution initialized >> --> : %1$s";
    public static final String LOGGER_SRV_COMPLETED = "[Services] - << Execution completed >>";
    public static final String LOGGER_SRV_COMPLETED_NAME = "[Services] - << Execution completed >> --> : %1$s";
    public static final String LOGGER_SRV_FAIL = "[Services] - << Execution failed >>";
    public static final String LOGGER_SRV_FAIL_NAME = "[Services] - << Execution failed >> --> : {} --> Message: {}";
    public static final String LOGGER_SRV_FAIL_NAME_ERROR = "[Services] - << Execution failed >> --> : %1$s --> Message: %2$s";

}
