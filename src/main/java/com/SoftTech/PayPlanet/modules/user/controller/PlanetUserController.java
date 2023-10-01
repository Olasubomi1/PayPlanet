package com.SoftTech.PayPlanet.modules.user.controller;

import com.SoftTech.PayPlanet.dto.ServerResponse;
import com.SoftTech.PayPlanet.modules.user.payload.request.SignupUserRequestPayload;
import com.SoftTech.PayPlanet.modules.user.service.IPlanetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}