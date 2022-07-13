package com.sifan.example.assignments0711;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class MySessionFactory {
    private static final SessionFactory sessionFactory;
    static {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }



}
