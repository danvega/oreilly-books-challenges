package com.oreilly.books;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Book> create(@Valid @RequestBody Book book ) {
        return ResponseEntity.status(201).body(dao.create(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Book book, @PathVariable int id) {
        dao.update(book,id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(int id) {
        dao.delete(id);
    }

}
