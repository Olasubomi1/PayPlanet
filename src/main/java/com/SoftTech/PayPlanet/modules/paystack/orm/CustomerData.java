package com.SoftTech.PayPlanet.modules.paystack.orm;

import lombok.Data;

@Data
public class CustomerData {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String customerCode;
    private String phone;
    private String riskAction;
}
