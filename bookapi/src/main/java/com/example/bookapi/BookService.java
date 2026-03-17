package com.example.bookapi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book addBook(BookDTO dto){
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_EXTENDED, "Author not found"));
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setPrice(dto.getPrice());
        book.setAuthor(author);
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
