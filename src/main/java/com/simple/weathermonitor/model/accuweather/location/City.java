package com.simple.weathermonitor.model.accuweather.location;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @JsonAlias("Key")
    private String key;

    @JsonAlias("EnglishName")
    private String name;

    @JsonAlias("Region")
    private Region region;

    @JsonAlias("Country")
    private Country country;

    @JsonAlias("AdministrativeArea")
    private AdministrativeArea administrativeArea;

    @JsonAlias("ParentCity")
    private ParentCity parentCity;
}

