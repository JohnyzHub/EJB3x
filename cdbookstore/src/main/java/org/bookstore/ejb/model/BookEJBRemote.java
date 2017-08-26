package org.bookstore.ejb.model;

import org.bookstore.model.Book;

import javax.ejb.Remote;
import javax.validation.constraints.NotNull;
import java.util.List;

@Remote
public interface BookEJBRemote {

    List<Book> findBooks();

    @NotNull
    Book createBook(@NotNull Book book);

    void deleteBook(@NotNull Book book);

    @NotNull
    Book updateBook(@NotNull Book book);
}
