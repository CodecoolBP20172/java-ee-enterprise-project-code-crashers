package com.codecool.crashbooks.model.mediaProperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Author.getById", query = "SELECT a FROM Author a WHERE id = :id"),
        @NamedQuery(name = "Author.getByName", query = "SELECT a FROM Author a WHERE name = :name"),
        @NamedQuery(name = "Author.getAllAuthor", query = "SELECT a FROM Author a")
})
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Media> medias;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
