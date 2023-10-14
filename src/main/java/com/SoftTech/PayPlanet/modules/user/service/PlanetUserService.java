package com.SoftTech.PayPlanet.modules.user.service;

import com.SoftTech.PayPlanet.config.MessageProvider;
import com.SoftTech.PayPlanet.constants.Creator;
import com.SoftTech.PayPlanet.constants.ResponseCode;
import com.SoftTech.PayPlanet.constants.RoleName;
import com.SoftTech.PayPlanet.constants.Status;
import com.SoftTech.PayPlanet.dto.ErrorResponse;
import com.SoftTech.PayPlanet.dto.OtpSendInfo;
import com.SoftTech.PayPlanet.dto.PayloadResponse;
import com.SoftTech.PayPlanet.dto.ServerResponse;
import com.SoftTech.PayPlanet.modules.user.model.PlanetUser;
import com.SoftTech.PayPlanet.modules.user.payload.request.SignupOtpVerificationRequestPayload;
import com.SoftTech.PayPlanet.modules.user.payload.request.SignupUserRequestPayload;
import com.SoftTech.PayPlanet.modules.user.payload.response.SignupOtpVerificationResponsePayload;
import com.SoftTech.PayPlanet.modules.user.payload.response.SignupUserResponsePayload;
import com.SoftTech.PayPlanet.modules.user.repository.IPlanetUserRepository;
import com.SoftTech.PayPlanet.utils.JwtUtil;
import com.SoftTech.PayPlanet.utils.OtpUtil;
import com.SoftTech.PayPlanet.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PlanetUserService implements IPlanetUserService{
    @Autowired
    private IPlanetUserRepository userRepository;

    @Autowired
    private MessageProvider messageProvider;

    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OtpUtil otpUtil;

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
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy(Creator.SYSTEM.name());
        user.setModifiedBy(Creator.SYSTEM.name());
        user.setUpdatedAt(LocalDateTime.now());
        user.setAuthToken(jwtUtil.createJWTString(requestPayload.getEmailAddress()));
        user.setAuthTokenCreationDate(LocalDateTime.now());
        user.setAuthTokenExpirationDate(jwtUtil.getJWTExpiration(LocalDateTime.now()));
        user.setUpdatedAt(LocalDateTime.now());
        user.setLoginAttempt(0);
        user.setOtpVerified(false);
        user.setIsVerified(false);
        user.setUserStatus(Status.UNVERIFIED.name());
        PlanetUser savedUser = userRepository.saveAndFlush(user);

        //send otp
        CompletableFuture
                .runAsync(
                ()->{
//                    smtp service currently not available
//                    System.out.println("initiated mail.");
//                    OtpSendInfo otpSendInfo = otpUtil.sendSignUpOtpMail(requestPayload.getEmailAddress());
//                    System.out.println("sending mail.");
//                    user.setOtp(passwordUtil.hashPassword(otpSendInfo.getOtpSent()));
//                    user.setOtpCreatedDate(otpSendInfo.getCreatedDateTime());
//                    user.setOtpExpDate(otpSendInfo.getExpirationDateTime());
//                    System.out.println(user.getOtp());
                    String otp = "1234";
                    user.setOtp(otp);
                    user.setOtpCreatedDate(LocalDateTime.now());
                    user.setOtpExpDate(user.getOtpCreatedDate().plusMinutes(10));
                    userRepository.saveAndFlush(user);
                    System.out.println(user.getOtp());
                }
        );

        SignupUserResponsePayload responsePayload = new SignupUserResponsePayload();
        responsePayload.setAuthToken(savedUser.getAuthToken());
        responsePayload.setUsername(savedUser.getUsername());
        responsePayload.setCreatedAt(savedUser.getCreatedAt());
        responsePayload.setUpdatedAt(savedUser.getUpdatedAt());

        responseCode = ResponseCode.SUCCESS;
        responseMessage = messageProvider.getMessage(responseCode);
        PayloadResponse response = PayloadResponse.getInstance();
        response.setResponseCode(responseCode);
        response.setResponseMessage(responseMessage);
        response.setResponseData(responsePayload);

        return response;
    }

    @Override
    public ServerResponse signupOtpVerification(SignupOtpVerificationRequestPayload requestPayload, String authToken) {
        String responseCode = ResponseCode.SYSTEM_ERROR;
        String responseMessage = this.messageProvider.getMessage(responseCode);
        ErrorResponse errorResponse = ErrorResponse.getInstance();

        String otpFromUser = requestPayload.getOtp();
        String userEmail = jwtUtil.getUserEmailFromJWTToken(authToken);
        PlanetUser user = userRepository.findByEmailAddress(userEmail);

        // Check for the existence of the user in the system
        if(user == null) {
            responseCode = ResponseCode.RECORD_NOT_FOUND;
            responseMessage = messageProvider.getMessage(responseCode);
            errorResponse.setResponseCode(responseCode);
            errorResponse.setResponseMessage(responseMessage);
            return errorResponse;
        }

        // Check if Otp is already verified.
        if(user.isOtpVerified()){
            responseCode = ResponseCode.OTP_ALREADY_VERIFIED;
            responseMessage = messageProvider.getMessage(responseCode);
            errorResponse.setResponseCode(responseCode);
            errorResponse.setResponseMessage(responseMessage);
            return errorResponse;
        }

        // Check if otp has expired
        if(user.getOtpExpDate().isBefore(LocalDateTime.now())){
            responseCode = ResponseCode.OTP_EXPIRED;
            responseMessage = messageProvider.getMessage(responseCode);
            errorResponse.setResponseCode(responseCode);
            errorResponse.setResponseMessage(responseMessage);
            return errorResponse;
        }

        // Check for the correctness of the otp
        boolean isOtpCorrect = passwordUtil.isPasswordMatch(otpFromUser, user.getOtp());
        if(!isOtpCorrect){
            responseCode = ResponseCode.OTP_INCORRECT;
            responseMessage = messageProvider.getMessage(responseCode);
            errorResponse.setResponseCode(responseCode);
            errorResponse.setResponseMessage(responseMessage);
            return errorResponse;
        }

        // Update the user status
        user.setUserStatus(Status.ACTIVE.name());
        user.setOtpVerified(true);
        user.setIsVerified(true);
        userRepository.saveAndFlush(user);

        CompletableFuture.runAsync(() -> {
            // Asynchronously create a wallet for the user.
//            BetasaveWallet betasaveWallet = new BetasaveWallet();
//            betasaveWallet.setWalletId(WalletUtils.generateUniqueWalletId());
//            betasaveWallet.setUserId(user.getUserId());
//            betasaveWallet.setUserEmail(user.getEmailAddress());
//            betasaveWallet.setCreateAt(LocalDateTime.now());
//            betasaveWallet.setUpdatedAt(LocalDateTime.now());
//            betasaveWallet.setCreatedBy(Creator.SYSTEM.name());
//            betasaveWallet.setUpdatedBy(Creator.SYSTEM.name());
//            betasaveWallet.setCurrencyId(currencyRepository.findByCurrencyName(DEFAULT_CURRENCY_NAME).getId()); // TODO
//            betasaveWallet.setBalance(new BigDecimal(0));
//            walletRepository.save(betasaveWallet);

            // Call a service to create a paystack customer associated with user
            // Use the customerId from paystack to create a virtual account for the customer.

        });

        // Create response to the application client.
        SignupOtpVerificationResponsePayload responsePayload = new SignupOtpVerificationResponsePayload();
        responsePayload.setAuthToken(user.getAuthToken());
        responsePayload.setUserStatus(user.getUserStatus());
        responsePayload.setVerifiedDateTime(LocalDateTime.now());
        responsePayload.setCreatedDateTime(user.getOtpCreatedDate());

        PayloadResponse payloadResponse = PayloadResponse.getInstance();
        payloadResponse.setResponseCode(ResponseCode.SUCCESS);
        payloadResponse.setResponseMessage(messageProvider.getMessage(ResponseCode.SUCCESS));
        payloadResponse.setResponseData(responsePayload);
        return payloadResponse;
    }
}
