package ir.reza.library;

import ir.reza.library.util.HibernateUtil;

public class Main {

    public static void main(String[] args) {

        HibernateUtil.getEntityManager();

        System.out.println("Hibernate Started");

    }
}