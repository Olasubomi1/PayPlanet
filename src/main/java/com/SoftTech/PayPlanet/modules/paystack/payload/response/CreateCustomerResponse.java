package com.SoftTech.PayPlanet.modules.paystack.payload.response;

import com.SoftTech.PayPlanet.modules.paystack.orm.CreateCustomerData;
import lombok.Data;

@Data
public class CreateCustomerResponse {
    private boolean status;
    private String message;
    CreateCustomerData data;
}
