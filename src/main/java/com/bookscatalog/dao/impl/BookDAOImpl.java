package com.bookscatalog.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookscatalog.dao.BookDAO;
import com.bookscatalog.domain.Book;

@Repository
public class BookDAOImpl implements BookDAO {
    private static final Logger logger = LoggerFactory.getLogger(BookDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Book book) {
        logger.debug("Saving book: {}", book);
        sessionFactory.getCurrentSession().persist(book);
    }

    @Override
    public void update(Book book) {
        logger.debug("Updating book: {}", book);
        sessionFactory.getCurrentSession().merge(book);
    }

    @Override
    public void delete(Book book) {
        logger.debug("Deleting book: {}", book);
        sessionFactory.getCurrentSession().remove(book);
    }

    @Override
    public List<Book> getAllBooks() {
        logger.debug("Getting all books");
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Book b LEFT JOIN FETCH b.authors", Book.class)
                .getResultList();
    }

    @Override
    public Book findBookById(int id) {
        logger.debug("Finding book by id: {}", id);
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Book b LEFT JOIN FETCH b.authors WHERE b.id = :id", Book.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<Book> findBooksByName(String name) {
        logger.debug("Finding books by name containing: {}", name);
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Book b LEFT JOIN FETCH b.authors WHERE LOWER(b.name) LIKE LOWER(:name)", Book.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    public List<Book> getBooksByAuthor(int authorId) {
        logger.debug("Finding books by author id: {}", authorId);
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.authors a WHERE a.id = :authorId", Book.class)
                .setParameter("authorId", authorId)
                .getResultList();
    }
}
