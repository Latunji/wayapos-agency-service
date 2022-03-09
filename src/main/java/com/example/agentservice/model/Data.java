package com.example.agentservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@lombok.Data
public class Data{
    public String id;
    public String email;
    public String phoneNumber;
    public String referenceCode;
    public String firstName;
    public String surname;
    public boolean phoneVerified;
    public boolean emailVerified;
    public boolean pinCreated;
    public ArrayList<String> roles;
    @JsonProperty("permits")
    public ArrayList<String> mypermits;
    public String transactionLimit;
    public boolean corporate;
}
