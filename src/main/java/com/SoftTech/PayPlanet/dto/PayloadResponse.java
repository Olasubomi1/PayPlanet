package com.SoftTech.PayPlanet.dto;

import lombok.Data;

@Data
public class PayloadResponse implements ServerResponse{
    private String responseCode;
    private String responseMessage;
    private Object responseData;
    public static PayloadResponse getInstance(){
        return new PayloadResponse();
    }
}
