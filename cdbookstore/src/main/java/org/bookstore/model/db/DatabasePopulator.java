package org.bookstore.model.db;

import org.bookstore.ejb.model.BookEJB;
import org.bookstore.model.Book;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
@DataSourceDefinition(name = "java:global/jdbc/defaultPU",
        className = "org.postgresql.ds.PGConnectionPoolDataSource",
        user = "postgres",
        password = "qweasd",
        databaseName = "postgres",
        properties = {"connectionAttributes=;create=true"}
)
public class DatabasePopulator {

    @Inject
    private BookEJB bookEJB;

    private Book eBook;
    private Book paperBack;

    @PostConstruct
    private void populateDB() {
        eBook = new Book();
        eBook.setTitle("Beginning JAVA EE - ePrint");
        eBook.setDescription("Beginning JAVA EE - ePrint");
        eBook.setIsbn("1-8763-9125-7");
        eBook.setNoOfPage(600);
        eBook.setPrice(new Float(30.99));
        eBook.setIllustrations(true);

        paperBack = new Book();
        paperBack.setTitle("Beginning JAVA EE - Paperback");
        paperBack.setDescription("Beginning JAVA EE - Paperback");
        paperBack.setIsbn("1-3452-2345-7");
        paperBack.setNoOfPage(600);
        paperBack.setPrice(new Float(30.99));
        paperBack.setIllustrations(false);

        bookEJB.createBook(eBook);
        bookEJB.createBook(paperBack);
    }

    @PreDestroy
    private void clearDB() {
        bookEJB.deleteBook(eBook);
        bookEJB.deleteBook(paperBack);
    }
}