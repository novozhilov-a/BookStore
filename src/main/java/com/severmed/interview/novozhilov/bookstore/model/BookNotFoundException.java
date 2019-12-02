package com.severmed.interview.novozhilov.bookstore.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long bookId) {
        super("Book with id=" + bookId + " not found");
    }
}
