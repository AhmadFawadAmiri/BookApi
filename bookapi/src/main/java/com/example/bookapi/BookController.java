package com.example.bookapi;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String authorName,
                                     @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){

        return bookService.searchBooks(title, authorName, page, size);
    }
    @GetMapping("/paged")
    public List<BookDTO> getBooksPaged(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                       @RequestParam(defaultValue = "title") String sortBy, @RequestParam(defaultValue = "asc") String sortDir){
        return bookService.getBooksPaged(page, size, sortBy, sortDir);
    }
    @PostMapping
    public Book addBook(@RequestBody @Valid BookDTO bookDTO){
         return bookService.addBook(bookDTO);
    }
    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id){
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody @Valid BookDTO bookDTO){
        return bookService.updateBook(id, bookDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }
}
