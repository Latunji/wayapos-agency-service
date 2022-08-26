package com.example.agentservice.service;

import com.example.agentservice.dto.*;
import com.example.agentservice.dto.priviledgesDTO.Root;
import com.example.agentservice.model.User;
import com.example.agentservice.model.WayaPosUsers;
import com.example.agentservice.repository.WayaPosUsersRepository;
import com.example.agentservice.util.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

import static com.example.agentservice.constants.Constants.*;

@Slf4j @Transactional @RequiredArgsConstructor @Service
public class UserService {
    @Value("${user.auth.endpoint}")
    String url;
    @Value("${user.auth.endpoint.userProfile}")
    String userProfileUrl;
    @Value("${user.auth.endpoint.getPriviledges}")
    String getAllPriviledgesUrl;
    @Value("${user.auth.endpoint.getRoles}")
    String getAllRoles;

    @Value("${kyc.endpoint.createkyc}")
    String createKyc;
    private final WebClient.Builder webClientBuilder;
    private final WayaPosUsersRepository wayaPosUsersRepository;

    public User validateUser(String authHeader){
        log.info("About validating user token with auth endpoint {}",url);
        User user;
        try {

            user = webClientBuilder
                    .build()
                    .post()
                    .uri(url)
                    .header(AUTH_HEADER,authHeader)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();
        }catch (Exception e){
            log.error("Error occured while performing user token validation");
            return null;
        }
        log.info("User gotten {}",user);
        return user;
    }

    public WalletBalanceStreamedResponse getMerchantBalance(String url, String authHeader){
        WalletBalanceStreamedResponse response = new WalletBalanceStreamedResponse();

        log.info("About fetching user balance");
        MerchantBalanceResponseDTO responseDTO;
        try {

            responseDTO = webClientBuilder
                    .build()
                    .get()
                    .uri(url)
                    .header(AUTH_HEADER,authHeader)
                    .retrieve()
                    .bodyToMono(MerchantBalanceResponseDTO.class)
                    .block();

            if (responseDTO==null)
                return null;
            response.setAccountName(responseDTO.getData().getAcct_name());
            response.setAccountNo(responseDTO.getData().getAccountNo());
            response.setBranchId(responseDTO.getData().getBacid());
            response.setCurrencyCode(responseDTO.getData().getAcct_crncy_code());
            response.setBalance(responseDTO.getData().getClr_bal_amt());

        }catch (Exception e){
            log.error("error fetching user balance for url {}",url);
            return null;
        }
        log.info("user balance gotten {}",response.getBalance());
        return response;
    }


    public CreateMerchantResponseDTO createMerchant(String url, CreateMerchantRequestDTO requestDTO) throws JsonProcessingException {
        log.info("About fetching user balance");
        CreateMerchantResponseDTO response;
        ObjectMapper mapper  = new ObjectMapper();
        try {
            response = webClientBuilder
                    .build()
                    .post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .syncBody(requestDTO)
                    .retrieve()
                    .bodyToMono(CreateMerchantResponseDTO.class)
                    .block();
        }catch (Exception e){
            log.error("error creating  merchant for url {}",url);
            return mapper.readValue(((WebClientResponseException.BadRequest) e).getResponseBodyAsString(),CreateMerchantResponseDTO.class) ;
        }
        log.info("merchant created");
        return response;
    }


    public Response getPriviledges(String authorization) {
        User user = validateUser(authorization);

        if (user==null){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        log.info("About fetching priviledges balance");
        Root response;
        try {
            response = webClientBuilder
                    .build()
                    .get()
                    .uri(getAllPriviledgesUrl)
                    .retrieve()
                    .bodyToMono(Root.class)
                    .block();
        }catch (Exception e){
            log.error("error fetching priviledges {}",getAllPriviledgesUrl);
            return new Response(FAILED_CODE,FAILED,"Error fectching priviledges "+getAllPriviledgesUrl+ "Error = "+e.getMessage()) ;
        }
        log.info("merchant created");
        return new Response(SUCCESS_CODE,SUCCESS,response);

    }

    public Response getRoles(String authorization){
        User user = validateUser(authorization);

        if (user==null){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }

        log.info("About fetching user roles");

        com.example.agentservice.dto.roleDTO.Root response;
        try {
            response = webClientBuilder
                    .build()
                    .get()
                    .uri(getAllRoles)
                    .retrieve()
                    .bodyToMono(com.example.agentservice.dto.roleDTO.Root.class)
                    .block();
        }catch (Exception e){
            log.error("error fetching priviledges {}",getAllRoles);
            return new Response(FAILED_CODE,FAILED,"Error fectching priviledges "+getAllRoles+ "Error = "+e.getMessage()) ;
        }
        log.info("merchant created");
        return new Response(SUCCESS_CODE,SUCCESS,response);

    }



    public CreateMerchantResponseDTO createKyc(String authHeader, CreateKycDto request) {
        User user = validateUser(authHeader);
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new CreateMerchantResponseDTO("","User Validation Failed",false, 0L);
        }
        CreateMerchantResponseDTO response;
        log.info("kyc object to be created..."+request);
        try {
            response = webClientBuilder
                    .build()
                    .post()
                    .uri(createKyc)
                    .contentType(MediaType.APPLICATION_JSON)
                    .syncBody(request)
                    .retrieve()
                    .bodyToMono(CreateMerchantResponseDTO.class)
                    .block();
        }catch (Exception e){
            log.error("error creating kyc for user {}",createKyc);
            return new CreateMerchantResponseDTO("",FAILED,false, 0L);
        }
        log.info("kyc created..."+response);
        return response;
    }


    public Response createUser(String authHeader, CreateUserDTO request) {
        User user = validateUser(authHeader);
        if (Objects.isNull(user)){
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Validation Failed");
        }
        if (user.getData().getRoles().stream().anyMatch(s -> s.equals("ROLE_CORP_ADMIN"))){
            WayaPosUsers users1 = wayaPosUsersRepository.findByEmail(request.getEmail()).orElse(null);
            if (users1==null){
                WayaPosUsers users = WayaPosUsers.builder()
                        .fullName(request.getFullName())
                        .email(request.getEmail())
                        .roles(Arrays.toString(request.getRoles().toArray()))
                        .build();

                wayaPosUsersRepository.saveAndFlush(users);

                //todo create wayapay user
                return new Response(SUCCESS_CODE,SUCCESS,"user "+ request.getEmail()+ " created successfully");

            }
            else
                return new Response(FAILED_CODE,FAILED,"user "+ request.getEmail()+ " already exists");


        }
        else {
            log.error("user validation failed");
            return new Response(FAILED_CODE,FAILED,"Permission not valid for user");
        }
    }

}
