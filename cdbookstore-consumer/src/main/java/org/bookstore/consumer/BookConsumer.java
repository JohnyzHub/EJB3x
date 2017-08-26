package org.bookstore.consumer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.bookstore.ejb.model.BookEJBRemote;
import org.bookstore.model.Book;

import java.util.List;

public class BookConsumer {

    public static void main(String args[]) throws NamingException {
        Context context = null;
        context = new InitialContext();
        BookEJBRemote bookEJB =
                (BookEJBRemote) context.lookup(
                        "java:global/cdbookstore/" +
                                "BookEJB!org.bookstore.ejb.model.BookEJBRemote");
        List<Book> bookList = bookEJB.findBooks();
        System.out.println("Books List:");
        for (Book book : bookList) {
            System.out.println(book);
        }

        Book book = new Book("My New Book", 23.34f,
                "My New Book Descr", "2345-697-900",
                300, false);
        Book dbBook = bookEJB.createBook(book);
        System.out.println("\nNew Book:" + dbBook);
        bookList = bookEJB.findBooks();
        System.out.println("Books List:");
        for (Book book1 : bookList) {
            System.out.println(book1);
        }
        dbBook.setPrice(35f);
        dbBook = bookEJB.updateBook(dbBook);
        System.out.println("Updated Price Book:" + dbBook);
        bookEJB.deleteBook(dbBook);
        bookList = bookEJB.findBooks();
        System.out.println("Books List:");
        for (Book book2 : bookList) {
            System.out.println(book2);
        }
    }
}
