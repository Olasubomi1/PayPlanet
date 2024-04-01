package com.SoftTech.PayPlanet.modules.paystack.service;

import com.SoftTech.PayPlanet.modules.paystack.model.DedicatedVirtualAccount;
import com.SoftTech.PayPlanet.modules.paystack.orm.Assignment;
import com.SoftTech.PayPlanet.modules.paystack.orm.Bank;
import com.SoftTech.PayPlanet.modules.paystack.orm.CustomerData;
import com.SoftTech.PayPlanet.modules.paystack.payload.request.CreateDVARequestPayload;
import com.SoftTech.PayPlanet.modules.paystack.payload.response.CreateCustomerResponse;
import com.SoftTech.PayPlanet.modules.paystack.payload.response.CreateDVAResponse;
import com.SoftTech.PayPlanet.modules.paystack.repository.CustomerRepository;
import com.SoftTech.PayPlanet.modules.paystack.repository.DVARepository;
import com.SoftTech.PayPlanet.web.WebService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class DVAServiceImp implements DVAService {
    @Autowired
    private Environment environment;

    @Autowired
    private DVARepository repository;

    private static final Gson gson = new Gson();
    @Override
    public CreateDVAResponse createAccount(int customerCode, String preferredBank, String firstName, String lastName, String phone) {
        CreateDVAResponse responsePayload;
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer ".concat(Objects.requireNonNull(environment.getProperty("paystack.secretKey"))));
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        String callBackUrl = environment.getProperty("");
        CreateDVARequestPayload requestPayload = new CreateDVARequestPayload();
        requestPayload.setCustomerCode(customerCode);
        requestPayload.setPreferredBank(preferredBank);
        requestPayload.setFirstName(firstName);
        requestPayload.setLastName(firstName);
        requestPayload.setPhone(phone);
        requestPayload.setCallbackUrl(callBackUrl);
        String requestJson = gson.toJson(requestPayload);

        String url = environment.getProperty("paystack.createCustomerDVAUrl");
        System.out.println("dvacreate url "+ url);
        String response = WebService.postForObject(url, requestJson, null, headers);
        log.info("DVA response: {}", response);

        responsePayload = gson.fromJson(response, CreateDVAResponse.class);
        log.info("DVA mapped response: {}", responsePayload);
        DedicatedVirtualAccount virtualAccount = new DedicatedVirtualAccount();
        virtualAccount.setDVAId(responsePayload.getData().getId());
        virtualAccount.setAccountName(responsePayload.getData().getAccountName());
        virtualAccount.setAccountNumber(responsePayload.getData().getAccountNumber());
        virtualAccount.setAssigned(responsePayload.getData().isAssigned());
        virtualAccount.setCurrency(responsePayload.getData().getCurrency());
        virtualAccount.setMetadata(responsePayload.getData().getMetadata());
        virtualAccount.setActive(responsePayload.getData().isAssigned());
        virtualAccount.setCreatedAt(responsePayload.getData().getCreatedAt());
        virtualAccount.setUpdatedAt(responsePayload.getData().getUpdatedAt());
        virtualAccount.getBank().setBankId(responsePayload.getData().getBank().getId());
        virtualAccount.getBank().setName(responsePayload.getData().getBank().getName());
        virtualAccount.getBank().setSlug(responsePayload.getData().getBank().getSlug());
        virtualAccount.getAssignment().setIntegration(responsePayload.getData().getAssignment().getIntegration());
        virtualAccount.getAssignment().setAccountType(responsePayload.getData().getAssignment().getAccountType());
        virtualAccount.getAssignment().setAssigneeId(responsePayload.getData().getAssignment().getAssigneeId());
        virtualAccount.getAssignment().setAssignedAt(responsePayload.getData().getAssignment().getAssignedAt());
        virtualAccount.getAssignment().setAssigneeType(responsePayload.getData().getAssignment().getAssigneeType());
        virtualAccount.getAssignment().setExpired(responsePayload.getData().getAssignment().isExpired());
        virtualAccount.getCustomer().setCustomerId(responsePayload.getData().getCustomer().getId());
        virtualAccount.getCustomer().setCustomerCode(responsePayload.getData().getCustomer().getCustomerCode());
        virtualAccount.getCustomer().setEmail(responsePayload.getData().getCustomer().getEmail());
        virtualAccount.getCustomer().setPhone(responsePayload.getData().getCustomer().getPhone());
        virtualAccount.getCustomer().setFirstName(responsePayload.getData().getCustomer().getFirstName());
        virtualAccount.getCustomer().setLastName(responsePayload.getData().getCustomer().getLastName());
        virtualAccount.getCustomer().setRiskAction(responsePayload.getData().getCustomer().getRiskAction());
        repository.saveAndFlush(virtualAccount);

        return responsePayload;
    }

}
