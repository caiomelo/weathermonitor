package com.simple.weathermonitor.model.accuweather.temperature;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Temperature {

    @JsonProperty("Value")
    private Integer value;

    @JsonProperty("Unit")
    private String unit;
}
