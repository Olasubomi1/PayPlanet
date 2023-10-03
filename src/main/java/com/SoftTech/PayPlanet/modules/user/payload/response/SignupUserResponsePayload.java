package com.SoftTech.PayPlanet.modules.user.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SignupUserResponsePayload {
    private String username;
    private String authToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
