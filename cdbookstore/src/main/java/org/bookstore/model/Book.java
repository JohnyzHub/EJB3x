package org.bookstore.model;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name = Book.FIND_ALL_BOOKS, query = "SELECT b FROM Book b")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL_BOOKS = "Books.findAllBooks";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String title;

    @Column
    private Float price;

    @Column(length = 2000)
    @Size(max = 2000)
    private String description;

    @Column
    private String isbn;

    @Column
    private Integer noOfPage;

    @Column
    private Boolean illustrations;

    public Book() {
    }

    public Book(String title, Float price, String description, String isbn, Integer noOfPage, Boolean illustrations) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
        this.noOfPage = noOfPage;
        this.illustrations = illustrations;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book other = (Book) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNoOfPage() {
        return noOfPage;
    }

    public void setNoOfPage(Integer noOfPage) {
        this.noOfPage = noOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    @Override
    public String toString() {
        String result = getClass().getSimpleName() + " ";
        if (title != null && !title.trim().isEmpty())
            result += "title: " + title;
        if (price != null)
            result += ", price: " + price;
        if (description != null && !description.trim().isEmpty())
            result += ", description: " + description;
        if (isbn != null && !isbn.trim().isEmpty())
            result += ", isbn: " + isbn;
        if (noOfPage != null)
            result += ", noOfPage: " + noOfPage;
        if (illustrations != null)
            result += ", illustrations: " + illustrations;
        return result;
    }
}