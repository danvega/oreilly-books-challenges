package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@Import({BookService.class})
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService service;

    @Autowired
    private BookController controller;

    /**
     * The BookController class should contain an instance variable called books and a constructor that takes a parameter of type BookService
     * The constructor doesn't need to be annotated with @Autowired but it also doesn't hurt.
     * Can we make sure that the constructor isn't just creating a new instance of the BookService class?
     *
     * mvn -Dtest=BookControllerTest#controllerShouldContainConstructorThatAcceptsBookService test -q
     */
    @Test
    void controllerShouldContainConstructorThatAcceptsBookService() throws NoSuchMethodException {
        Constructor<? extends BookController> constructor = controller.getClass().getConstructor(BookService.class);
        assertNotNull(constructor);
        System.out.println("success");
    }

    /**
     * The findAll() method should call the BookService to find all of the books
     *
     * mvn -Dtest=BookControllerTest#findAllShouldCallServiceClassToFindAllBooks test -q
     */
    @Test
    void findAllShouldCallServiceClassToFindAllBooks() throws Exception {
        final int expectedSize = 3;
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(expectedSize));
        System.out.println("success");
    }

    /**
     * The findOne() method should call the BookService to return a specific book.
     *
     * mvn -Dtest=BookControllerTest#findOneShouldCallServiceClassToFindOneBook test -q
     */
    @Test
    void findOneShouldCallServiceClassToFindOneBook() throws Exception {
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
