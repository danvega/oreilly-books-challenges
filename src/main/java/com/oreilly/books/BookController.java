package com.oreilly.books;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/books")
class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Map<Integer,Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable int id) {
        return bookService.findOne(id);
    }

}
