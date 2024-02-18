package com.SoftTech.PayPlanet.web;

import lombok.Data;

@Data
public class PaystackWebResponse {
    private boolean status = true;
    private String message;
    private boolean isConnectionError;
    private String webDataString = null;
}

