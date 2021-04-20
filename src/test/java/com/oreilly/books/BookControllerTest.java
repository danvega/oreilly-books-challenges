package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController controller;


    /**
     * The BookController class should contain the @RestController annotation
     * mvn -Dtest=BookControllerTest#controllerShouldContainRestControllerAnnotation test -q
     */
    @Test
    void controllerShouldContainRestControllerAnnotation() {
        RestController annotation = controller.getClass().getAnnotation(RestController.class);
        assertNotNull(annotation);
        System.out.println("success");
    }

    /**
     * The BookController class should contain the @RequestMapping("/books") annotation
     * mvn -Dtest=BookControllerTest#controllerShouldContainRequestMappingAnnotation test -q
     */
    @Test
    void controllerShouldContainRequestMappingAnnotation() {
        RequestMapping annotation = controller.getClass().getAnnotation(RequestMapping.class);
        String[] paths = annotation.value();

        assertNotNull(annotation);
        assertEquals(1,paths.length);
        assertEquals("/books",paths[0]);
        System.out.println("success");
    }

    /**
     * GET /books returns 3 books
     * mvn -Dtest=BookControllerTest#findAllShouldReturnListOfBooks test -q
     */
    @Test
    void findAllShouldReturnListOfBooks() throws Exception {
        final int expectedSize = 3;
        mockMvc.perform(get("/books"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(expectedSize));
        System.out.println("success");
    }

    /**
     * GET /books/1 returns valid book
     * mvn -Dtest=BookControllerTest#findOneWithValidIdShouldReturnOneBook test -q
     */
    @Test
    void findOneWithValidIdShouldReturnOneBook() throws Exception {
        mockMvc.perform(get("/books/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("97 Things Every Java Programmer Should Know"))
            .andExpect(jsonPath("$.author").value("Kevlin Henney, Trisha Gee"))
            .andExpect(jsonPath("$.publisher").value("OReilly Media, Inc."))
            .andExpect(jsonPath("$.releaseDate").value("May 2020"))
            .andExpect(jsonPath("$.isbn").value("9781491952696"))
            .andExpect(jsonPath("$.topic").value("Java"));
        System.out.println("success");
    }

}
