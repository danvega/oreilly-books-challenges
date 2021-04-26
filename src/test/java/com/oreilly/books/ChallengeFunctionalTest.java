package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ChallengeFunctionalTest {

    /**
     * Validate that the BookControllerIntTest has the @SpringBootTest annotation along with a RANDOM_PORT webEnvironment.
     *
     * mvn -Dtest=ChallengeFunctionalTest#bookControllerIntTestShouldHaveSpringBootTestAnnotation test -q
     */
    @Test
    public void bookControllerIntTestShouldHaveSpringBootTestAnnotation() {
        BookControllerIntTest sut = new BookControllerIntTest();
        SpringBootTest annotation = sut.getClass().getAnnotation(SpringBootTest.class);
        assertNotNull(annotation);
        assertEquals("RANDOM_PORT",annotation.webEnvironment().name());
        System.out.println("success");
    }

}
