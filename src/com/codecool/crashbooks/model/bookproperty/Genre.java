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

    @Enumerated(EnumType.STRING)
    private Genres name;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> book;

    public Genre(Genres name) {
        this.name = name;
    }

    public Genre() {
    }
}
