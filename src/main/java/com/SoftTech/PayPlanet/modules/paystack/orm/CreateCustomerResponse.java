package com.SoftTech.PayPlanet.modules.paystack.orm;

import lombok.Data;

@Data
public class CreateCustomerResponse {
    private boolean status;
    private String message;
    CreateCustomerData data;
}
