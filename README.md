# oreilly-books-challenges

Properties

spring.datasource.username=user
spring.datasource.password=password
spring.datasource.name=books
spring.datasource.url=jdbc:h2:mem:books

Schema

```sql
CREATE TABLE book(
    id INT IDENTITY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publisher VARCHAR(100),
    release_date VARCHAR(20),
    isbn VARCHAR(20),
    topic VARCHAR(20)
);
```

Data

```sql
INSERT INTO book(title,author,publisher,release_date,isbn,topic)
VALUES ('97 Things Every Java Programmer Should Know','Kevlin Henney, Trisha Gee','OReilly Media, Inc.','May 2020','9781491952696','Java');
INSERT INTO book(title,author,publisher,release_date,isbn,topic)
VALUES ('Spring Boot: Up and Running','Mark Heckler','OReilly Media, Inc.','February 2021',' 9781492076919','Spring');
INSERT INTO book(title,author,publisher,release_date,isbn,topic)
VALUES ('Hacking with Spring Boot 2.3: Reactive Edition','Greg L. Turnquist','Amazon.com Services LLC','May 2020','B086722L4L','Spring');
```
