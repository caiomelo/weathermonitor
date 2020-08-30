package com.simple.weathermonitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.weathermonitor.controller.UserController;
import com.simple.weathermonitor.controller.UserObservedCityController;
import com.simple.weathermonitor.controller.CityController;
import com.simple.weathermonitor.model.User;
import com.simple.weathermonitor.repository.UserObservedCityRepository;
import com.simple.weathermonitor.repository.UserRepository;
import com.simple.weathermonitor.service.UserObservedCityService;
import com.simple.weathermonitor.service.UserService;
import com.simple.weathermonitor.service.CityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherMonitorApplicationTests {

    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserObservedCityController userObservedCityController;
    @Autowired
    private UserObservedCityService userObservedCityService;
    @Autowired
    private UserObservedCityRepository userObservedCityRepository;

    @Autowired
    private CityController weatherController;
    @Autowired
    private CityService weatherService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertNotNull(userController);
        assertNotNull(userService);
        assertNotNull(userRepository);

        assertNotNull(userObservedCityController);
        assertNotNull(userObservedCityService);
        assertNotNull(userObservedCityRepository);

        assertNotNull(weatherController);
        assertNotNull(weatherService);
    }

    @Test
    void t() throws Exception {
        User user = new User();
        user.setId(3000);

        Mockito.when(userRepository.findAll()).thenReturn(singletonList(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(singletonList(user))));
    }

}
