package com.SoftTech.PayPlanet.modules.paystack.service;

import com.SoftTech.PayPlanet.modules.paystack.payload.response.CreateDVAResponse;

public interface DVAService {
    CreateDVAResponse createAccount(String customerCode, String preferred_bank, String firstName, String lastName, String phone);
}
