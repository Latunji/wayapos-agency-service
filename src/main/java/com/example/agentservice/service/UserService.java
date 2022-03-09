package com.example.agentservice.service;

import com.example.agentservice.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

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

}
