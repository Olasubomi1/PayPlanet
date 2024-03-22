package com.SoftTech.PayPlanet.modules.paystack.orm;

import lombok.Data;

@Data
public class Assignment {
    private int integration;
    private int assigneeId;
    private String assigneeType;
    private boolean expired;
    private String accountType;
    private String assignedAt;
}
