package com.codecool.crashbooks.model.bookproperty;

import com.codecool.crashbooks.model.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> book;

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {
    }
}
