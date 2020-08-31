package com.simple.weathermonitor.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.weathermonitor.client.AccuWeatherClient;
import com.simple.weathermonitor.configuration.ConfigurationService;
import com.simple.weathermonitor.model.User;
import com.simple.weathermonitor.model.UserObservedCity;
import com.simple.weathermonitor.model.accuweather.temperature.Temperature;
import com.simple.weathermonitor.model.accuweather.temperature.TemperatureInfo;
import com.simple.weathermonitor.model.accuweather.temperature.TemperatureObservation;
import com.simple.weathermonitor.repository.UserObservedCityRepository;
import com.simple.weathermonitor.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
class UserObservedCityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserObservedCityRepository repository;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private AccuWeatherClient weatherProvider;

    @MockBean
    private ConfigurationService configurationService;

    private User savedUser;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setEmail(UUID.randomUUID().toString().substring(0, 10) + "@email.com");
        savedUser = userRepository.save(user);

        when(configurationService.getApiKey()).thenReturn(API_KEY);
    }

    @Test
    void itShouldSaveNewObservation() throws Exception {
        UserObservedCity userObservedCity = buildObservationFor("4336", "Fortaleza");
        String observationJson = getJsonWithUser(userObservedCity);

        String responseJson = mockMvc.perform(buildPost(observationJson))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();

        UserObservedCity created = new ObjectMapper().readValue(responseJson, UserObservedCity.class);
        assertNotNull(created.getId());
        assertEqualAttributes(userObservedCity, created);

        UserObservedCity found = repository.findById(created.getId()).orElseThrow(EntityNotFoundException::new);
        assertEqualAttributes(userObservedCity, found);
    }

    @Test
    void itShouldRetrieveOnlyTemperatureInfoForActiveObservations() throws Exception {
        UserObservedCity observingFortaleza = buildObservationFor("4336", "Fortaleza");
        observingFortaleza.setUser(savedUser);
        repository.save(observingFortaleza);

        UserObservedCity observingCrato = buildObservationFor("32080", "Crato");
        observingCrato.setObservationPeriodStart(Date.from(Instant.now().minus(Period.ofDays(5))));
        observingCrato.setObservationPeriodEnd(Date.from(Instant.now().minus(Period.ofDays(1))));
        observingCrato.setUser(savedUser);
        repository.save(observingCrato);

        TemperatureObservation temperatureObservation = buildTemperatureObservation();

        when(weatherProvider.getCurrentConditions(API_KEY, observingFortaleza.getExternalId()))
                .thenReturn(singletonList(temperatureObservation));

        MvcResult result = mockMvc.perform(get("/observation/" + savedUser.getEmail()))
                .andExpect(status().isOk())
                .andReturn();

        List<Map<String, Object>> observations = new ObjectMapper().readValue(result.getResponse().getContentAsString(), List.class);
        assertEquals(1, observations.size());
        Map<String, Object> cityTemperatureInfo = observations.get(0);
        assertEquals(observingFortaleza.getCityName(), cityTemperatureInfo.get("cityName"));
        assertEquals("30 C", cityTemperatureInfo.get("temperatureMetric"));
        assertEquals("86 F", cityTemperatureInfo.get("temperatureImperial"));
        assertNotNull(cityTemperatureInfo.get("observationPeriodStart"));
        assertNotNull(cityTemperatureInfo.get("observationPeriodEnd"));
        assertNotNull(cityTemperatureInfo.get("actualObservationDate"));
    }

    private UserObservedCity buildObservationFor(String externalId, String name) {
        UserObservedCity userObservedCity = new UserObservedCity();
        userObservedCity.setCityName(name);
        userObservedCity.setExternalId(externalId);
        userObservedCity.setObservationPeriodStart(new Date());
        userObservedCity.setObservationPeriodEnd(Date.from(Instant.now().plus(Period.ofDays(5))));
        return userObservedCity;
    }

    private String getJsonWithUser(UserObservedCity userObservedCity) throws JsonProcessingException {
        String userJson = new ObjectMapper().writeValueAsString(savedUser);
        String observationJson = new ObjectMapper().writeValueAsString(userObservedCity);
        return observationJson.replace("}", ",\"user\":" + userJson + "}");
    }

    private TemperatureObservation buildTemperatureObservation() {
        Temperature metric = new Temperature();
        metric.setUnit("C");
        metric.setValue(30);

        Temperature imperial = new Temperature();
        imperial.setUnit("F");
        imperial.setValue(86);

        TemperatureInfo temperatureInfo = new TemperatureInfo();
        temperatureInfo.setMetricTemperature(metric);
        temperatureInfo.setImperialTemperature(imperial);

        TemperatureObservation temperatureObservation = new TemperatureObservation();
        temperatureObservation.setTemperatureInfo(temperatureInfo);
        temperatureObservation.setLocalObservedDate(Date.from(Instant.now().minus(3, ChronoUnit.HOURS)));

        return temperatureObservation;
    }

    private void assertEqualAttributes(UserObservedCity expected, UserObservedCity actual) {
        assertEquals(expected.getCityName(), actual.getCityName());
        assertEquals(expected.getExternalId(), actual.getExternalId());
        assertEquals(expected.getObservationPeriodStart(), actual.getObservationPeriodStart());
        assertEquals(expected.getObservationPeriodEnd(), actual.getObservationPeriodEnd());
    }

    private MockHttpServletRequestBuilder buildPost(String json) {
        return post("/observation")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    private static final String API_KEY = "1234567890";
}
