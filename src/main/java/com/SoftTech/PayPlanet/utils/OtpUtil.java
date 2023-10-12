package com.SoftTech.PayPlanet.utils;

import com.SoftTech.PayPlanet.dto.MailData;
import com.SoftTech.PayPlanet.dto.OtpSendInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class OtpUtil {
    @Autowired
    private Environment env;

    @Autowired
    private MailUtil mailUtil;

    private static final int OTP_LENGTH = 6;

    private String generateOtp(){
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomInteger = (int)(Math.random()* 10);
            stringBuilder.append(randomInteger);
        }
        return stringBuilder.toString();
    }

    public OtpSendInfo sendSignUpOtpMail(String email){
        String fileName = "otp-signup";
        String otpExpirationTime = env.getProperty("payplanet.otp.signup.expirationTimeInMin");
        OtpSendInfo otpSendInfo = new OtpSendInfo();
        try {
            String signupTemplate = MessageTemplateUtil.getTemplateOf(fileName);
            String otp = generateOtp();
            otpSendInfo.setOtpSent(otp);
            otpSendInfo.setRecipientEmail(email);
            otpSendInfo.setCreatedDateTime(LocalDateTime.now());
            otpSendInfo.setExpirationDateTime(otpSendInfo.getCreatedDateTime().plusMinutes(Integer.parseInt(otpExpirationTime) ));
            signupTemplate = signupTemplate.replace("{otp}",otp)
                    .replace("{otpExpiration}",otpExpirationTime);

            MailData mailData = new MailData();
            mailData.setRecipientMail(email);
            mailData.setSubject("Pay-Planet Email Verification");
            mailData.setContent(signupTemplate);
            System.out.println("maildata: " +mailData);
            mailUtil.sendNotification(mailData);
        } catch (IOException ignored) {
            System.out.println(ignored);
        }
        return otpSendInfo;
    }

}
