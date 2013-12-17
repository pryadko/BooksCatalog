package com.bookscatalog.dao;

import com.bookscatalog.domain.Book;

import java.util.List;

public interface BookDAO {

    public void save(Book book);

    public void update(Book book);

    public List<Book> getAllBooks();

    public void delete(Book book);

    public Book findBookById(int id);

    public List<Book> findBooksByName(String name);

    public List<Book> getBooksByAuthor(int authorId);
}
