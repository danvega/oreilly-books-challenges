package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    /**
     * Task 2: Write a test that covers a scenario where the user hits the wrong api path "/api/books/1"
     * and validates that we return a status code 404(NotFound).
     *
     */
    @Test
    public void invalidPathShouldThrow404NotFound() {
        ResponseEntity<Book> entity = testRestTemplate.getForEntity("/api/books/1", Book.class);
        assertEquals(HttpStatus.NOT_FOUND,entity.getStatusCode());

        System.out.println("success"); // DO NOT REMOVE: NEEDED FOR THE CHALLENGE
    }

    /**
     * Task 3: Validate the actual values from the response body
     */
    @Test
    public void findByIdWithValidIdShouldReturnBook() {
        ResponseEntity<Book> entity = testRestTemplate.getForEntity("/books/1", Book.class);
        assertEquals(HttpStatus.OK,entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());

        Book response = entity.getBody();
        if( response != null ) {
            // TODO: update the assertions with the actual value from the response
            assertEquals("97 Things Every Java Programmer Should Know", response.getTitle()); // title
            assertEquals("Kevlin Henney, Trisha Gee",response.getAuthor()); // author
            assertEquals("OReilly Media, Inc.",response.getPublisher()); // publisher
            assertEquals("May 2020",response.getReleaseDate()); // release date
            assertEquals("9781491952696",response.getIsbn()); // isbn
            assertEquals("Java",response.getTopic()); // topic
        }

        System.out.println("success"); // DO NOT REMOVE: NEEDED FOR THE CHALLENGE
    }


    /**
     * Validate that /books returns all books
     */
    @Test
    public void findAllShouldReturnAllBooks() {
        ResponseEntity<Map> entity = testRestTemplate.getForEntity("/books", Map.class);
        assertEquals(HttpStatus.OK,entity.getStatusCode());
        Map<Integer,String> books = entity.getBody();
        assertNotNull(books);
        assertEquals(3,books.size());

        System.out.println("success"); // DO NOT REMOVE: NEEDED FOR THE CHALLENGE
    }

}
