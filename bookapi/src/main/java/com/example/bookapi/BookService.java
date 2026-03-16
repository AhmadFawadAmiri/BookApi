package com.example.bookapi;
import org.hibernate.query.criteria.JpaCriteriaUpdate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
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
    public Book getBookById(int id){
        return bookRepository.findById(id).orElse(null);
    }
    public Book updateBook(int id, Book book){
        Book existing = bookRepository.findById(id).orElse(null);
        if(existing != null){
            existing.setTitle(book.getTitle());
            existing.setPrice(book.getPrice());
            return bookRepository.save(existing);
        }
        return null;
    }

    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }

}
