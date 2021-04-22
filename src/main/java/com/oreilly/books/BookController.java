package com.oreilly.books;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/books")
class BookController {

    @GetMapping
    public Map<Integer,Book> findAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable int id) {
        return null;
    }

}
