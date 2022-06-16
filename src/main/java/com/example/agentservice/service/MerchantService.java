package com.example.agentservice.service;

import com.example.agentservice.dto.AssignDto;
import com.example.agentservice.dto.CreateMerchantResponseDTO;
import com.example.agentservice.dto.MerchantDto;
import com.example.agentservice.dto.ViewDto;
import com.example.agentservice.util.Response;

public interface MerchantService {
    CreateMerchantResponseDTO registerMerchant(MerchantDto merchantDto) throws Exception;
    Response updateMerchant(String authHeader, MerchantDto merchantDto);
    Response viewMerchantById(String authHeader, Long merchantId);
    Response viewAllMerchants(String authHeader, ViewDto viewDto);
    Response viewAllMerchantsByUserId(String authHeader, ViewDto viewDto);
    Response deleteMerchant(String authHeader, Long merchantId);
    Response getUnAssignedTerminals(String authHeader);
    Response getAllTerminalsByMerchant(String authHeader,ViewDto viewDto);
    Response activateMerchant(String authHeader,String merchantId);
    Response deactivateMerchant(String authHeader,String merchantId);


    Response getAllMerchants(String authHeader);

    Response getByAdminType(String authHeader, boolean isAdmin);
}
