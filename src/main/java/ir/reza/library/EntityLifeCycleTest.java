package ir.reza.library;

import ir.reza.library.entity.Book;
import ir.reza.library.entity.PublisherAddress;
import ir.reza.library.entity.StockStatus;
import ir.reza.library.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;


public class EntityLifeCycleTest {


    public static void main(String[] args) {


        EntityManager em = HibernateUtil.getEntityManager();

        EntityTransaction tx = em.getTransaction();


        try {

            tx.begin();


            // 1. Transient state

            Book book = new Book(
                    "Hibernate Life Cycle",
                    "111111111",
                    2026,
                    new BigDecimal("100"),
                    StockStatus.IN_STOCK,
                    new PublisherAddress(
                            "Berlin",
                            "Main Street",
                            "12345"
                    )
            );


            System.out.println("Before persist:");
            System.out.println(book);



            // 2. Managed state

            em.persist(book);


            System.out.println("\nAfter persist:");
            System.out.println(book);



            // 3. Modify while Managed

            book.setPrice(new BigDecimal("120"));

            System.out.println("\nChanged price while managed");



            // 4. Detach

            em.detach(book);


            System.out.println("\nEntity detached");



            // 5. Modify while Detached

            book.setPrice(new BigDecimal("150"));

            System.out.println("Changed price while detached");



            // 6. Commit

            tx.commit();


            System.out.println("\nTransaction committed");



            /*
             * At this point:
             *
             * 120 was saved
             * 150 was NOT saved
             *
             */



            // 7. Make Managed again

            tx.begin();


            Book managedBook = em.merge(book);



            // 8. Final change

            managedBook.setPrice(new BigDecimal("200"));


            tx.commit();



            System.out.println("\nFinal update saved");



        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();

        }

    }
}