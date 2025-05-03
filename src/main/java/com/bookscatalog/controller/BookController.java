package com.bookscatalog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookscatalog.domain.Book;
import com.bookscatalog.service.AuthorBo;
import com.bookscatalog.service.BookBo;

import jakarta.validation.Valid;

@Controller
public class BookController {

    @Autowired
    private BookBo bookBo;
    @Autowired
    private AuthorBo authorBo;

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(List.class, "authors", new CustomCollectionEditor(List.class) {
            protected Object convertElement(Object element) {
                if (element instanceof String) {
                    return authorBo.findAuthorById(Integer.parseInt((String) element));
                }
                return element;
            }
        });
    }

    @RequestMapping("/book/index")
    public String listBooks(Map<String, Object> map) {
        map.put("bookList", bookBo.getAllBooks());
        map.put("findBook", new Book());
        return "book_list";
    }

    @RequestMapping("/author/booksAuthor/{authorId}")
    public String listBooksByAuthors(@PathVariable("authorId") int authorId, Map<String, Object> map) {
        map.put("bookList", bookBo.getBooksByAuthor(authorId));
        return "books_list_by_author";
    }


    @RequestMapping(value = "/book/addsave", method = RequestMethod.POST)
    public String addBookSave(@Valid Book book, BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            map.put("book", book);
            map.put("allAuthors", authorBo.getAllAuthors());
            return "book_add";
        } else {
            bookBo.save(book);
            return "redirect:/book/index";
        }
    }

    @RequestMapping("/book/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") int bookId) {
        bookBo.delete(bookBo.findBookById(bookId));
        return "redirect:/book/index";
    }

    @RequestMapping(value = "/book/save", method = RequestMethod.POST)
    public String saveBook(@Valid Book book, BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            map.put("book", book);
            map.put("allAuthors", authorBo.getAllAuthors());
            return "book_edit";
        } else {
            bookBo.save(book);
            return "redirect:/book/edit/" + book.getId();
        }
    }

    @RequestMapping("/book/add/")
    public String addBook(Map<String, Object> map) {
        Book book = new Book();
        map.put("book", book);
        map.put("allAuthors", authorBo.getAllAuthors());
        return "book_add";
    }

    @RequestMapping("/book/edit/{bookId}")
    public String editBook(@PathVariable("bookId") int bookId, Map<String, Object> map) {
        Book book = bookBo.findBookById(bookId);
        map.put("book", book);
        map.put("allAuthors", authorBo.getAllAuthors());
        return "book_edit";
    }


    @RequestMapping("/book/find")
    public String findBooks(@ModelAttribute("findBook") Book book, Map<String, Object> map) {
        map.put("bookList", bookBo.findBooksByName(book.getName()));
        return "book_list";
    }

}