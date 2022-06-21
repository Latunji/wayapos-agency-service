package com.example.agentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateMerchantResponseDTO {
    private String data;
    private String message;
    private boolean status;
    private Long timeStamp;
}
