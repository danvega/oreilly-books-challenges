package com.oreilly.books;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ChallengeSpringBootTest {

    @Test
    public void jdbcBookDaoTestShouldHaveTransactionalAnnotation() {
        JdbcBookDAOTest sut = new JdbcBookDAOTest();
        Transactional annotation = sut.getClass().getAnnotation(Transactional.class);
        assertNotNull(annotation);
        System.out.println("success");
    }

}
