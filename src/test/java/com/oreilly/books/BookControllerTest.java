package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class BookControllerTest {

    @Autowired
    BookController controller;

    @Test
    void createMethodShouldHavePostMapping() throws NoSuchMethodException {
        PostMapping annotation = controller.getClass().getMethod("create", Book.class).getAnnotation(PostMapping.class);
        assertNotNull(annotation);
        System.out.println("success");
    }

    @Test
    void createMethodBookArgumentShouldBeAnnotatedWithRequestBody() throws NoSuchMethodException {
        Annotation[][] annotations = controller.getClass().getMethod("create", Book.class).getParameterAnnotations();
        assertNotNull(annotations);
        assertEquals(1, annotations.length);
        assertEquals("RequestBody", annotations[0][0].annotationType().getSimpleName());
        System.out.println("success");
    }

    @Test
    void createMethodShouldAddNewBookInDatabase() {
        ResponseEntity<Book> bookResponseEntity = controller.create(getSampleBook());
        assertNotNull(bookResponseEntity);
        assertEquals(4,bookResponseEntity.getBody().getId());
        System.out.println("success");
    }

    @Test
    public void createMethodShouldReturn201Created() {
        ResponseEntity<Book> bookResponseEntity = controller.create(getSampleBook());
        assertNotNull(bookResponseEntity);
        assertEquals(HttpStatus.CREATED,bookResponseEntity.getStatusCode());
        System.out.println("success");
    }

    /**
     * I will create a sample book
     * @return a new instance of a book populated with sample data.
     */
    private Book getSampleBook() {
        Book book = new Book();
        book.setTitle("Cloud Native Java: Designing Resilient Systems with Spring Boot, Spring Cloud, and Cloud Foundry");
        book.setAuthor("Josh Long & Kenny Bastani");
        book.setPublisher("OReilly Media, Inc.");
        book.setReleaseDate("September 2019");
        book.setIsbn("9781449374648");
        book.setTopic("Spring Cloud");
        return book;
    }
}
