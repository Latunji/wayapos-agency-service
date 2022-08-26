package com.example.agentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KycResponseDto {

    private String data;
    private String message;
    private boolean status;
    private Date timeStamp;
}
