package com.bookscatalog.controller;

import com.bookscatalog.domain.Author;
import com.bookscatalog.service.AuthorBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class AuthorController {

    @Autowired
    private AuthorBo authorBo;

    @RequestMapping("/author/index")
    public String listAuthors(Map<String, Object> map) {
        map.put("authorList", authorBo.getAllAuthors());
        return "author_list";
    }

    @RequestMapping(value = "/author/addsave", method = RequestMethod.POST)
    public String addAuthorSave(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "author_add";
        } else {
            authorBo.save(author);
            return "redirect:/author/index";
        }
    }

    @RequestMapping("/author/delete/{authorId}")
    public String deleteAuthor(@PathVariable("authorId") int authorId) {
        authorBo.delete(authorBo.findAuthorById((authorId)));
        return "redirect:/author/index";
    }

    @RequestMapping(value = "/author/save", method = RequestMethod.POST)
    public String saveAuthor(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "author_edit";
        } else {
            author.setBooks(authorBo.findAuthorById(author.getId()).getBooks());
            authorBo.update(author);
            return "redirect:/author/edit/" + author.getId();
        }
    }

    @RequestMapping(value = "/author/add/", method = RequestMethod.GET)
    public String addAuthor(ModelMap model) {
        model.addAttribute("author", new Author());
        return "author_add";
    }

    @RequestMapping("/author/edit/{authorId}")
    public String editAuthor(@PathVariable("authorId") int authorId, Map<String, Object> map) {
        Author author = authorBo.findAuthorById(authorId);
        map.put("author", author);
        return "author_edit";
    }
}