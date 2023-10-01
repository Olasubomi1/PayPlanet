package com.SoftTech.PayPlanet.modules.user.payload.request;

import lombok.Data;

@Data
public class SignupUserRequestPayload {
    private String username;
    private String mobileNumber;
    private String emailAddress;
    private String password;
    private String gender;
}
