package com.example.agentservice.model;

import com.example.agentservice.constants.PricingStatus;
import lombok.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "pricing")
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String name;
    private String products;
    private BigDecimal pricingRate;
    private BigDecimal cap;
    private BigDecimal discount;
    private boolean merchant;
    private boolean agent;
    private boolean aggregator;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private boolean deleted;
    private PricingStatus status;

}
