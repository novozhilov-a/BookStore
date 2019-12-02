package com.severmed.interview.novozhilov.bookstore.service;

import com.severmed.interview.novozhilov.bookstore.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BooksRepo booksRepo;

    public Book addNewBook(BookSmall smallBook) {
        Book book = new Book(smallBook);
        return booksRepo.save(book);
    }

    public ResponseEntity<?> deleteBook(Long id) {
        Book book = booksRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        booksRepo.delete(book);
        return ResponseEntity.ok().build();
    }

    public Book updateBook(BookSmall smallBook, Long id) {
        Book book = booksRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        if (smallBook.getAuthor() != null) {
            book.setAuthor(smallBook.getAuthor());
        }
        if (smallBook.getName() != null) {
            book.setName(smallBook.getName());
        }
        if (smallBook.getRackNum() != null) {
            book.setRackNum(smallBook.getRackNum());
        }
        if (smallBook.getLevelNum() != null) {
            book.setLevelNum(smallBook.getLevelNum());
        }

        return booksRepo.save(book);
    }

    public List<Book> searchBooks(BookSmall smallBook) {
        Book searchBook = new Book();
        searchBook.setName(smallBook.getName());
        return booksRepo.findAll(Example.of(searchBook));
    }

    public Book getBookById(Long id) {
        return booksRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Page<Book> getBookByRackId(Integer id, Pageable pageable) {
        return booksRepo.findByRackNum(id, pageable);
    }

    public Page<Book> getBookByLevelId(Integer id, Pageable pageable) {
        return booksRepo.findByLevelNum(id, pageable);
    }

    public Page<Book> getBookByLevelAndRackId(Integer rackId, Integer levelId, Pageable pageable) {
        return booksRepo.findByRackNumAndLevelNum(rackId, levelId, pageable);
    }
}
