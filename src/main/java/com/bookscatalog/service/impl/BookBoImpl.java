package com.bookscatalog.service.impl;

import com.bookscatalog.dao.BookDAO;
import com.bookscatalog.domain.Book;
import com.bookscatalog.service.BookBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookBoImpl implements BookBo {
    @Autowired
    BookDAO bookDAO;

    @Transactional
    public void save(Book book) {
        bookDAO.save(book);
    }

    @Transactional
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Transactional
    public void delete(Book book) {
        bookDAO.delete(book);
    }

    @Transactional
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Transactional
    public Book findBookById(int id) {
        return bookDAO.findBookById(id);
    }

    @Transactional
    public List<Book> findBooksByName(String name) {
        return bookDAO.findBooksByName(name);
    }

    @Transactional
    public List<Book> getBooksByAuthor(int authorId) {
        return bookDAO.getBooksByAuthor(authorId);
    }
}
