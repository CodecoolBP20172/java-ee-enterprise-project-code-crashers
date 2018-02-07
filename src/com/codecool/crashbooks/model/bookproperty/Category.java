package com.codecool.crashbooks.model.bookproperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.List;
@NamedQueries({
        @NamedQuery(name="Category.getById", query="SELECT c FROM Category c WHERE id = :id")
})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Categories name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Media> book;

    public Category(Categories name) {
        this.name = name;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public static Category getCategoryById(EntityManagerFactory emf, int id){
        EntityManager em = emf.createEntityManager();
        Category category = em.createNamedQuery("Author.getById", Category.class).setParameter("id", id).getSingleResult();
        em.close();
        return category;
    }
}
