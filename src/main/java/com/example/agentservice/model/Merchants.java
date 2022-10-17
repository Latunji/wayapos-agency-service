package com.example.agentservice.model;

import com.example.agentservice.dto.SettlementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Builder
@Entity
@Data
@Table(name = "merchants")
@AllArgsConstructor
@NoArgsConstructor
public class Merchants implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "merchant_id",unique = true)
    private String merchantId;
    private String firstname;
    private String surname;
    private String email;
    private String dob;
    private String gender;
    private String phoneNumber;
    private String state;
    @Column(length = 4)
    private String merchantCategoryCode;
    @Column(length = 40)
    private String merchantNameAndLocation;
    @Column(length = 4)
    private String countryCode;
    private String city;
    @Column(length = 4)
    private String currencyCode;
    private Date createdAt;
    private Date modifiedAt;
    @Column(length = 4)
    private String acquiringInstitutionID;
    private short deleted;
    private Boolean active;
    private String userId;//this is the corporate user that owns the merchants
    private Boolean admin;
    private String referenceCode;
    private String orgType;
    private String orgName;
    private String officeAddress;
    private String businessType;
    private String orgPhone;
    private String settlementBankAccount;
    private String settlementBankCode;
    private SettlementType settlementType;

}