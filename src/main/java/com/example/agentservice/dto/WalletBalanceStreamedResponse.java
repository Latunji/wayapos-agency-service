package com.example.agentservice.dto;

import lombok.Data;

@Data
public class WalletBalanceStreamedResponse {
    private float id;
    private String branchId;
    private String accountNo;
    private String accountName;
    private String productCode;
    private float balance;
    private String currencyCode;
    private boolean accountDefault;
}
