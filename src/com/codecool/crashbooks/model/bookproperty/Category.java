package com.codecool.crashbooks.model.bookproperty;

import com.codecool.crashbooks.model.Book;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Book> book;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }
}
