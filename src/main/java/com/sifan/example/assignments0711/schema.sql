create database assignments0711;
use assignments0711;

DROP TABLE IF EXISTS authors;
CREATE TABLE authors (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  PRIMARY KEY(id)
);

DROP TABLE IF EXISTS books;
CREATE TABLE books (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS author_book;
CREATE TABLE author_book (
    id INT unsigned NOT NULL AUTO_INCREMENT,
    author_id INT NOT NULL,
    book_id INT NOT NULL,
    PRIMARY KEY(id),
    KEY author_id (author_id),
    KEY book_id (book_id),
    CONSTRAINT author_book_ibfk_1 FOREIGN KEY (author_id) REFERENCES authors (id) ON UPDATE CASCADE,
    CONSTRAINT author_book_ibfk_2 FOREIGN KEY (book_id) REFERENCES books (id) ON UPDATE CASCADE
);
