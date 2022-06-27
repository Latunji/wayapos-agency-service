package com.example.agentservice.dto.priviledgesDTO;

import java.util.ArrayList;

@lombok.Data
public class Data {
    private ArrayList<Privilege> privileges;
    private int totalItems;
    private int totalPages;
    private int currentPage;
}
