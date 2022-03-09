package com.example.agentservice.dto;

import com.example.agentservice.model.Terminal;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class MerchantDto {
    @NotEmpty
    @NotNull
    @NotBlank
    private String merchantId;
    @NotEmpty
    @NotNull
    @NotBlank
    private String firstname;
    private String surname;
    private String email;
    private String dob;
    private String gender;
    private String address;
    private String phoneNumber;
    private String state;
    private String merchantCategoryCode;
    private String merchantNameAndLocation;
    private String countryCode;
    private String city;
    private String currencyCode;
    private String acquiringInstitutionID;

}
