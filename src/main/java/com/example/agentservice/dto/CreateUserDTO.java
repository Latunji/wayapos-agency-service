package com.example.agentservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserDTO {
    private String fullName;
    private String email;
    private List<String> roles;
}
