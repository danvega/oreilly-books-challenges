package com.oreilly.books;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    Optional<Book> findById(int id);
    List<Book> findAll();
    long count();
}
