package com.severmed.interview.novozhilov.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class BookSmall {
    String name;
    String author;
    @NotNull
    Integer rackNum;
    @Min(1) @Max(3)
    Integer levelNum;
}
