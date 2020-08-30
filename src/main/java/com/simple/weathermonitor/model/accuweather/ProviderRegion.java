package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProviderRegion extends ProviderLocation {

    @JsonProperty("ID")
    private String id;

}
