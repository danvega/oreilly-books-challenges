package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcBookDAOTest {

    @Autowired
    JdbcBookDAO dao;

    /**
     * Task 2: find a book by a valid id. The application creates 3 books on load
     * with id's of 1,2 & 3.
     *
     */
    @Test
    public void findByIdThatExists() {
        Optional<Book> book = dao.findById(1);
        assertTrue(null); // TODO: check to make sure the book is present
        assertEquals(1, 0); // TODO: make sure the books id is 1

        System.out.println("success"); // DO NOT REMOVE
    }

    /**
     * Task 3: create a new book
     */
    @Test
    public void createNewBook() {
        String title = "Cloud Native Java: Designing Resilient Systems with Spring Boot, Spring Cloud, and Cloud Foundry";
        String author = "Josh Long & Kenny Bastani";
        String publisher = "OReilly Media, Inc.";
        String releaseDate = "September 2019";
        String isbn = "9781449374648";
        String topic = "Spring Cloud";

        // TODO: create a new book and save it to the database using the DAO.

        // there should be 4 records in the database now
        assertEquals(4, dao.findAll().size());
        System.out.println("success"); // DO NOT REMOVE
    }

    /**
     * Task 4: the count method on the dao should return 3
     */
    @Test
    public void countShouldEqualThree() {
        assertEquals(3, 0); // TODO: call the DAO and get the number of records in the database
        System.out.println("success"); // DO NOT REMOVE
    }

    /**
     * Task 5 find a distinct list of publishers
     */
    @Test
    public void findDistinctListOfPublishers() {
        List<String> distinctPublishers = new ArrayList<>(); // TODO: use dao.findAll() and return a new list of
        // distinct publishers

        List<String> expected = List.of("OReilly Media, Inc.", "Amazon.com Services LLC");
        assertEquals(expected, distinctPublishers);
        System.out.println("success"); // DO NOT REMOVE
    }

    /**
     * Task 6: delete all books in the database
     */
    @Test
    public void deleteAllBooks() {
        int[] bookIds = { 1, 2, 3 };
        // TODO: delete all books for the assertion to pass

        assertEquals(0, dao.findAll().size());
        System.out.println("success"); // DO NOT REMOVE
    }

}
