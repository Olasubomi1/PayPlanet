package com.SoftTech.PayPlanet.modules.user.service;

import com.SoftTech.PayPlanet.dto.ServerResponse;
import com.SoftTech.PayPlanet.modules.user.payload.request.SignupUserRequestPayload;

public interface IPlanetUserService {
    ServerResponse signupUser(SignupUserRequestPayload requestPayload);
}
