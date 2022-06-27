package com.example.agentservice.dto.priviledgesDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Privilege {
    private int id;
    private String name;
    private String description;
    @JsonProperty("default")
    private boolean mydefault;
}
