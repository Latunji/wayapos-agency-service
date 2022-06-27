package com.example.agentservice.dto.roleDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Root{
    private String createdAt;
    private String updatedAt;
    private String createdBy;
    private String modifiedBy;
    private int id;
    private String name;
    private String description;
    @JsonProperty("default")
    private Boolean mydefault;
}
