package com.SoftTech.PayPlanet.modules.paystack.ORM;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CreateCustomerResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean status;
    private String message;
    private CreateCustomerData data;
}
