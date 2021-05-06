package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import(JdbcBookDAO.class)
class JdbcBookDAOTest {

    @Autowired
    JdbcBookDAO dao;

    @Test
    void countShouldReturn3() {
        assertEquals(3,dao.count());
        System.out.println("success");
    }

    @Test
    void findAllShouldReturnAllRecordsFromDatabase() {
        List<Book> books = dao.findAll();
        assertEquals(3,books.size());
        assertEquals("97 Things Every Java Programmer Should Know", books.get(0).getTitle());
        assertEquals("Spring Boot: Up and Running", books.get(1).getTitle());
        assertEquals("Hacking with Spring Boot 2.3: Reactive Edition", books.get(2).getTitle());
        System.out.println("success");
    }

    @Test
    void findByIdShouldReturnOneRecordFromDatabase() {
        Optional<Book> result = dao.findById(1);
        assertTrue(result.isPresent());
        Book book = result.get();
        assertEquals("97 Things Every Java Programmer Should Know",book.getTitle());
        assertEquals("Kevlin Henney, Trisha Gee",book.getAuthor());
        assertEquals("OReilly Media, Inc.",book.getPublisher());
        assertEquals("May 2020",book.getReleaseDate());
        assertEquals("9781491952696",book.getIsbn());
        assertEquals("Java",book.getTopic());
        System.out.println("success");
    }

    /**
     * Task 1: create a new book
     * Hint: The id for book is not being auto generated in the database
     */
    @Test
    void shouldInsertValidBookIntoDatabase() {
        Book book = new Book();
        book.setTitle("Cloud Native Java: Designing Resilient Systems with Spring Boot, Spring Cloud, and Cloud Foundry");
        book.setAuthor("Josh Long & Kenny Bastani");
        book.setPublisher("OReilly Media, Inc.");
        book.setReleaseDate("September 2019");
        book.setIsbn("9781449374648");
        book.setTopic("Spring Cloud");

        Book inserted = dao.create(book);
        assertNotNull(inserted.getId());

        List<Book> all = dao.findAll();
        assertEquals(4,all.size());
        assertEquals("Cloud Native Java: Designing Resilient Systems with Spring Boot, Spring Cloud, and Cloud Foundry",all.get(3).getTitle());
    }

    /**
     * Task 2: update an existing book in the database
     */
    @Test
    void shouldUpdateExistingBookInDatabase() {
        final String newISBN = "12345678910";
        Book book = dao.findById(1).get();
        book.setIsbn(newISBN);
        dao.update(book,1);

        // check updated book in db
        Book updated = dao.findById(1).get();
        assertEquals(newISBN,updated.getIsbn());
    }

    /**
     * Task 3: delete an existing book from the database
     */
    @Test
    void shouldRemoveValidBookFromDatabase() {
        dao.delete(3);
        assertEquals(2,dao.findAll().size());
    }

}
