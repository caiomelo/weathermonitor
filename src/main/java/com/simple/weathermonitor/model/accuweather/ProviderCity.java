package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProviderCity extends ProviderLocation {

    @JsonProperty("Key")
    private String key;

    @JsonProperty("Region")
    private ProviderRegion region;

    @JsonProperty("Country")
    private ProviderCountry country;

    @JsonProperty("AdministrativeArea")
    private ProviderAdministrativeArea administrativeArea;

    @JsonProperty("ParentCity")
    private ProviderCity parentCity;
}
