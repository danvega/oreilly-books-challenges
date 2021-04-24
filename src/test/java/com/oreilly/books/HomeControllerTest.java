package com.oreilly.books;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeController controller;

    /**
     * home() Should have a GetMapping("/")
     *
     * mvn -Dtest=HomeControllerTest#homeMethodShouldRespondToRootContext test -q
     */
    @Test
    void homeMethodShouldRespondToRootContext() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    /**
     * A field of type String with the name of welcomeMsg should be declared in the controller
     *
     * mvn -Dtest=HomeControllerTest#welcomeMsgInstanceVariableShouldBeDeclared test -q
     */
    @Test
    void welcomeMsgInstanceVariableShouldBeDeclared() throws NoSuchFieldException {
        Field welcomeMsg = controller.getClass().getDeclaredField("welcomeMsg");
        assertNotNull(welcomeMsg);
        assertEquals("java.lang.String",welcomeMsg.getType().getName());
        System.out.println("success");
    }

    /**
     * With no value set in application.properties welcomeMsg should default to "Hello, World!"
     *
     * mvn -Dtest=HomeControllerTest#welcomeMsgShouldReturnHelloWorldByDefault test -q
     */
    @Test
    void welcomeMsgShouldReturnHelloWorldByDefault() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    /**
     * welcomeMsg=Hello, Spring Boot Challenges! set in application.properties
     *
     * mvn -Dtest=HomeControllerTest#welcomeMsgShouldReturnHelloSpringBootChallengesFromProperties test -q
     */
    @Test
    void welcomeMsgShouldReturnHelloSpringBootChallengesFromProperties() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Spring Boot Challenges!"));
    }

}
