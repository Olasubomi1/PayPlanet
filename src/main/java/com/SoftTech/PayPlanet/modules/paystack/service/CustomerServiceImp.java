package com.SoftTech.PayPlanet.modules.paystack.service;

import com.SoftTech.PayPlanet.dto.ServerResponse;
import com.SoftTech.PayPlanet.modules.paystack.orm.CreateCustomerResponse;
import com.SoftTech.PayPlanet.modules.paystack.payload.CreateCustomerRequestPayload;
import com.SoftTech.PayPlanet.web.WebService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class CustomerServiceImp implements CustomerService{
    @Autowired
    private Environment environment;

    private static final Gson gson = new Gson();

    @Override
    public ServerResponse createPaystackCustomer(String email, String firstName, String lastName, String phone) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer ".concat(Objects.requireNonNull(environment.getProperty("paystack.secretKey"))));
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        String callbackUrl = environment.getProperty("paystack.createCustomerCallbackEndpoint");
        CreateCustomerRequestPayload requestPayload = new CreateCustomerRequestPayload();
        requestPayload.setEmail(email);
        requestPayload.setFirstName(firstName);
        requestPayload.setLastName(lastName);
        requestPayload.setPhone(phone);
        requestPayload.setCallbackUrl(callbackUrl);
        String requestJson = gson.toJson(requestPayload);

        String url = environment.getProperty("paystack.createCustomerUrl");
        String response = WebService.postForObject(url, requestJson, null, headers);
        log.info("response: {}", response);

        CreateCustomerResponse responsePayload = gson.fromJson(response, CreateCustomerResponse.class);
        log.info("mapped response: {}", responsePayload);

//        PaystackWebResponse webResponse = gson.fromJson(response, PaystackWebResponse.class);
//        log.info("paystack Mapped Response: {}", responsePayload);

//        if(webResponse.isConnectionError()){
//            ErrorResponse errorResponse = ErrorResponse.getInstance();
//            errorResponse.setResponseCode(ResponseCode.THIRD_PARTY_FAILURE);
//            errorResponse.setResponseCode(webResponse.getMessage());
//            return errorResponse;
//        }

        return null;
    }

    @Override
    public ServerResponse handlePaystackCustomerCallback(Map<String, String> params) {
        log.info("handle paystack response: {}", params);
        return null;
    }
}
