package ir.reza.library.repository;

import ir.reza.library.entity.Book;
import ir.reza.library.util.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {


    @Override
    public Book save(Book entity) {

        return HibernateUtil.executeInTransaction(em -> {

            em.persist(entity);

            return entity;

        });
    }


    @Override
    public Optional<Book> findById(Long id) {

//        return HibernateUtil.executeInTransaction(em -> {
//
//            Book book = em.find(Book.class, id);
//
//            return Optional.ofNullable(book);
//
//        });
        try (EntityManager em = HibernateUtil.getEntityManager()) {
            return Optional.ofNullable(em.find(Book.class, id));
        }
    }


    @Override
    public List<Book> findAll() {

        return HibernateUtil.executeInTransaction(em -> {

            return em.createQuery(
                    "select b from Book b",
                    Book.class
            ).getResultList();

        });
    }


    @Override
    public Book update(Book entity) {

        return HibernateUtil.executeInTransaction(
                em -> em.merge(entity)
        );
    }


    @Override
    public void delete(Long id) {

        HibernateUtil.executeInTransaction(em -> {

            Book book = em.find(Book.class, id);

            if (book != null) {
                em.remove(book);
            }

            return null;
        });
    }
}