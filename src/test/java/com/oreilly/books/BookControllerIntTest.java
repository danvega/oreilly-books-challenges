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
        ResponseEntity<Book> entity = null; // TODO: use the test rest template to make a call to /api/books/1
        assertEquals(HttpStatus.NOT_FOUND,null); // TODO: get the actual value of the status code from the entity

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
            assertEquals("97 Things Every Java Programmer Should Know", null); // title
            assertEquals("Kevlin Henney, Trisha Gee",null); // author
            assertEquals("OReilly Media, Inc.",null); // publisher
            assertEquals("May 2020",null); // release date
            assertEquals("9781491952696",null); // isbn
            assertEquals("Java",null); // topic
        }

        System.out.println("success"); // DO NOT REMOVE: NEEDED FOR THE CHALLENGE
    }


    /**
     * Validate that /books returns all books
     */
    @Test
    public void findAllShouldReturnAllBooks() {
        ResponseEntity<Map> entity = testRestTemplate.getForEntity("/books", Map.class);
        assertEquals(HttpStatus.OK,null); // TODO: update the actual value of the status code
        Map<Integer,String> books = null; // TODO: get the response body of the entity
        assertNotNull(books);
        assertEquals(3,0); // TODO: get the actual number of books returned

        System.out.println("success"); // DO NOT REMOVE: NEEDED FOR THE CHALLENGE
    }


}
