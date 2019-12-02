package com.severmed.interview.novozhilov.bookstore.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepo extends JpaRepository<Book, Long> {
    Page<Book> findByRackNum(Integer rack_num, Pageable pageable);
    Page<Book> findByLevelNum(Integer level_num, Pageable pageable);
    Page<Book> findByRackNumAndLevelNum(Integer rack_num, Integer level_num, Pageable pageable);
    Page<Book> findByNameContaining(String likeName, Pageable pageable);
}
