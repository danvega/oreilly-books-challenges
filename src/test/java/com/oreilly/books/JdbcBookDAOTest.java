package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcBookDAOTest {

    @Autowired
    JdbcBookDAO dao;

    @Test
    void count() {
        assertEquals(3,dao.count());
        System.out.println("success");
    }

    @Test
    void findAll() {
        List<Book> books = dao.findAll();
        assertEquals(3,books.size());
        assertEquals("97 Things Every Java Programmer Should Know", books.get(0).getTitle());
        assertEquals("Spring Boot: Up and Running", books.get(1).getTitle());
        assertEquals("Hacking with Spring Boot 2.3: Reactive Edition", books.get(2).getTitle());
        System.out.println("success");
    }

    @Test
    void findById() {
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

}
