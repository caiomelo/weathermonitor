package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class City extends Location {

    @JsonProperty("Key")
    private String key;

    @JsonProperty("Region")
    private Region region;

    @JsonProperty("Country")
    private Country country;

    @JsonProperty("AdministrativeArea")
    private AdministrativeArea administrativeArea;

    @JsonProperty("ParentCity")
    private City parentCity;
}
