package com.example.agentservice.service;

import com.example.agentservice.dto.AssignDto;
import com.example.agentservice.dto.MerchantDto;
import com.example.agentservice.dto.ViewDto;
import com.example.agentservice.util.Response;

public interface MerchantService {
    Response registerMerchant(String authHeader, MerchantDto merchantDto);
    Response updateMerchant(String authHeader, MerchantDto merchantDto);
    Response viewMerchantById(String authHeader, Long merchantId);
    Response viewAllMerchants(String authHeader, ViewDto viewDto);
    Response viewAllMerchantsByUserId(String authHeader, ViewDto viewDto);
    Response deleteMerchant(String authHeader, Long merchantId);
    Response getUnAssignedTerminals(String authHeader);
    Response assignTerminalsToMerchantsr(String authHeader, AssignDto assignDto);
    Response unassignTerminals(String authHeader,AssignDto assignDto);
    Response getAllTerminalsByMerchant(String authHeader,ViewDto viewDto);
    Response activateMerchant(String authHeader,String merchantId);
    Response deactivateMerchant(String authHeader,String merchantId);



}
