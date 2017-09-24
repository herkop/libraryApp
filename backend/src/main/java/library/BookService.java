package library;
import library.entity.Book;
import library.EntityServerDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookService {
    @Inject
    SessionFactory sessionFactory;
    Session session = null;

    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();

        return configuration.buildSessionFactory();

    }


    public Book getBook(int id) throws Exception{

            session = getSessionFactory().openSession();
            session.beginTransaction();
            String query = "from Book where id = :bookId";

            Query quer = session.createQuery(query);
            quer.setParameter("bookId", id);
            Book b1 = (Book) quer.list().get(0);
            session.close();
            return b1;

    }

    public List<Book> getBookByTitle(String title) throws Exception{

        List<Book> books = null;
        String query = "from Book where title LIKE ?1";
        EntityServerDao entityServerDao = new EntityServerDao();
        books = entityServerDao.getEntity(query, "%"+title+"%");
        return books;

    }

    public List<Book> getBookByAuthor(String author) throws Exception{

        List<Book> books = null;
        String query = "from Book where author LIKE ?1";
        EntityServerDao entityServerDao = new EntityServerDao();
        books = entityServerDao.getEntity(query, "%"+author+"%");
        return books;

    }

}
