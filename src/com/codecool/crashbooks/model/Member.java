package com.codecool.crashbooks.model;

import javax.persistence.*;
@NamedQueries({
        @NamedQuery(name="Member.getMemberById", query = "SELECT u FROM Member u WHERE id = :id"),
        @NamedQuery(name="Member.getMemberByName", query="SELECT u FROM Member u WHERE name = :name"),
        @NamedQuery(name="Member.getMembersByMembership", query="SELECT u FROM Member u WHERE membership = :membership"),
        @NamedQuery(name="Member.getAllMember", query="SELECT u FROM Member u")
})
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Enum<Membership> membership;

    public Member(String name, String password) {
        this.name = name;
        this.password = password;
        //this.membership = Membership.FREE;
    }

    public Member(){}

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

    public static Member getMemberById(EntityManagerFactory emf, int id){
        EntityManager em = emf.createEntityManager();
        Member member = em.createNamedQuery("Member.getMemberById", Member.class).setParameter("id", id).getSingleResult();
        em.close();
        return member;
    }

    public static Member getMemberByName(EntityManagerFactory emf, String name){
        EntityManager em = emf.createEntityManager();
        Member member = em.createNamedQuery("Member.getMemberByName", Member.class).setParameter("name", name).getSingleResult();
        em.close();
        return member;
    }

    public static void saveMember(EntityManagerFactory emf, String name, String password){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Member member = new Member(name, password);
        transaction.begin();
        em.persist(member);
        transaction.commit();
        em.close();
    }

}
