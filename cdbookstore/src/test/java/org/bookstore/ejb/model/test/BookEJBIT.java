package org.bookstore.ejb.model.test;

import org.bookstore.ejb.model.BookEJB;
import org.bookstore.model.Book;
import org.junit.Test;

import static org.junit.Assert.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookEJBIT {

    @Test
    public void shouldCeateABook() throws Exception {
        Map<String, Object> properties = new HashMap();
        properties.put(EJBContainer.MODULES, new File("target/classes"));

        try (EJBContainer ejbContainer = EJBContainer.createEJBContainer(properties)) {
            Context context = ejbContainer.getContext();
            assertNotNull(context.lookup("java:global/jdbc/defaultPU"));
            Object bookEJBObj = context.lookup("java:global/classes/BookEJB!org.bookstore.ejb.model.BookEJB");
            assertNotNull(bookEJBObj);
            BookEJB bookEJB = (BookEJB) bookEJBObj;
            List<Book> bookList = bookEJB.findBooks();
            assertNotNull(bookList);
            assertEquals(2, bookList.size());
            Book book =
                    new Book("My New Book", 40f, "My New Book Descr",
                            "1-3452-4567-8", 500, false);
            Book dbBook = bookEJB.createBook(book);
            assertNotNull(dbBook);
            bookList = bookEJB.findBooks();
            assertEquals(3, bookList.size());
            assertEquals(dbBook.getDescription(), book.getDescription());
            book.setDescription("My Updated Book Desc");
            dbBook = bookEJB.updateBook(book);
            assertEquals(dbBook.getDescription(), book.getDescription());
            bookEJB.deleteBook(dbBook);
            bookList = bookEJB.findBooks();
            assertEquals(2, bookList.size());
        }
    }
}