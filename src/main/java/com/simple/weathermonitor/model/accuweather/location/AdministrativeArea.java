package com.simple.weathermonitor.model.accuweather.location;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AdministrativeArea {

    @JsonAlias("ID")
    private final String id;

    @JsonAlias("EnglishName")
    private final String name;

    @JsonAlias("EnglishType")
    private final String type;
}
