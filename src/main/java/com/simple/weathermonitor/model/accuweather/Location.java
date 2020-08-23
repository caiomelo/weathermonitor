package com.simple.weathermonitor.model.accuweather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Location {

    @JsonProperty("EnglishName")
    private String name;

    @JsonProperty("LocalizedName")
    private String localizedName;

}
