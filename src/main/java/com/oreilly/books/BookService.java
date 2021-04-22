package com.oreilly.books;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class BookService {

    private Map<Integer,Book> books;

    public Map<Integer,Book> findAll() {
        return books;
    }

    public Book findOne(int id) {
        return books.get(id);
    }

    /**
     * This method will be called once after the bean was initialized and add some seed data to the books list.
     */
    @PostConstruct
    private void loadBooks() {
        books = new HashMap<>();

        Book one = new Book(1,
                "97 Things Every Java Programmer Should Know",
                "Kevlin Henney, Trisha Gee",
                "OReilly Media, Inc.",
                "May 2020",
                "9781491952696",
                "Java");
        books.put(1,one);

        Book two = new Book(2,
                "Spring Boot: Up and Running",
                "Mark Heckler",
                "OReilly Media, Inc.",
                "February 2021",
                "9781492076919",
                "Spring");
        books.put(2,two);

        Book three = new Book(3,
                "Hacking with Spring Boot 2.3: Reactive Edition",
                "Greg L. Turnquist",
                "Amazon.com Services LLC",
                "May 2020",
                "B086722L4L",
                "Spring");
        books.put(3,three);
    }

}
