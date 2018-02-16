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

    public static Category getCategoryByType(EntityManagerFactory emf, String type){
        EntityManager em = emf.createEntityManager();
        Category category = em.createNamedQuery("Category.getByType", Category.class).setParameter("type", type).getSingleResult();
        em.close();
        return category;
    }

}
