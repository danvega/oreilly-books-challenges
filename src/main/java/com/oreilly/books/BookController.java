package com.oreilly.books;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book ) {
        return ResponseEntity.status(201).body(dao.create(book));
    }

}
