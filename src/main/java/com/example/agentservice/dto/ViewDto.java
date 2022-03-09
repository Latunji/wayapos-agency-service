package com.example.agentservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
public class ViewDto {
    private int pageSize;
    private int pageNo;
    private HashMap<String,Object> params;
    private Date from;
    private Date to;
}
