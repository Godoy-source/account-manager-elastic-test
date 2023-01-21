package com.whiteboard.accountmanager.configuration;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConnections {
    @Value("http://localhost:8080")
    private String logStash;
}
