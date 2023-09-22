package com.whiteboard.accountmanager.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ApplicationConfiguration {

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
