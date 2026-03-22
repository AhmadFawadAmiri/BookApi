package com.example.bookapi;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors(){
        return authorService.getAllAuthors();
    }

    @PostMapping
    public Author addAuthor(@RequestBody @Valid Author author){
        return authorService.addAuthor(author);
    }

    @PostMapping("/{id}/books")
    public Book addBookToAuthor(@PathVariable long id, @RequestBody @Valid Book book){
    return authorService.addBookToAuthor(id, book);
    }
    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable long id){
    return authorService.getAuthorById(id);
    }
    @GetMapping("/{id}/books")
    public List<Book> getBooksByAuthor(@PathVariable long id){
        Author author = authorService.getAuthorById(id);
        return author.getBooks();
    }
}
