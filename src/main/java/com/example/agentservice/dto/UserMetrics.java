package com.example.agentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserMetrics {
    private Long activeUser;
    private Long inActiveUser;
    private Long activeAdminUser;
    private Long inActiveAdminUser;
    private Long totalUser;
}
