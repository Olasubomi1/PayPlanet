package com.SoftTech.PayPlanet.modules.user.payload.request;

import lombok.Data;

@Data
public class LoginUserRequestPayload {
    private String email;
    private String password;
}
