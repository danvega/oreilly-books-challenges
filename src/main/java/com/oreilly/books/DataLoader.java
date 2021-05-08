package com.oreilly.books;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;


public class DataLoader implements CommandLineRunner {

    private BookDAO dao;

    public DataLoader(BookDAO dao) {
        this.dao = dao;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO: save the 3 books returned from the getBooks() method into the database using the DAO
    }

    /**
     * I will return a list of books that you can use to populate the database with
     * @return
     */
    private List<Book> getBooks() {
        Book one = new Book(
                "97 Things Every Java Programmer Should Know",
                "Kevlin Henney, Trisha Gee",
                "OReilly Media, Inc.",
                "May 2020",
                "9781491952696",
                "Java");

        Book two = new Book(
                "Spring Boot: Up and Running",
                "Mark Heckler",
                "OReilly Media, Inc.",
                "February 2021",
                "9781492076919",
                "Spring");

        Book three = new Book(
                "Hacking with Spring Boot 2.3: Reactive Edition",
                "Greg L. Turnquist",
                "Amazon.com Services LLC",
                "May 2020",
                "B086722L4L",
                "Spring");

        return List.of(one,two,three);
    }

}
