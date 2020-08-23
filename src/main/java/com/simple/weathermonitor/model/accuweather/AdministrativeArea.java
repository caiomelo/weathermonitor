package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdministrativeArea extends Location {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("LocalizedType")
    private String localizedType;

    @JsonProperty("EnglishType")
    private String type;
}
