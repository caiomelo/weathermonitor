package com.simple.weathermonitor.model.accuweather.location;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ParentCity {

    @JsonAlias("Key")
    private final String key;

    @JsonAlias("EnglishName")
    private final String name;
}
