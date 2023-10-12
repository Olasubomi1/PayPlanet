package com.SoftTech.PayPlanet.config;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Properties;

@Configuration
@Slf4j
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(new ClassPathResource("messages.yml"));
        messageSource.setCommonMessages(bean.getObject());
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(5);
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }
//    @Bean
//    public ResourceBundleMessageSource envSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
//        bean.setResources(new ClassPathResource(".env"));
//        messageSource.setCommonMessages(bean.getObject());
//        messageSource.setUseCodeAsDefaultMessage(true);
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setCacheSeconds(0);
//        return messageSource;
//    }

    @Bean
    public JavaMailSender javaMailSender(){
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.debug", "true");
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(587);
        mailSender.setPassword(env.getProperty("spring.mail.password"));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setJavaMailProperties(props);

//        mailSender.setProtocol("");

        return mailSender;
    }
}
