package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Member;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MemberService {


    public Member getMemberById(EntityManagerFactory emf, int id) {
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

    public Member getMemberByName(EntityManagerFactory emf, String name){
        try {
            EntityManager em = emf.createEntityManager();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> cq = cb.createQuery(Member.class);

            Root<Member> memberRoot = cq.from(Member.class);
            cq.where(cb.equal(memberRoot.get("name"), name));

            TypedQuery<Member> query = em.createQuery(cq);
            Member result = query.getSingleResult();

            em.close();
            return result;
        }catch(NoResultException e){
            return null;
        }
    }

    public void saveMember(EntityManagerFactory emf, String name, String password) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Member member = new Member(name, password);
        transaction.begin();
        em.persist(member);
        transaction.commit();
        em.close();
    }
}
