package com.SoftTech.PayPlanet.web;

import com.SoftTech.PayPlanet.constants.ResponseCode;
import com.SoftTech.PayPlanet.dto.ErrorResponse;
import com.google.gson.Gson;
import kong.unirest.GetRequest;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class WebService {
    private static final Gson gson = new Gson();

    private static String getForObject(String url, Map<String, Object> params, Map<String, String> headers){
        ErrorResponse errorResponse = ErrorResponse.getInstance();

        try{
            GetRequest getRequest = Unirest.get(url);
            if (params != null) getRequest.queryString(params);
            if(headers != null) getRequest.headers(headers);
            return getRequest.asString().getBody();
        }catch (UnirestException exception){
            log.error("Web service error: {}", exception.getMessage());
            errorResponse.setResponseMessage(ResponseCode.THIRD_PARTY_FAILURE);
            errorResponse.setResponseMessage(exception.getMessage());
            return gson.toJson(errorResponse);
        }
    }

    
}
