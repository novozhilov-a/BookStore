package com.severmed.interview.novozhilov.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String author;
    Integer rackNum;
    @Min(1) @Max(3)
    Integer levelNum;

    public Book(BookSmall smallBook) {
        name = smallBook.getName();
        author = smallBook.getAuthor();
        rackNum = smallBook.getRackNum();
        levelNum = smallBook.getLevelNum();
    }
}
