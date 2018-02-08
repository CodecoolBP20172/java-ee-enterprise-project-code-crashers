package com.codecool.crashbooks.model;

import javax.persistence.*;
@NamedQueries({
        @NamedQuery(name="getUserById", query = "SELECT u FROM User u WHERE id = :id"),
        @NamedQuery(name="getUserByName", query="SELECT u FROM User u WHERE name = :name"),
        @NamedQuery(name="getUsersByMembership", query="SELECT u FROM User u WHERE membership = :membership"),
        @NamedQuery(name="getAllUser", query="SELECT u FROM User u")
})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Enum<Membership> membership;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.membership = Membership.FREE;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setMembership(Enum<Membership> membership) {
        this.membership = membership;
    }

    public static User getUserById(EntityManagerFactory emf, int id){
        EntityManager em = emf.createEntityManager();
        User user = em.createNamedQuery("getUserById", User.class).setParameter("id", id).getSingleResult();
        em.close();
        return user;
    }

    public static User getUserByName(EntityManagerFactory emf, String name){
        EntityManager em = emf.createEntityManager();
        User user = em.createNamedQuery("getUserByName", User.class).setParameter("name", name).getSingleResult();
        em.close();
        return user;
    }

    public static void saveUser(EntityManagerFactory emf, User user){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        transaction.commit();
        em.close();
    }

}
