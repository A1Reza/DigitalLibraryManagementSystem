package ir.reza.library.util;

import jakarta.persistence.*;

import java.util.Optional;
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

    //Additional features

    public static <T> T read(Function<EntityManager, T> operation) {
        try (EntityManager em = getEntityManager()) {
            return operation.apply(em);
        }
    }

    public static PersistenceUnitUtil getPersistenceUnitUtil() {
        return getEntityManagerFactory().getPersistenceUnitUtil();
    }

    public static void getState(EntityManager em, Object entity) {

        Object id = getPersistenceUnitUtil().getIdentifier(entity);

        if (em.contains(entity)) {
            System.out.println("MANAGED");
        } else if (id == null) {
            System.out.println("TRANSIENT");
        } else {
            System.out.println("DETACHED");
        }
    }
}