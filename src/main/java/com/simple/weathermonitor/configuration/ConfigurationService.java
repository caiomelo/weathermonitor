package com.simple.weathermonitor.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConfigurationService {

    @Value("${weatherservice.apikey}")
    private String apiKey;
}
