package com.example.exam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "petstore")
public class PetstoreProperties {
    private String baseUrl;
    private int connectTimeout = 5000;
    private int readTimeout = 10000;
}
