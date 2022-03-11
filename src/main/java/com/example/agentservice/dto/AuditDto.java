package com.example.agentservice.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class AuditDto {
    @NotEmpty
    @NotBlank
    private String activity;
    @NotEmpty
    @NotBlank
    private String userID;
}
