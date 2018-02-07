package com.codecool.crashbooks.model.bookproperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Genres name;

    @ManyToMany(mappedBy = "genres")
    private Set<Media> book;

    public Genre(Genres name) {
        this.name = name;
    }

    public Genre() {
    }
}
