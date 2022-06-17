package com.example.agentservice.service;

import com.example.agentservice.dto.AuditDto;
import com.example.agentservice.model.User;
import com.example.agentservice.util.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.example.agentservice.constants.Constants.AUTH_HEADER;

@Slf4j @Transactional @RequiredArgsConstructor @Service
public class LogService {
    @Value("${log.service.url}")
    String logServiceUrl;
    private final WebClient.Builder webClientBuilder;

    public void sendLogs(AuditDto logs){
        log.info("sending logs to logservice url {}",logServiceUrl);
        Response response;
        try {

            response = webClientBuilder
                    .build()
                    .post()
                    .uri(logServiceUrl)
                    .body(Mono.just(logs),AuditDto.class)
                    .retrieve()
                    .bodyToMono(Response.class)
                    .block();
            log.info("logs sent ");
            log.info("log response is {} ",response);
        }catch (Exception e){
            log.error("Error occured sending logs {}");
        }

    }

}
