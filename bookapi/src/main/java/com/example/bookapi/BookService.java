package com.example.bookapi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book addBook(Book book){
        return bookRepository.save(book);
    }
    public Book getBookById(long id){
        return bookRepository.findById(id).orElse(null);
    }
    public Book updateBook(long id, Book book){
        Book existing = bookRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        existing.setTitle(book.getTitle());
        existing.setPrice(book.getPrice());
        return bookRepository.save(existing);
    }

    public void deleteBook(long id){
        bookRepository.deleteById(id);
    }

}
