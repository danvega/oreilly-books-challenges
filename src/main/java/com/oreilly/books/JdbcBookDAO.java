package com.oreilly.books;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBookDAO implements BookDAO {

    private List<Book> books;

    private JdbcTemplate jdbcTemplate;

    public JdbcBookDAO(JdbcTemplate jdbcTemplate) {
        this.books = new ArrayList<>();
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Book> rowMapper = (rs,rowNum) -> {
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

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from book", Long.class);
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM BOOK";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<Book> findById(int id) {
        String sql = "SELECT * FROM book where id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql,rowMapper,id));
    }

}
