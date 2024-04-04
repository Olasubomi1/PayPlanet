package com.SoftTech.PayPlanet.modules.paystack.service;

import com.SoftTech.PayPlanet.dto.ServerResponse;
import com.SoftTech.PayPlanet.modules.paystack.payload.request.ValidateCustomerRequestPayload;
import com.SoftTech.PayPlanet.modules.paystack.payload.response.CreateCustomerResponse;
import com.SoftTech.PayPlanet.modules.paystack.payload.response.ValidateCustomerResponsePayload;

import java.util.Map;

public interface CustomerService {
    CreateCustomerResponse createPaystackCustomer(String email, String firstName, String lastName, String phone);
    ServerResponse handlePaystackCustomerCallback(Map<String, String> params);
    ServerResponse handleValidateCustomer(ValidateCustomerRequestPayload responsePayload);
}
