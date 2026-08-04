package ir.reza.library.service;

import ir.reza.library.entity.Book;
import ir.reza.library.exception.BookNotFoundException;
import ir.reza.library.exception.InvalidBookDataException;
import ir.reza.library.repository.BookRepository;

import java.math.BigDecimal;
import java.util.List;

public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;


    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Book save(Book book) {

        validateBook(book);

        return bookRepository.save(book);
    }


    @Override
    public Book findById(Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException(id)
                );
    }


    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();
    }


    @Override
    public Book update(Book book) {

        if (book.getId() == null) {
            throw new InvalidBookDataException(
                    "Book id is required for update"
            );
        }

        validateBook(book);

        // check existence
        findById(book.getId());

        return bookRepository.update(book);
    }


    @Override
    public void delete(Long id) {

        // check existence
        findById(id);

        bookRepository.delete(id);
    }


    private void validateBook(Book book) {

        if (book == null) {
            throw new InvalidBookDataException(
                    "Book cannot be null"
            );
        }


        if (book.getTitle() == null ||
                book.getTitle().isBlank()) {

            throw new InvalidBookDataException(
                    "Book title is required"
            );
        }


        if (book.getIsbn() == null ||
                book.getIsbn().isBlank()) {

            throw new InvalidBookDataException(
                    "Book ISBN is required"
            );
        }


        if (book.getPrice() == null ||
                book.getPrice().compareTo(BigDecimal.ZERO) < 0) {

            throw new InvalidBookDataException(
                    "Book price must be positive"
            );
        }


        if (book.getStockStatus() == null) {

            throw new InvalidBookDataException(
                    "Book stock status is required"
            );
        }
    }
}