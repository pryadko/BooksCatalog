package com.bookscatalog.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookscatalog.dao.BookDAO;
import com.bookscatalog.domain.Book;

@Repository
public class BookDAOImpl implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Book book) {
        sessionFactory.getCurrentSession().persist(book);
    }

    private List<Book> bookInitAuthor(List<Book> books) {
        for (Book book : books)
            Hibernate.initialize(book.getAuthors());
        return books;
    }

    public void update(Book book) {
        sessionFactory.getCurrentSession().merge(book);
    }

    @SuppressWarnings("unchecked")
    public List<Book> getAllBooks() {
        return bookInitAuthor(sessionFactory.getCurrentSession().createQuery("from Book", Book.class).list());
    }

    public void delete(Book book) {
        sessionFactory.getCurrentSession().remove(book);
    }

    public Book findBookById(int id) {
        Book result = sessionFactory.getCurrentSession()
            .createQuery("from Book where id = :id", Book.class)
            .setParameter("id", id)
            .uniqueResult();
        Hibernate.initialize(result.getAuthors());
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Book> findBooksByName(String name) {
        return bookInitAuthor(
            sessionFactory.getCurrentSession()
                .createQuery("from Book where name like :name", Book.class)
                .setParameter("name", "%" + name + "%")
                .list()
        );
    }

    @SuppressWarnings("unchecked")
    public List<Book> getBooksByAuthor(int authorId) {
        return bookInitAuthor(
            sessionFactory.getCurrentSession()
                .createQuery("select b from Book b INNER JOIN b.authors author where author.id = :authorId", Book.class)
                .setParameter("authorId", authorId)
                .list()
        );
    }
}
