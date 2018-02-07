package com.codecool.crashbooks.model.bookproperty;

import com.codecool.crashbooks.model.Book;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Categories name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Book> book;

    public Category(Categories name) {
        this.name = name;
    }

    public Category() {
    }
}
