package com.oreilly.books;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataLoaderTest {

    @Autowired
    private DataLoader dataLoader;

    @Autowired
    private BookDAO dao;

    @Test
     void dataLoaderClassShouldContainComponentAnnotation() {
        Component annotation = dataLoader.getClass().getAnnotation(Component.class);
        assertNotNull(annotation);
        System.out.println("success");
    }

    @Test
    public void dataLoaderClassShouldHaveProfileAnnotationWithDevValue() {
        Profile annotation = dataLoader.getClass().getAnnotation(Profile.class);
        assertNotNull(annotation);
        assertEquals(1,annotation.value().length);
        assertEquals("dev",annotation.value()[0]);
        System.out.println("success");
    }

    @Test
    void dataLoaderRunShouldLoad3Books() {
        List<Book> books = dao.findAll();
        assertEquals(3,books.size());
        assertEquals("97 Things Every Java Programmer Should Know", books.get(0).getTitle());
        assertEquals("Spring Boot: Up and Running", books.get(1).getTitle());
        assertEquals("Hacking with Spring Boot 2.3: Reactive Edition", books.get(2).getTitle());
        System.out.println("success");
    }

}
