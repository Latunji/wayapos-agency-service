package com.example.agentservice.service;

import com.example.agentservice.dto.CreateMerchantRequestDTO;
import com.example.agentservice.dto.CreateMerchantResponseDTO;
import com.example.agentservice.dto.MerchantBalanceResponseDTO;
import com.example.agentservice.model.User;
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

import static com.example.agentservice.constants.Constants.AUTH_HEADER;

@Slf4j @Transactional @RequiredArgsConstructor @Service
public class UserService {
    @Value("${user.auth.endpoint}")
    String url;
    @Value("${user.auth.endpoint.userProfile}")
    String userProfileUrl;
    private final WebClient.Builder webClientBuilder;

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

    public MerchantBalanceResponseDTO getMerchantBalance(String url, String authHeader){
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
        }catch (Exception e){
            log.error("error fetching user balance for url {}",url);
            return null;
        }
        log.info("user balance gotten {}",responseDTO.getData().getBalance());
        return responseDTO;
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


}
