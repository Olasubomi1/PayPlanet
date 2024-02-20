package com.SoftTech.PayPlanet.modules.paystack.service;

import com.SoftTech.PayPlanet.dto.ServerResponse;

import java.util.Map;

public interface CustomerService {
    ServerResponse createPaystackCustomer(String email, String firstName, String lastName, String phone);
    ServerResponse handlePaystackCustomerCallback(Map<String, String> params);
}
