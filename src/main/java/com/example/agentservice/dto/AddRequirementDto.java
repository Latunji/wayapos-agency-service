package com.example.agentservice.dto;

import lombok.Data;

@Data
public class AddRequirementDto {

    private Long customerId;
    private String reqItem;
    private String reqValue;
    private String status;
    private String tierName;
}
