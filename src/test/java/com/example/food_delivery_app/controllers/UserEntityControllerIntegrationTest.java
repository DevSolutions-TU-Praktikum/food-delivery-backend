package com.example.food_delivery_app.controllers;

import com.example.food_delivery_app.Entity.UserEntity;
import com.example.food_delivery_app.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserEntityControllerIntegrationTest {

    @Autowired
    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @Autowired
    public UserEntityControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatUserCanBeCreatedSuccesfullyReturnsHttp201Created() throws Exception {
        UserEntity testUserA =new UserEntity();
        testUserA.setUsername("testUserA");
        testUserA.setPassword("<PASSWORD>");
        testUserA.setUserEmail("<EMAIL>");
        testUserA.setUserPhoneNumber("1234567890");
//        testUserA.setId(null);
        String userJson = objectMapper.writeValueAsString(testUserA);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/signup")
                        .contentType("application/json")
                        .content(userJson))
                        .andExpect(status().isCreated());
    }
}


