package com.SoftTech.PayPlanet.utils;

import com.SoftTech.PayPlanet.dto.MailData;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Service
public class MailUtil {
    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;

    public void sendNotification(MailData mailData)throws MailException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, "utf-8");
        try {
            messageHelper.setText(mailData.getContent(), true);
            messageHelper.setFrom(env.getProperty("spring.mail.username"));
            messageHelper.setTo(mailData.getRecipientMail());
            messageHelper.setSubject(mailData.getSubject());

//            System.out.println("message2: " +messageHelper.getMimeMessage().getContent());
            mailSender.send(message);
        }catch (MessagingException ignore) {
            System.out.println(ignore);
        }
    }
}