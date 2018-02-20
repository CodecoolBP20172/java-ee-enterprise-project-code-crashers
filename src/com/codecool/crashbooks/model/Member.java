package com.codecool.crashbooks.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Membership membership;

    public Member(String name, String password) {
        this.name = name;
        this.password = password;
        this.membership = Membership.FREE;
    }

    public Member() {
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

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public static Member getMemberById(EntityManagerFactory emf, int id) {
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> memberRoot = cq.from(Member.class);
        cq.where(cb.equal(memberRoot.get("id"), id));

        TypedQuery<Member> query = em.createQuery(cq);
        Member result = query.getSingleResult();

        em.close();
        return result;
    }

    public static Member getMemberByName(EntityManagerFactory emf, String name) {
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> memberRoot = cq.from(Member.class);
        cq.where(cb.equal(memberRoot.get("name"), name));

        TypedQuery<Member> query = em.createQuery(cq);
        Member result = query.getSingleResult();

        em.close();
        return result;
    }

    public static void saveMember(EntityManagerFactory emf, String name, String password) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Member member = new Member(name, password);
        transaction.begin();
        em.persist(member);
        transaction.commit();
        em.close();
    }

}
