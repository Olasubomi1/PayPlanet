package com.SoftTech.PayPlanet.web;

import com.SoftTech.PayPlanet.constants.ResponseCode;
import com.SoftTech.PayPlanet.dto.ErrorResponse;
import com.google.gson.Gson;
import kong.unirest.GetRequest;
import kong.unirest.RequestBodyEntity;
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

    public static String postForObject(String url, String body, Map<String, Object> params, Map<String, String> headers) {
        PaystackWebResponse paystackWebResponse = new PaystackWebResponse();
        paystackWebResponse.setConnectionError(false);

        try{
            RequestBodyEntity postRequest = Unirest.post(url).body(body);
            if(params != null)
                postRequest.queryString(params);
            if(headers != null)
                postRequest.headers(headers);


            String webDataString = postRequest.asString().getBody();
            paystackWebResponse.setWebDataString(webDataString);

            return webDataString;
        }
        catch (UnirestException exception) {
            log.error("Server connection or client error: {}", exception.getMessage());
            paystackWebResponse.setMessage(exception.getMessage());
            paystackWebResponse.setStatus(false);
            paystackWebResponse.setConnectionError(true);
            return gson.toJson(paystackWebResponse);
        }
    }

    public static String postForObject(String url, String body, Map<String, Object> params,Map<String, Object> pathVariable, Map<String, String> headers) {
        PaystackWebResponse paystackWebResponse = new PaystackWebResponse();
        paystackWebResponse.setConnectionError(false);

        try{
            RequestBodyEntity postRequest = Unirest.post(url).body(body);
            if(params != null)
                postRequest.queryString(params);
            if(headers != null)
                postRequest.headers(headers);
            if(pathVariable != null)
                postRequest.routeParam(pathVariable);


            String webDataString = postRequest.asString().getBody();
            paystackWebResponse.setWebDataString(webDataString);

            return webDataString;
        }
        catch (UnirestException exception) {
            log.error("Server connection or client error: {}", exception.getMessage());
            paystackWebResponse.setMessage(exception.getMessage());
            paystackWebResponse.setStatus(false);
            paystackWebResponse.setConnectionError(true);
            return gson.toJson(paystackWebResponse);
        }
    }
}
