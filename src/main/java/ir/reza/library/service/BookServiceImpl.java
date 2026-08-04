package ir.reza.library.service;

import ir.reza.library.entity.Book;
import ir.reza.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;


    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }



    @Override
    public Book save(Book book) {

        return bookRepository.save(book);

    }


    @Override
    public Optional<Book> findById(Long id) {

        return bookRepository.findById(id);

    }


    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();

    }


    @Override
    public Book update(Book book) {

        return bookRepository.update(book);

    }


    @Override
    public void delete(Long id) {

        bookRepository.delete(id);

    }
}