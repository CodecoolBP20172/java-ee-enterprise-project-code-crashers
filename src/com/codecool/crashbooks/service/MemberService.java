package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

@Service
public class MemberService {
/*    private final EntityManagerFactory emf;

    public MemberService(EntityManagerFactory emf) {
        this.emf = emf;
    }*/

    @Autowired
    MemberRepository memberRepository;

    public Member getMemberById(int id) {
/*        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Member.getMemberById", Member.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        return memberRepository.findOne(id);
    }

    public Member getMemberByName(String name) {
/*        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Member.getMemberByName", Member.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        return memberRepository.findByName(name);
    }

    public void saveMember(String name, String password) {
/*        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Member member = new Member(name, password);
        transaction.begin();
        em.persist(member);
        transaction.commit();
        em.close();*/
        Member member = new Member(name, password);
        memberRepository.save(member);
    }
}
