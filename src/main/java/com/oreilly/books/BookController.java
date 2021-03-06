package com.oreilly.books;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book create(@RequestBody Book book) {
        return dao.create(book);
    }

    @PutMapping("/{id}")
    public Book update(@RequestBody Book book, @PathVariable int id) {
        return dao.update(book,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        dao.delete(id);
    }
}
