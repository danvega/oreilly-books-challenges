package com.oreilly.books;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcBookDAO implements BookDAO {

    private List<Book> books;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertBook;

    public JdbcBookDAO(JdbcTemplate jdbcTemplate) {
        this.books = new ArrayList<>();
        this.jdbcTemplate = jdbcTemplate;
        insertBook = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("book")
                .usingGeneratedKeyColumns("id");
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

    /**
     * I will persist a new instance of a book to the database. The database currently does not auto generate a new id. This means
     * that the incoming request will have the books id included.
     *
     * @param book The new book.
     * @return the new book that was persisted to the database.
     */
    @Override
    public Book create(Book book) {
        return null;
    }

    /**
     * I will update an existing book in the database.
     *
     * @param book The updated book
     * @param id The id of the book being updated.
     * @return The updated book.
     */
    @Override
    public Book update(Book book, int id) {
        return null;
    }

    /**
     * I will remove a book from the database
     *
     * @param id the id of the book being deleted.
     */
    @Override
    public void delete(int id) {

    }

}
