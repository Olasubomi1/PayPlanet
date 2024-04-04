package com.SoftTech.PayPlanet.modules.paystack.controller;

import com.SoftTech.PayPlanet.dto.ServerResponse;
import com.SoftTech.PayPlanet.modules.paystack.payload.request.ValidateCustomerRequestPayload;
import com.SoftTech.PayPlanet.modules.paystack.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Tag(name = "Customer Service and Management", description = "Web API service for the management of customer")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customer/verify")
    public ResponseEntity<ServerResponse> handleInitPaystackCallback(@RequestParam Map<String, String> params){
        ServerResponse response = customerService.handlePaystackCustomerCallback(params);
        return null;
    }

    @GetMapping(value = "/customer/validate_customer")
    public ResponseEntity<ServerResponse> validateCustomer(@RequestBody ValidateCustomerRequestPayload requestPayload){
        ServerResponse response = customerService.handleValidateCustomer(requestPayload);
        return ResponseEntity.ok(response);
    }
}
