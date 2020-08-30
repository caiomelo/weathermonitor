package com.simple.weathermonitor.model.accuweather.location;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@AllArgsConstructor
public class City {

    @JsonAlias("Key")
    private final String key;

    @JsonAlias("EnglishName")
    private final String name;

    @JsonAlias("Region")
    private final Region region;

    @JsonAlias("Country")
    private final Country country;

    @JsonAlias("AdministrativeArea")
    private final AdministrativeArea administrativeArea;

    @JsonAlias("ParentCity")
    private final ParentCity parentCity;
}

