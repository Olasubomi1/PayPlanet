package com.SoftTech.PayPlanet.modules.paystack.payload.response;

import lombok.Data;

@Data
public class ValidateCustomerResponsePayload {
    private boolean status;
    private String message;
}
