package com.example.bookapi;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepostory) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepostory;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }
    public Author getAuthorById(long id){
        return authorRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Author not found"));
    }
    public Book addBookToAuthor(long authorId, Book book){
        Author author = getAuthorById(authorId);
        book.setAuthor(author);
        return bookRepository.save(book);
    }
}
