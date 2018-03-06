package com.codecool.crashbooks.model.mediaProperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Categories type;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Media> media;

    public Category(Categories type) {
        this.type = type;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public Categories getType() {
        return type;
    }

    public static Category create(Categories type) {
        return new Category(type);
    }
}
