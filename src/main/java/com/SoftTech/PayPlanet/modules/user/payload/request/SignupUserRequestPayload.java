package com.SoftTech.PayPlanet.modules.user.payload.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SignupUserRequestPayload {
    private String username;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String gender;
}
