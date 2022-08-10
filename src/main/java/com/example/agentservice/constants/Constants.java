package com.example.agentservice.constants;

public class Constants {
    public static final String PATH  = "api/v1/agent";
    public static final int SUCCESS_CODE  = 00;
    public static final int FAILED_CODE  = 96;
    public static final String FAILED  = "Failed";
    public static final String SUCCESS  = "Successful";
    public static final String AUTH_HEADER  = "authorization";
    public static final String REGISTER_MERCHANT  = "registermerchant";
    public static final String UPDATE_MERCHANT  = "updatemerchant";
    public static final String VIEW_MERCHANT_BY_ID  = "viewmerchantbyid";

    public static final String VIEW_MERCHANT_BY_MERCHANT_ID  = "viewmerchantbymerchantid";
    public static final String VIEW_ALL_MERCHANTS  = "viewallmerchants";
    public static final String VIEW_ALL_MERCHANTS_BY_USERID  = "viewallmerchantsbyuserid";
    public static final String DELETE_MERCHANT  = "deletemerchant";
    public static final String GET_UNASSIGNED_TERMINALS  = "getunassignedterminals";
    public static final String ASSIGN_TERMINAL  = "assignterminal";
    public static final String UNASSIGN_TERMINAL  = "unassignterminal";
    public static final String GET_ALL_TERMINALS_BY_MERCHANT  = "getmerchantterminals";
    public static final String ACTIVATE_MERCHANT = "activatemerchant";
    public static final String DEACTIVATE_MERCHANT = "deactivatemerchant";
}
