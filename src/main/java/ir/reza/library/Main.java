package ir.reza.library;

import ir.reza.library.entity.Book;
import ir.reza.library.util.HibernateUtil;

public class Main {

    public static void main(String[] args) {

        HibernateUtil.executeInTransaction(em -> {

            Book book = new Book();

            em.persist(book);

            return null;
        });

    }
}