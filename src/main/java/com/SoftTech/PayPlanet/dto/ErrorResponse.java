package com.SoftTech.PayPlanet.dto;

import lombok.Data;

@Data
public class ErrorResponse implements ServerResponse{
    private String responseCode;
    private String responseMessage;
    private ErrorResponse(){}
    public static ErrorResponse getInstance(){
        return new ErrorResponse();
    }
}
