package ir.reza.library.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Function;

public final class HibernateUtil {

    private static final String PERSISTENCE_UNIT = "digital-library-unit";

    private static EntityManagerFactory emf;


    private HibernateUtil() {
    }


    private static synchronized EntityManagerFactory getEntityManagerFactory() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }

        return emf;
    }


    public static EntityManager getEntityManager() {

        return getEntityManagerFactory()
                .createEntityManager();
    }


    public static <T> T executeInTransaction(Function<EntityManager, T> action) {

        EntityManager em = getEntityManager();

        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            T result = action.apply(em);

            transaction.commit();

            return result;


        } catch (RuntimeException e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw e;


        } finally {

            em.close();

        }
    }
}