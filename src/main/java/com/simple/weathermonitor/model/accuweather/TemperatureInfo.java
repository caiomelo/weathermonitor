package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TemperatureInfo {

    @JsonProperty("Metric")
    private Temperature metricTemperature;

    @JsonProperty("Imperial")
    private Temperature imperialTemperature;
}
