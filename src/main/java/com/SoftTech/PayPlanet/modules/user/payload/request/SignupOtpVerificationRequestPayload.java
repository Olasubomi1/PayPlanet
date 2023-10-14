package com.SoftTech.PayPlanet.modules.user.payload.request;

import lombok.Data;

@Data
public class SignupOtpVerificationRequestPayload {
    private String otp;
}
