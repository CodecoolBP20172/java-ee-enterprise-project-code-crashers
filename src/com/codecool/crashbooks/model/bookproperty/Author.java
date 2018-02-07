package com.codecool.crashbooks.model.bookproperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Media> book;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }
}
