package com.sifan.example.assignments0711;


import org.hibernate.Session;

import java.util.List;

public class Queries {

    public static void saveBook(String name){
        Book book = new Book();
        book.setName(name);
        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(book);
        session.close();
    }
    public static void saveAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(author);
        session.close();
    }
    public static void deleteBook(int id) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        session.delete(book);
        session.getTransaction().commit();
        session.close();
    }

    public static void getBookById(int id) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        System.out.println(book.toString());
        session.getTransaction().commit();
        session.close();
    }

    public static void updateJunctionTable(int bookId, int authorId) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Book b = session.get(Book.class, bookId);
        Author a = session.get(Author.class, authorId);
        Author_Book ab = new Author_Book();
        ab.setBook(b);
        ab.setAuthor(a);
        session.persist(ab);
        session.close();
    }

    public static void main(String[] args) {
        saveBook("three-body");
        saveAuthor("liu cixin");
        updateJunctionTable(2,2);
//        deleteBook(1);
    }









}
