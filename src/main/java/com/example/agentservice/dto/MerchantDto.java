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
    private boolean admin;
    private String businessType;
    private String city;
    private String dateOfBirth;
    private String email;
    private String firstName;
    private String gender;
    private String officeAddress;
    private String orgEmail;
    private String orgName;
    private String orgPhone;
    private String orgType;
    private String password;
    private String phoneNumber;
    private String referenceCode;
    private String state;
    private String surname;
    private String acquiringInstitutionCode;
    private String countryCode;
    private String currencyCode;
    private String merchantCategoryCode;

}
