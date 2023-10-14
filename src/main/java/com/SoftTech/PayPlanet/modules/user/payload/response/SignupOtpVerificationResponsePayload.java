package com.SoftTech.PayPlanet.modules.user.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SignupOtpVerificationResponsePayload {
    private String authToken;
    private String userStatus;
    private LocalDateTime createdDateTime;
    private LocalDateTime verifiedDateTime;
}