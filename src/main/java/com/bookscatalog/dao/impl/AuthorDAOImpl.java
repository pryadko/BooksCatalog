package com.bookscatalog.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookscatalog.dao.AuthorDAO;
import com.bookscatalog.domain.Author;

@Repository
public class AuthorDAOImpl implements AuthorDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Author author) {
        sessionFactory.getCurrentSession().persist(author);
    }

    public void update(Author author) {
        sessionFactory.getCurrentSession().merge(author);
    }

    private List<Author> authorInitBooks(List<Author> authors) {
        for (Author author : authors)
            Hibernate.initialize(author.getBooks());
        return authors;
    }

    public void delete(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeMutationQuery("delete from AuthorToBook where authorId = :id")
            .setParameter("id", author.getId())
            .executeUpdate();
        session.createNativeMutationQuery("delete from Author where id = :id")
            .setParameter("id", author.getId())
            .executeUpdate();
        session.clear();
    }

    public Author findAuthorById(int id) {
        Author result = sessionFactory.getCurrentSession()
            .createQuery("from Author where id = :id", Author.class)
            .setParameter("id", id)
            .uniqueResult();
        Hibernate.initialize(result.getBooks());
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Author> getAllAuthors() {
        return authorInitBooks(
            sessionFactory.getCurrentSession()
                .createQuery("from Author", Author.class)
                .list()
        );
    }
}
