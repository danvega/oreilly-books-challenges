package com.oreilly.books;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    Optional<Book> findById(int id);
    List<Book> findAll();
    long count();
    Book create(Book book);
    Book update(Book book, int id);
    void delete(int id);
}
