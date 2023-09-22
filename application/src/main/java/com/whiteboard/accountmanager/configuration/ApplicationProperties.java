package com.whiteboard.accountmanager.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "variables")
public class ApplicationProperties {

    private String logstash;
    private String indiceAccounts;
}
