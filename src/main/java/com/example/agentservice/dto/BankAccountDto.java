package com.example.agentservice.dto;

import lombok.Data;

@Data
public class BankAccountDto {

    private String accountNumber;
    private String accountName;
    private String bankCode;
    private String bankName;
    private String userId;
}
