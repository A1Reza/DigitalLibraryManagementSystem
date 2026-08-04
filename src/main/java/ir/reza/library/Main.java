package ir.reza.library;

import ir.reza.library.entity.Book;
import ir.reza.library.entity.PublisherAddress;
import ir.reza.library.entity.StockStatus;
import ir.reza.library.repository.BookRepository;
import ir.reza.library.repository.BookRepositoryImpl;
import ir.reza.library.service.BookService;
import ir.reza.library.service.BookServiceImpl;

import java.math.BigDecimal;

public class Main {


    public static void main(String[] args) {


        BookRepository bookRepository = new BookRepositoryImpl();

        BookService bookService = new BookServiceImpl(bookRepository);



        PublisherAddress address1 =
                new PublisherAddress(
                        "Tehran",
                        "Valiasr",
                        "123456"
                );


        PublisherAddress address2 =
                new PublisherAddress(
                        "Shiraz",
                        "Zand",
                        "654321"
                );


        PublisherAddress address3 =
                new PublisherAddress(
                        "Mashhad",
                        "Imam Reza",
                        "111222"
                );



        Book book1 = new Book(
                "Clean Code",
                "9780132350884",
                2008,
                new BigDecimal("50.00"),
                StockStatus.IN_STOCK,
                address1
        );


        Book book2 = new Book(
                "Effective Java",
                "9780134685991",
                2018,
                new BigDecimal("70.00"),
                StockStatus.COMING_SOON,
                address2
        );


        Book book3 = new Book(
                "Hibernate Basics",
                "9781111111111",
                2025,
                new BigDecimal("90.00"),
                StockStatus.OUT_OF_STOCK,
                address3
        );



        // Save

        bookService.save(book1);
        bookService.save(book2);
        bookService.save(book3);



        System.out.println("Books saved:");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);



        // Find by id

        Book foundBook =
                bookService.findById(book1.getId())
                        .orElseThrow();



        System.out.println("\nFound book:");
        System.out.println(foundBook);



        // Update

        foundBook.setPrice(new BigDecimal("60.00"));
        foundBook.setStockStatus(StockStatus.OUT_OF_STOCK);


        bookService.update(foundBook);



        System.out.println("\nAfter update:");
        System.out.println(
                bookService.findById(foundBook.getId()).get()
        );



        // Delete

        bookService.delete(book3.getId());



        System.out.println("\nFinal database state:");

        bookService.findAll()
                .forEach(System.out::println);

    }
}