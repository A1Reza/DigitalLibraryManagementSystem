package ir.reza.library.service;

import ir.reza.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {


    Book save(Book book);


    Optional<Book> findById(Long id);


    List<Book> findAll();


    Book update(Book book);


    void delete(Long id);

}