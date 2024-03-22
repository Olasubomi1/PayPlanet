package com.SoftTech.PayPlanet.modules.paystack.orm;

import lombok.Data;

@Data
public class CreateDVAData {
    private int id;
    private String accountName;
    private String accountNumber;
    private boolean assigned;
    private String currency;
    private String metadata;
    private boolean active;
    private String createdAt;
    private String updatedAt;
    Bank bank;
    Assignment assignment;
    CustomerData customer;
}
