package com.bookscatalog.service.impl;

import com.bookscatalog.dao.AuthorDAO;
import com.bookscatalog.domain.Author;
import com.bookscatalog.service.AuthorBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorBoImpl implements AuthorBo {
    @Autowired
    private AuthorDAO authorDAO;

    @Transactional
    public void save(Author author) {
        authorDAO.save(author);
    }

    @Transactional
    public void update(Author author) {
        authorDAO.update(author);
    }

    @Transactional
    public void delete(Author author) {
        authorDAO.delete(author);
    }

    @Transactional
    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    @Transactional
    public Author findAuthorById(int id) {
        return authorDAO.findAuthorById(id);
    }
}
