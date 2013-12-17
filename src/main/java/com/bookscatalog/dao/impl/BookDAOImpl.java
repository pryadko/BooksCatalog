package com.bookscatalog.dao.impl;

import com.bookscatalog.dao.BookDAO;
import com.bookscatalog.domain.Book;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class BookDAOImpl implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Book book) {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
    }

    private List<Book> bookInitAuthor(List<Book> books) {
        for (Book book : books)
            Hibernate.initialize(book.getAuthors());
        return books;
    }

    public void update(Book book) {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
    }

    @SuppressWarnings("unchecked")
    public List<Book> getAllBooks() {
        return bookInitAuthor((List<Book>) sessionFactory.getCurrentSession().createQuery("from Book").list());
    }

    public void delete(Book book) {
        sessionFactory.getCurrentSession().delete(book);
    }

    public Book findBookById(int id) {
        Book result;
        Query q = sessionFactory.getCurrentSession().createQuery("from Book where id = :id");
        q.setInteger("id", id);
        result = (Book) q.uniqueResult();
        Hibernate.initialize(result.getAuthors());
        return result;
    }
    @SuppressWarnings("unchecked")
    public List<Book> findBooksByName(String name) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Book where name like :name");
        q.setString("name", "%" + name + "%");
        return bookInitAuthor((List<Book>) q.list());
    }
    @SuppressWarnings("unchecked")
    public List<Book> getBooksByAuthor(int authorId) {
        Query q = sessionFactory.getCurrentSession().createQuery(
                "select b from Book b INNER JOIN b.authors author where author.id =:authorId");
        q.setInteger("authorId", authorId);
        return bookInitAuthor((List<Book>) q.list());
    }

}
