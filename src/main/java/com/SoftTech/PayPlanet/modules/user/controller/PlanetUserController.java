package com.SoftTech.PayPlanet.modules.user.controller;

import com.SoftTech.PayPlanet.dto.ServerResponse;
import com.SoftTech.PayPlanet.modules.user.payload.request.LoginUserRequestPayload;
import com.SoftTech.PayPlanet.modules.user.payload.request.SignupOtpVerificationRequestPayload;
import com.SoftTech.PayPlanet.modules.user.payload.request.SignupUserRequestPayload;
import com.SoftTech.PayPlanet.modules.user.service.IPlanetUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "api/user")
public class PlanetUserController {

    @Autowired
    private IPlanetUserService iPlanetUserService;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServerResponse> signupPlanetUser(@RequestBody SignupUserRequestPayload requestPayload){
        // TODO: Validation of the request body.
        ServerResponse serverResponse = iPlanetUserService.signupUser(requestPayload);
        return ResponseEntity.ok(serverResponse);
    }

    @PostMapping(value = "/verify-signup-otp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServerResponse> verifySignupOtp(@RequestBody SignupOtpVerificationRequestPayload requestPayload, @RequestHeader("Authorization") String authToken){
        ServerResponse serverResponse = iPlanetUserService.signupOtpVerification(requestPayload, authToken);
        return ResponseEntity.ok(serverResponse);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServerResponse> loginPlanetUser(@RequestBody LoginUserRequestPayload requestPayload){
        // todo: Validation of request payload
        ServerResponse serverResponse = iPlanetUserService.loginUser(requestPayload);
        return ResponseEntity.ok(serverResponse);
    }
}
