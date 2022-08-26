package com.example.agentservice.service;

import com.example.agentservice.dto.*;
import com.example.agentservice.util.Response;

import java.io.IOException;

public interface MerchantService {
    CreateMerchantResponseDTO registerMerchant(MerchantDto merchantDto) throws Exception;
    Response updateMerchant(String authHeader, MerchantUpdateDto merchantDto) throws IOException;
    Response viewMerchantById(String authHeader, Long merchantId);

    Response searchMerchant(String authHeader, SearchDto searchDto);

    Response viewMerchantByUserId(String authHeader, String userId);

    Response viewMerchantByMerchantId(String authHeader, String merchantId);
    Response viewAllMerchants(String authHeader, ViewDto viewDto);
    Response viewAllMerchantsByUserId(String authHeader, ViewDto viewDto);
    Response deleteMerchant(String authHeader, Long merchantId);
    Response getUnAssignedTerminals(String authHeader);
    Response getAllTerminalsByMerchant(String authHeader,ViewDto viewDto);
    Response activateMerchant(String authHeader,String merchantId);
    Response deactivateMerchant(String authHeader,String merchantId);


    Response getAllMerchants(String authHeader);

    Response getByAdminType(String authHeader, boolean isAdmin);

    Response getMerchantBalance(String authHeader, String request);

    Response updateMerchantUserID(UpdateMerchantIDRequest request);
}
