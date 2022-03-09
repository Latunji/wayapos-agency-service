package com.example.agentservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AssignDto {
    @NotNull
    @NotBlank
    @NotEmpty
    private Long merchantID;
    @NotNull
    @NotBlank
    @NotEmpty
    private List<Long> terminalIds;
}
