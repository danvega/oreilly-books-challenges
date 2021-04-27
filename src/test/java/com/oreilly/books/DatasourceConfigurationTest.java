package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DatasourceConfigurationTest {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.name}")
    private String name;

    @Value("${spring.datasource.url}")
    private String url;

    /**
     * Task 1: Validate that they filled in the correct values in application.properties to setup the datasource connection.
     *
     */
    @Test
    public void shouldSetDatasourceProperties() {
        assertEquals("user",username);
        assertEquals("password",password);
        assertEquals("books",name);
        assertEquals("jdbc:h2:mem:books",url);
    }

}
