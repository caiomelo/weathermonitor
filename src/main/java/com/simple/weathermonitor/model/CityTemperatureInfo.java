package com.simple.weathermonitor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simple.weathermonitor.model.accuweather.temperature.TemperatureObservation;
import com.simple.weathermonitor.model.accuweather.temperature.Temperature;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CityTemperatureInfo {

    private String cityName;
    private String temperatureMetric;
    private String temperatureImperial;
    private Date actualObservationDate;
    private Date observationPeriodStart;
    private Date observationPeriodEnd;

    public CityTemperatureInfo(UserObservedCity userObservedCity, TemperatureObservation currentConditions) {
        this.actualObservationDate = currentConditions.getLocalObservedDate();
        this.temperatureMetric = buildTemperature(currentConditions.getTemperatureInfo().getMetricTemperature());
        this.temperatureImperial = buildTemperature(currentConditions.getTemperatureInfo().getImperialTemperature());

        this.cityName = userObservedCity.getCityName();
        this.observationPeriodStart = userObservedCity.getObservationPeriodStart();
        this.observationPeriodEnd = userObservedCity.getObservationPeriodEnd();
    }

    private String buildTemperature(Temperature temperature) {
        return temperature.getValue() + " " + temperature.getUnit();
    }

}
