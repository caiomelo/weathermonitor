package com.simple.weathermonitor.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.weathermonitor.client.AccuWeatherClient;
import com.simple.weathermonitor.configuration.ConfigurationService;
import com.simple.weathermonitor.model.accuweather.location.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
class CityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccuWeatherClient weatherProvider;

    @MockBean
    private ConfigurationService configurationService;

    @BeforeEach
    void setUp() {
        when(configurationService.getApiKey()).thenReturn(API_KEY);
    }

    @Test
    void itShouldBeAbleToTextSearchForACity() throws Exception {
        String searchText = "Fortaleza";

        City city = City.builder().key("123").name(searchText).build();
        List<City> cities = Collections.singletonList(city);

        when(weatherProvider.search(API_KEY, searchText)).thenReturn(cities);

        String json = mockMvc.perform(get("/city/search?searchText=" + searchText))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Map<String, Object> found = new ObjectMapper().readValue(json.substring(1, json.length() - 1), Map.class);
        assertEquals(city.getKey(), found.get("key"));
        assertEquals(city.getName(), found.get("name"));
    }

    @Test
    void itShouldBeAbleToSearchForACityByItsKey() throws Exception {
        City city = City.builder().key("123").name("Fortaleza").build();

        when(weatherProvider.getCityInfo(API_KEY, city.getKey())).thenReturn(city);

        String json = mockMvc.perform(get("/city/" + city.getKey()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        City found = new ObjectMapper().readValue(json, City.class);
        assertEquals(city.getKey(), found.getKey());
        assertEquals(city.getName(), found.getName());
    }

    private static final String API_KEY = "1234567890";
}
