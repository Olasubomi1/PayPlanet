package com.SoftTech.PayPlanet.modules.paystack.ORM;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCustomerData {
    private int id;
    private String email;
    private int integration;
    private String domain;
    private String customerCode;
    private boolean identified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
