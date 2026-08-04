package ir.reza.library.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "books")
public class Book {


    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;


    private String title;


    private String isbn;


    private Integer publicationYear;


    private BigDecimal price;


    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;


    @Embedded
    private PublisherAddress publisherAddress;



    public Book() {
    }



    public Book(String title,
                String isbn,
                Integer publicationYear,
                BigDecimal price,
                StockStatus stockStatus,
                PublisherAddress publisherAddress) {

        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stockStatus = stockStatus;
        this.publisherAddress = publisherAddress;
    }



    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public Integer getPublicationYear() {
        return publicationYear;
    }


    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public StockStatus getStockStatus() {
        return stockStatus;
    }


    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }


    public PublisherAddress getPublisherAddress() {
        return publisherAddress;
    }


    public void setPublisherAddress(PublisherAddress publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", price=" + price +
                ", stockStatus=" + stockStatus +
                '}';
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof Book book))
            return false;

        return id != null && id.equals(book.id);
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}