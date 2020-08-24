package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CurrentConditions {

    @JsonProperty("LocalObservationDateTime")
    private Date localObservedDate;

    @JsonProperty("Temperature")
    private TemperatureInfo temperatureInfo;
}
