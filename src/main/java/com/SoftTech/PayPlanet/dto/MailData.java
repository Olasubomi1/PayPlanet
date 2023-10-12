package com.SoftTech.PayPlanet.dto;

import lombok.Data;

@Data
public class MailData {
    private String recipientMail;
    private String subject;
    private String content;
    private String contentType;
}
