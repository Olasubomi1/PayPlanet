package com.SoftTech.PayPlanet.modules.paystack.service;

import com.SoftTech.PayPlanet.config.MessageProvider;
import com.SoftTech.PayPlanet.constants.ResponseCode;
import com.SoftTech.PayPlanet.dto.ErrorResponse;
import com.SoftTech.PayPlanet.dto.PayloadResponse;
import com.SoftTech.PayPlanet.dto.ServerResponse;
import com.SoftTech.PayPlanet.modules.paystack.model.Customer;
import com.SoftTech.PayPlanet.modules.paystack.payload.request.ValidateCustomerRequestPayload;
import com.SoftTech.PayPlanet.modules.paystack.payload.response.CreateCustomerResponse;
import com.SoftTech.PayPlanet.modules.paystack.payload.request.CreateCustomerRequestPayload;
import com.SoftTech.PayPlanet.modules.paystack.payload.response.ValidateCustomerResponsePayload;
import com.SoftTech.PayPlanet.modules.paystack.repository.CustomerRepository;
import com.SoftTech.PayPlanet.web.WebService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class CustomerServiceImp implements CustomerService{
    @Autowired
    private Environment environment;

    @Autowired
    private CustomerRepository repository;

    private static final Gson gson = new Gson();

    @Autowired
    private MessageProvider messageProvider;

    @Override
    public CreateCustomerResponse createPaystackCustomer(String email, String firstName, String lastName, String phone) {
        CreateCustomerResponse responsePayload;
        Customer customer = new Customer();
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

        responsePayload = gson.fromJson(response, CreateCustomerResponse.class);
        log.info("mapped response: {}", responsePayload);

        customer.setCustomerCode(responsePayload.getData().getCustomerCode());
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setIntegration(responsePayload.getData().getIntegration());
        customer.setDomain(responsePayload.getData().getDomain());
        customer.setIdentified(responsePayload.getData().isIdentified());
        customer.setCreatedAt(LocalDateTime.now());
        repository.saveAndFlush(customer);

        return responsePayload;
    }

    @Override
    public ServerResponse handlePaystackCustomerCallback(Map<String, String> params) {
        log.info("handle paystack response: {}", params);
        return null;
    }

    @Override
    public ServerResponse handleValidateCustomer(ValidateCustomerRequestPayload requestPayload) {
        String responseCode = ResponseCode.SYSTEM_ERROR;
        String responseMessage = this.messageProvider.getMessage(responseCode);
        ErrorResponse errorResponse = ErrorResponse.getInstance();

        String customerEmail = requestPayload.getEmail();
        Customer customer = repository.findFirstByEmail(customerEmail);

        // Check for the existence of the user in the system
        if(customer == null) {
            responseCode = ResponseCode.RECORD_NOT_FOUND;
            responseMessage = messageProvider.getMessage(responseCode);
            errorResponse.setResponseCode(responseCode);
            errorResponse.setResponseMessage(responseMessage);
            return errorResponse;
        }

        if (customer.isIdentified()){
            responseCode = ResponseCode.CUSTOMER_ALREADY_IDENTIFIED;
            responseMessage = messageProvider.getMessage(responseCode);
            errorResponse.setResponseCode(responseCode);
            errorResponse.setResponseMessage(responseMessage);
        }

        ValidateCustomerResponsePayload validateCustomerResponsePayload;
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer ".concat(Objects.requireNonNull(environment.getProperty("paystack.secretKey"))));
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        Map<String, Object> pathVariable = new HashMap<>();
        pathVariable.put("customer_code", customer.getCustomerCode());


        ValidateCustomerRequestPayload requestBody = new ValidateCustomerRequestPayload();
                requestBody.setFirstName(requestPayload.getFirstName());
                requestBody.setLastName(requestPayload.getLastName());
                requestBody.setBvn(requestPayload.getBvn());
                requestBody.setAccountNumber(requestPayload.getAccountNumber());
                requestBody.setBankCode(requestPayload.getBankCode());
                requestBody.setCountry(requestPayload.getCountry());
                requestBody.setType(requestPayload.getType());
        String requestJson = gson.toJson(requestBody);

        String url = environment.getProperty("paystack.validateCustomerUrl");
        String response = WebService.postForObject(url, requestJson, null, pathVariable, headers);
        log.info("response: {}", response);

        validateCustomerResponsePayload = gson.fromJson(response, ValidateCustomerResponsePayload.class);
        log.info("mapped response: {}", validateCustomerResponsePayload);

        PayloadResponse payloadResponse = new PayloadResponse();
        payloadResponse.setResponseCode(ResponseCode.SUCCESS);
        payloadResponse.setResponseMessage(messageProvider.getMessage(ResponseCode.SUCCESS));
        payloadResponse.setResponseData(validateCustomerResponsePayload);
        return payloadResponse;
    }

}
