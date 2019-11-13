package com.dmoffat.dkpmanager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class Beans {

    @Autowired private Environment environment;

    @Bean
    public SendGrid sendGridClient() {
        return new SendGrid(environment.getProperty("sendgrid.api-key"));
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
