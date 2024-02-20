package com.SoftTech.PayPlanet.modules.paystack.controller;

import com.SoftTech.PayPlanet.dto.ServerResponse;
import com.SoftTech.PayPlanet.modules.paystack.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@Tag(name = "Customer Service and Management", description = "Web API service for the management of customer")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping(value = "/customer/verify")
    public ResponseEntity<ServerResponse> handleInitPaystackCallback(@RequestParam Map<String, String> params){
        ServerResponse response = customerService.handlePaystackCustomerCallback(params);
        return null;
    }
}
