package com.codecool.crashbooks.model.mediaproperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.List;
@NamedQueries({
        @NamedQuery(name="Category.getById", query="SELECT c FROM Category c WHERE id = :id"),
        @NamedQuery(name="Category.getByType", query="SELECT c FROM Category c WHERE type = :type"),
        @NamedQuery(name="Category.getAllCategory", query="SELECT c FROM Category c")
})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private CategoryType type;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Media> book;

    public Category(CategoryType type) {
        this.type = type;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public CategoryType getType() {
        return type;
    }
}
