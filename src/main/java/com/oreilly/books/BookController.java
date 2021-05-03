package com.oreilly.books;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
class BookController {

    private JdbcBookDAO dao;

    public BookController(JdbcBookDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Book> findAll() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> findOne(@PathVariable int id) {
        return dao.findById(id);
    }

}
