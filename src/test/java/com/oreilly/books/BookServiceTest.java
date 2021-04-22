package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;

    /**
     * The BookService class should contain the @Service annotation
     * mvn -Dtest=BookServiceTest#serviceClassShouldContainServiceAnnotation test -q
     */
    @Test
    void serviceClassShouldContainServiceAnnotation() {
        Service annotation = bookService.getClass().getAnnotation(Service.class);
        assertNotNull(annotation);
        System.out.println("success");
    }

}
