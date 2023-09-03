package com.SoftTech.PayPlanet.dto;

public class ErrorResponse implements ServerResponse{
    private String responseCode;
    private String responseMessage;
    private ErrorResponse(){}
    public static ErrorResponse getInstance(){
        return new ErrorResponse();
    }
}
