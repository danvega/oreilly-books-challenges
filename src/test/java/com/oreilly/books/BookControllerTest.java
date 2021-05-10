package com.oreilly.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    BookController controller;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * I will make sure that the Book parameter has the required @Valid annotation
     *
     * mvn -Dtest=BookControllerTest#bookParameterShouldHaveValidAnnotation test -q
     * @throws NoSuchMethodException
     */
    @Test
    void bookParameterShouldHaveValidAnnotation() throws NoSuchMethodException {
        Annotation[][] annotations = controller.getClass().getMethod("create", Book.class).getParameterAnnotations();
        assertNotNull(annotations);
        assertEquals(1, annotations.length);
        assertEquals("Valid", annotations[0][0].annotationType().getSimpleName());
        System.out.println("success");
    }

    /**
     * I will make sure that the book title is not allowed to be blank.
     *
     * mvn -Dtest=BookControllerTest#bookTitleShouldNotBeBlank,BookControllerTest#bookAuthorShouldNotBeBlank test -q
     *
     * @throws Exception
     */
    @Test
    void bookTitleShouldNotBeBlank() throws Exception {
        Book book = new Book();
        book.setTitle("");
        book.setAuthor("Josh Long & Kenny Bastani");
        book.setPublisher("OReilly Media, Inc.");
        book.setReleaseDate("September 2019");
        book.setIsbn("9781449374648");
        book.setTopic("Spring Cloud");
        book.setPrice(46.62);

        mvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());

        System.out.println("success");
    }

    /**
     * I will make sure that the book author is not allowed to be blank.
     *
     * mvn -Dtest=BookControllerTest#bookTitleShouldNotBeBlank,BookControllerTest#bookAuthorShouldNotBeBlank test -q
     *
     * @throws Exception
     */
    @Test
    void bookAuthorShouldNotBeBlank() throws Exception {
        Book book = new Book();
        book.setTitle("Cloud Native Java: Designing Resilient Systems with Spring Boot, Spring Cloud, and Cloud Foundry");
        book.setAuthor("");
        book.setPublisher("OReilly Media, Inc.");
        book.setReleaseDate("September 2019");
        book.setIsbn("9781449374648");
        book.setTopic("Spring Cloud");
        book.setPrice(46.62);

        mvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());

        System.out.println("success");
    }

    /**
     * I will make sure that the ISBN is at least 10 characters in length
     *
     * mvn -Dtest=BookControllerTest#bookIsbnShouldBe10CharactersInLength test -q
     *
     * @throws Exception
     */
    @Test
    void bookIsbnShouldBe10CharactersInLength() throws Exception {
        Book book = new Book();
        book.setTitle("Cloud Native Java: Designing Resilient Systems with Spring Boot, Spring Cloud, and Cloud Foundry");
        book.setAuthor("Josh Long & Kenny Bastani");
        book.setPublisher("OReilly Media, Inc.");
        book.setReleaseDate("September 2019");
        book.setIsbn("1234");
        book.setTopic("Spring Cloud");
        book.setPrice(46.62);

        mvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());

        System.out.println("success");
    }


    /**
     * I will make sure that the book price is greater than 0
     *
     * mvn -Dtest=BookControllerTest#bookIsbnShouldBe10CharactersInLength test -q
     *
     * @throws Exception
     */
    @Test
    void bookPriceShouldBeGreaterThanZero() throws Exception {
        Book book = new Book();
        book.setTitle("Cloud Native Java: Designing Resilient Systems with Spring Boot, Spring Cloud, and Cloud Foundry");
        book.setAuthor("Josh Long & Kenny Bastani");
        book.setPublisher("OReilly Media, Inc.");
        book.setReleaseDate("September 2019");
        book.setIsbn("9781449374648");
        book.setTopic("Spring Cloud");
        book.setPrice(0.00);

        mvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());

        System.out.println("success");
    }


}
