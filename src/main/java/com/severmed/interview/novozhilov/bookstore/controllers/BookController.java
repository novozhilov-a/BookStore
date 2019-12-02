package com.severmed.interview.novozhilov.bookstore.controllers;

import com.severmed.interview.novozhilov.bookstore.model.Book;
import com.severmed.interview.novozhilov.bookstore.model.BookSmall;
import com.severmed.interview.novozhilov.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * - Получение списка книг по id cтеллажа, либо по номеру уровня, либо по обоим параметрам одновременно
 *
 *
 */
@RestController
public class BookController {
    @Autowired
    BookService bookService;

    //- Добавление книги на определенный стеллаж и уровень(+)
    @PostMapping("/books")
    Book addNewBook(@RequestBody BookSmall smallBook){
        return bookService.addNewBook(smallBook);
    }

    //- Удаление книги по id
    @DeleteMapping("/books/{id}")
    ResponseEntity<?> deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }

    //- Обновление информации по id книги
    @PutMapping("/books/{id}")
    Book updateBook(@RequestBody BookSmall smallBook, @PathVariable Long id){
        return bookService.updateBook(smallBook, id);
    }

    //- Поиск книги в хранилище по ее названию
    @PostMapping("/books/findByBookName")
    Page<Book> searchBooks(@RequestBody BookSmall smallBook,
                           Pageable pageable){
        return bookService.searchBooks(smallBook, pageable);
    }

    //- Получение книги по ее id
    @GetMapping("/books/{id}")
    Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    //- Получение книги по id стелажа
    @GetMapping("/books/rack/{id}")
    Page<Book> getBookByRackId(@PathVariable Integer id, Pageable pageable) {
        return bookService.getBookByRackId(id, pageable);
    }

    //- Получение книги по id уровня
    @GetMapping("/books/level/{id}")
    Page<Book> getBookByLevelId(@PathVariable Integer id, Pageable pageable) {
        return bookService.getBookByLevelId(id, pageable);
    }

    //- Получение книги по обоим параметрам одновременно
    @GetMapping("/books/rack/{rackId}/level/{levelId}")
    Page<Book> getBookByLevelAndRackId(@PathVariable Integer rackId, @PathVariable Integer levelId, Pageable pageable) {
        return bookService.getBookByLevelAndRackId(rackId, levelId, pageable);
    }


}
