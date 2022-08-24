package com.example.agentservice.dto;

import lombok.Data;

@Data
public class CreateKycDto {

    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
}
