package com.example.bookapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> gelAuthors(){
        return authorService.getAllAuthors();
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @PostMapping("/{id}/books")
    public Book addBookToAuthor(@PathVariable long id, @RequestBody Book book){
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
