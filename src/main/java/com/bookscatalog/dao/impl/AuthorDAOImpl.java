package com.bookscatalog.dao.impl;

import com.bookscatalog.dao.AuthorDAO;
import com.bookscatalog.domain.Author;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class AuthorDAOImpl implements AuthorDAO {
    static private final String SQL_DELETE_NULL_BOOK = "DELETE Book FROM Book left outer join  AuthorToBook on Book.id=AuthorToBook.bookId  where AuthorToBook.authorId is null";
    static private final String SQL_DELETE_AUTHOR_TO_BOOK = "DELETE FROM AuthorToBook WHERE AuthorToBook.authorId = ?";
    static private final String SQL_DELETE_AUTHOR = "DELETE FROM Author WHERE Author.id=?";

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Author author) {
        sessionFactory.getCurrentSession().saveOrUpdate(author);
    }

    public void update(Author author) {
        sessionFactory.getCurrentSession().saveOrUpdate(author);
    }

    @SuppressWarnings("unchecked")
    public List<Author> getAllAuthors() {
        return (List<Author>) sessionFactory.getCurrentSession().createQuery("from Author").list();
    }

    public void delete(Author author) {
        int authorId = author.getId();
        sessionFactory.getCurrentSession().createSQLQuery(SQL_DELETE_AUTHOR_TO_BOOK).setParameter(0, authorId).executeUpdate();
        sessionFactory.getCurrentSession().createSQLQuery(SQL_DELETE_AUTHOR).setParameter(0, authorId).executeUpdate();
        sessionFactory.getCurrentSession().createSQLQuery(SQL_DELETE_NULL_BOOK).executeUpdate();
    }
    @SuppressWarnings("unchecked")
    public Author findAuthorById(int id) {
        Author result;
        Query q = sessionFactory.getCurrentSession().createQuery("from Author where id = :id");
        q.setInteger("id", id);
        result = ((Author) q.uniqueResult());
        Hibernate.initialize(result.getBooks());
        return result;
    }

}
