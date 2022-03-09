package com.example.agentservice.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Response {
    private int respCode;
    private String respDesc;
    private Object respBody;
}
