package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProviderLocation {

    @JsonProperty("EnglishName")
    private String name;
}
