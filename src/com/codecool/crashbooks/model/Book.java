package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.bookproperty.Author;
import com.codecool.crashbooks.model.bookproperty.Category;
import com.codecool.crashbooks.model.bookproperty.Genre;

import java.util.Set;

public class Book {
    private int id;
    private String title;
    private Author author;
    private Category category;
    private Set<Genre> genres;
    private String pictureUrl;
    private int year;
    private String description;

    //test constructor, can be deleted any time
    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.pictureUrl = "book_thumb.png";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
