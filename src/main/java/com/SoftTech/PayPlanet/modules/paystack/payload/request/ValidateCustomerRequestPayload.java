package com.SoftTech.PayPlanet.modules.paystack.payload.request;

import lombok.Builder;
import lombok.Data;

@Data
public class ValidateCustomerRequestPayload {
    private String email;
    private String firstName;
    private String lastName;
    private String country;
    private String type;
    private String accountNumber;
    private String bvn;
    private String bankCode;
}
