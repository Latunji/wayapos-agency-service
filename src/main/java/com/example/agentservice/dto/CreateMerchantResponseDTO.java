package com.example.agentservice.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class CreateMerchantResponseDTO {
    private String data;
    private String message;
    private boolean status;
    private Long timeStamp;
}
