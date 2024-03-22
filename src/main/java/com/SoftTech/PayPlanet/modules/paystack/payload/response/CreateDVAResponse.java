package com.SoftTech.PayPlanet.modules.paystack.payload.response;

import com.SoftTech.PayPlanet.modules.paystack.orm.CreateDVAData;
import lombok.Data;

@Data
public class CreateDVAResponse {
    private boolean status;
    private String message;
    CreateDVAData data;
}
