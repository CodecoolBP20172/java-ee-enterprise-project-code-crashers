package com.codecool.crashbooks.model;

import org.eclipse.jetty.util.annotation.Name;

import javax.persistence.*;
@NamedQueries({
        @NamedQuery(name="AllUsers.getUserById", query = "SELECT u FROM AllUsers u WHERE id = :id"),
        @NamedQuery(name="AllUsers.getUserByName", query="SELECT u FROM AllUsers u WHERE name = :name"),
        @NamedQuery(name="AllUsers.getUsersByMembership", query="SELECT u FROM AllUsers u WHERE membership = :membership"),
        @NamedQuery(name="AllUsers.getAllUser", query="SELECT u FROM AllUsers u")
})
@Entity
public class AllUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Enum<Membership> membership;

    public AllUsers(String name, String password) {
        this.name = name;
        this.password = password;
        //this.membership = Membership.FREE;
    }

    public AllUsers(){}

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

    public static AllUsers getUserById(EntityManagerFactory emf, int id){
        EntityManager em = emf.createEntityManager();
        AllUsers user = em.createNamedQuery("AllUsers.getUserById", AllUsers.class).setParameter("id", id).getSingleResult();
        em.close();
        return user;
    }

    public static AllUsers getUserByName(EntityManagerFactory emf, String name){
        EntityManager em = emf.createEntityManager();
        AllUsers user = em.createNamedQuery("AllUsers.getUserByName", AllUsers.class).setParameter("name", name).getSingleResult();
        em.close();
        return user;
    }

    public static void saveUser(EntityManagerFactory emf, String name, String password){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        AllUsers user = new AllUsers(name, password);
        transaction.begin();
        em.persist(user);
        transaction.commit();
        em.close();
    }

}
