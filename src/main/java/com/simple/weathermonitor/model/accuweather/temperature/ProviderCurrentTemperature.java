package com.simple.weathermonitor.model.accuweather.temperature;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ProviderCurrentTemperature {

    @JsonProperty("LocalObservationDateTime")
    private Date localObservedDate;

    @JsonProperty("Temperature")
    private ProviderTemperatureInfo temperatureInfo;
}
