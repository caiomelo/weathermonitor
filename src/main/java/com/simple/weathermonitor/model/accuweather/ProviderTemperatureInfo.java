package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProviderTemperatureInfo {

    @JsonProperty("Metric")
    private ProviderTemperature metricTemperature;

    @JsonProperty("Imperial")
    private ProviderTemperature imperialTemperature;
}