package com.example.bookapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }
    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }
    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id){
        return bookService.getBookById(id);
    }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }
}
