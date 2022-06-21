package com.example.agentservice.dto;


@lombok.Data
public class MerchantBalanceResponseDTO {
    private String timeStamp;
    private boolean status;
    private String message;
    private Data data;

}

