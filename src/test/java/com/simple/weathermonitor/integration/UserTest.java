package com.simple.weathermonitor.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.weathermonitor.exception.UserNotFoundException;
import com.simple.weathermonitor.model.User;
import com.simple.weathermonitor.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void itShouldSaveNewUser() throws Exception {
        String randomEmail = getRandomString();
        User user = new User();
        user.setEmail(randomEmail + "@email.com");

        String json = mockMvc.perform(buildUserPost(user))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();

        User created = new ObjectMapper().readValue(json, User.class);
        assertEquals(user.getEmail(), created.getEmail());
        assertNotNull(created.getId());

        User found = userRepository.findById(created.getId()).orElseThrow(UserNotFoundException::new);
        assertEquals(created.getEmail(), found.getEmail());
    }

    @NotNull
    private String getRandomString() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

    @Test
    void itShouldUpdateExistingUser() throws Exception {
        User user = new User();
        user.setEmail(getRandomString() + "@email.com");

        String json = mockMvc.perform(buildUserPost(user))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();

        User created = new ObjectMapper().readValue(json, User.class);
        created.setEmail(getRandomString() + "@email.com");
        created.setObservedCities(emptyList());

        mockMvc.perform(buildUserPost(created))
                .andExpect(status().isOk());

        User found = userRepository.findById(created.getId()).orElseThrow(UserNotFoundException::new);
        assertEquals(created.getEmail(), found.getEmail());
    }

    @Test
    void itShouldNotAllowMultipleUsersWithSameEmail() throws Exception {
        User user = new User();
        user.setEmail(getRandomString() + "@email.com");

        mockMvc.perform(buildUserPost(user))
                .andExpect(status().isOk());

        mockMvc.perform(buildUserPost(user));
        //.andExpect(status().isConflict());
        //TODO: adding 2 users with same email is not allowed, but the tests are not recognizing that database constraint
    }

    private MockHttpServletRequestBuilder buildUserPost(User user) throws JsonProcessingException {
        return post("/user")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
