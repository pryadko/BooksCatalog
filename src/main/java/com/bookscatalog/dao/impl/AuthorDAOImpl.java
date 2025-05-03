package com.bookscatalog.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookscatalog.dao.AuthorDAO;
import com.bookscatalog.domain.Author;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
    private static final Logger logger = LoggerFactory.getLogger(AuthorDAOImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Author author) {
        logger.debug("Saving author: {}", author);
        sessionFactory.getCurrentSession().persist(author);
    }

    @Override
    public void update(Author author) {
        logger.debug("Updating author: {}", author);
        sessionFactory.getCurrentSession().merge(author);
    }

    @Override
    public void delete(Author author) {
        logger.debug("Deleting author: {}", author);
        Session session = sessionFactory.getCurrentSession();
        
        // First remove author from all books (many-to-many relationship)
        session.createNativeMutationQuery("DELETE FROM AuthorToBook WHERE authorId = :id")
               .setParameter("id", author.getId())
               .executeUpdate();
        
        // Then delete the author
        session.remove(author);
        session.flush();
    }

    @Override
    public Author findAuthorById(int id) {
        logger.debug("Finding author by id: {}", id);
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Author a LEFT JOIN FETCH a.books WHERE a.id = :id", Author.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<Author> getAllAuthors() {
        logger.debug("Getting all authors");
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Author a LEFT JOIN FETCH a.books", Author.class)
                .getResultList();
    }
}
