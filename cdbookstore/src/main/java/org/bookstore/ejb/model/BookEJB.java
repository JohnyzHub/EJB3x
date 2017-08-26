package org.bookstore.ejb.model;

import org.bookstore.model.Book;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

@Stateless
@LocalBean
public class BookEJB implements BookEJBRemote {

    @Inject
    private EntityManager em;

    @Override
    public List<Book> findBooks() {
        TypedQuery findBooks = em.createNamedQuery(Book.FIND_ALL_BOOKS, Book.class);
        return findBooks.getResultList();
    }

    @Override
    public @NotNull
    Book createBook(@NotNull Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public void deleteBook(@NotNull Book book) {
        em.remove(updateBook(book));

    }

    @Override
    public Book updateBook(@NotNull Book book) {
        return em.merge(book);
    }
}
