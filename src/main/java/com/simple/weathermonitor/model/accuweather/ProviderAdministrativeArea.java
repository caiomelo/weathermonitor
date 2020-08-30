package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProviderAdministrativeArea extends ProviderLocation {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("EnglishType")
    private String type;
}
