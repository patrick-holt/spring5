package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //Spring detects this as a Bean and create the class for us (in Spring MVC)
public class BookController {

    private BookRepository bookRepository;

    //Spring automatically injects the repository when it sees this
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model) { //Pass in instance of the model
        model.addAttribute("books", bookRepository.findAll()); //Add attribute called books, which is the list of books
        return "books"; //Associate the return with view called books (for thymeleaf)
    }

}
