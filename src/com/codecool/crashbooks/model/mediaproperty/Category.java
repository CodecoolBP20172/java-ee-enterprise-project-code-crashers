package com.codecool.crashbooks.model.mediaproperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.List;
@NamedQueries({
        @NamedQuery(name="Category.getById", query="SELECT c FROM Category c WHERE id = :id"),
        @NamedQuery(name="Category.getByName", query="SELECT c FROM Category c WHERE name = :name"),
        @NamedQuery(name="Category.getAllCategory", query="SELECT c FROM Category c")
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

    public Categories getName() {
        return name;
    }

    public static List<Category> getAllCategory(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        List<Category> categoryList = em.createNamedQuery("Category.getAllCategory", Category.class).getResultList();
        em.close();
        return categoryList;
    }

    public static Category getCategoryById(EntityManagerFactory emf, int id){
        EntityManager em = emf.createEntityManager();
        Category category = em.createNamedQuery("Category.getById", Category.class).setParameter("id", id).getSingleResult();
        em.close();
        return category;
    }

    public static Category getCategoryByName(EntityManagerFactory emf, String name){
        EntityManager em = emf.createEntityManager();
        Category category = em.createNamedQuery("Category.getByName", Category.class).setParameter("name", name).getSingleResult();
        em.close();
        return category;
    }

}
