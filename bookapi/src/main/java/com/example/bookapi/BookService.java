package com.example.bookapi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    public List<BookDTO> getBooksPaged(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookRepository.findAll(pageable);
        return bookPage.stream()
                   .map(book -> new BookDTO(book.getId(),book.getTitle(),book.getPrice(), book.getAuthor().getId()))
                   .collect(Collectors.toList());

    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book addBook(BookDTO dto){
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setPrice(dto.getPrice());
        book.setAuthor(author);
        return bookRepository.save(book);
    }
    public Book getBookById(long id){
        return bookRepository.findById(id).orElse(null);
    }
    public Book updateBook(long id, BookDTO dto){
        Book existing = bookRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        existing.setTitle(dto.getTitle());
        existing.setPrice(dto.getPrice());
        return bookRepository.save(existing);
    }

    public void deleteBook(long id){
        bookRepository.deleteById(id);
    }

}
