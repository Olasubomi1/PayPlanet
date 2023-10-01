package com.SoftTech.PayPlanet.modules.user.service;

import com.SoftTech.PayPlanet.config.MessageProvider;
import com.SoftTech.PayPlanet.constants.ResponseCode;
import com.SoftTech.PayPlanet.dto.ErrorResponse;
import com.SoftTech.PayPlanet.dto.ServerResponse;
import com.SoftTech.PayPlanet.modules.user.model.PlanetUser;
import com.SoftTech.PayPlanet.modules.user.payload.request.SignupUserRequestPayload;
import com.SoftTech.PayPlanet.modules.user.repository.IPlanetUserRepository;
import com.SoftTech.PayPlanet.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlanetUserService implements IPlanetUserService{
    @Autowired
    private IPlanetUserRepository userRepository;

    @Autowired
    private MessageProvider messageProvider;

    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    public ServerResponse signupUser(SignupUserRequestPayload requestPayload) {
        String responseCode = ResponseCode.SYSTEM_ERROR;
        String responseMessage = messageProvider.getMessage(responseCode);
        ErrorResponse errorResponse = ErrorResponse.getInstance();

        // Check if the user already exist in database by email, username and phone number
        PlanetUser userByEmail = userRepository.findByEmailAddress(requestPayload.getEmailAddress());
        if(userByEmail != null){
            responseCode = ResponseCode.RECORD_ALREADY_EXIST_BY_EMAIL;
            responseMessage = messageProvider.getMessage(responseCode);
            errorResponse.setResponseCode(responseCode);
            errorResponse.setResponseMessage(responseMessage);
            return errorResponse;
        }

        // Check if the user exists by username
        PlanetUser userByUsername = userRepository.findByUsername(requestPayload.getUsername());
        if(userByUsername != null){
            responseCode = ResponseCode.RECORD_ALREADY_EXIST_BY_USERNAME;
            responseMessage = messageProvider.getMessage(responseCode);
            errorResponse.setResponseCode(responseCode);
            errorResponse.setResponseMessage(responseMessage);
            return errorResponse;
        }

        // Check if the user already exists by mobileNumber.
        PlanetUser userByMobileNumber = userRepository.findByMobileNumber(requestPayload.getMobileNumber());
        if(userByMobileNumber != null) {
            responseCode = ResponseCode.RECORD_ALREADY_EXIST_BY_MOBILE_NUMBER;
            responseMessage = messageProvider.getMessage(responseCode);
            errorResponse.setResponseCode(responseCode);
            errorResponse.setResponseMessage(responseMessage);
            return errorResponse;
        }

        //Create user entity
        PlanetUser user = new PlanetUser();
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername(requestPayload.getUsername());
        user.setEmailAddress(requestPayload.getEmailAddress());
        user.setPassword(passwordUtil.hashPassword(requestPayload.getPassword()));
        user.setMobileNumber(requestPayload.getMobileNumber());
        user.setGender(requestPayload.getGender().toUpperCase());
        // getting jwt secret
        user.setOtpVerified(false);
        user.setIsVerified(false);

        return null;
    }
}