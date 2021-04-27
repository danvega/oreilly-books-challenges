package com.oreilly.books;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
public class BookJdbcTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String RESOURCES_PATH = "src/main/resources";

    RowMapper<Book> rowMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPublisher(rs.getString("publisher"));
        book.setReleaseDate(rs.getString("release_date"));
        book.setIsbn(rs.getString("isbn"));
        book.setTopic(rs.getString("topic"));
        return book;
    };

    /**
     * Validate that schema.sql exists
     */
    @Test
    public void schemaSqlShouldExist() {
        File file = new File(RESOURCES_PATH + "/schema.sql");
        assertTrue(file.exists());
    }

    @Test
    public void dataSqlShouldExist() {
        File file = new File(RESOURCES_PATH + "/data.sql");
        assertTrue(file.exists());
    }

    /**
     * If everything was setup correctly there should be 3 rows in the database.
     */
    @Test
    public void shouldListAllBooks() {
        String sql = "SELECT * FROM BOOK";
        List<Book> books = jdbcTemplate.query(sql,rowMapper);
        assertEquals(3,books.size());
    }

}
