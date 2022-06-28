package com.example.agentservice.dto.priviledgesDTO;

import lombok.Data;

@Data
public class Root {
    private Headers headers;
    private Body body;
    private String statusCode;
    private int statusCodeValue;
}
