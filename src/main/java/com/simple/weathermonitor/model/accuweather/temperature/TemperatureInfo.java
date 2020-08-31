package com.simple.weathermonitor.model.accuweather.temperature;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemperatureInfo {

    @JsonProperty("Metric")
    private Temperature metricTemperature;

    @JsonProperty("Imperial")
    private Temperature imperialTemperature;
}
