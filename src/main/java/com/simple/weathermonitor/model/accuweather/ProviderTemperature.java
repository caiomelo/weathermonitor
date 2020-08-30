package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProviderTemperature {

    @JsonProperty("Value")
    private Integer value;

    @JsonProperty("Unit")
    private String unit;
}
