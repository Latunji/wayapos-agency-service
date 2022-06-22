package com.example.agentservice.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "spring")
@Component
@Data
@Validated
@Slf4j
public class DBConfig {

    @NotNull
    @Valid
    private Datasource datasource;


    @Data
    public static class Datasource {

        public String url;
        public String username;
        public String password;
    }

    @PostConstruct
    public void init(){

        log.info("db----?dataSourceUrl "+getDatasource().url);
        log.info("db----?dataSourceUsername "+getDatasource().getUsername());
        log.info("db----?dataSourcePassword "+getDatasource().getPassword());

    }
}
