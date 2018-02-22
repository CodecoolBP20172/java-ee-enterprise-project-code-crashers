package com.codecool.crashbooks.service;


import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.mediaproperty.Copy;
import com.codecool.crashbooks.model.mediaproperty.Rent;
import com.codecool.crashbooks.model.mediaproperty.StatusType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;


public class RentService {
    EntityManagerFactory emf;
    CopyService copyService;

    public RentService(EntityManagerFactory emf, CopyService copyService) {
        this.emf = emf;
        this.copyService = copyService;
    }

    public void createNewRent(Member member, Copy copy) {
        if (member.getId() != 0) {
            copyService.updateCopyStatus(copy, StatusType.PENDING);
            EntityManager em = emf.createEntityManager();
            Rent rent = new Rent(member, copy);
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(rent);
            transaction.commit();
            em.close();
        }
    }

    public Rent getRentById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Rent rent = em.createNamedQuery("Rent.getById", Rent.class)
                    .setParameter("id", id).getSingleResult();
            return rent;
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rent> getRentByMemberIdAndStatus(int id, StatusType status) {
        EntityManager em = emf.createEntityManager();
        List<Rent> rent = em.createNamedQuery("Rent.getByMemberIdAndStatus", Rent.class)
                .setParameter("id", id).setParameter("status", status).getResultList();
        return rent;
    }

    public List<Rent> getRentByMemberId(int id) {
        EntityManager em = emf.createEntityManager();
        List<Rent> rent = em.createNamedQuery("Rent.getByMemberId", Rent.class)
                .setParameter("id", id).getResultList();
        return rent;
    }

    public List<Rent> getPendingByMemberId(int id) {
        EntityManager em = emf.createEntityManager();
        List<Rent> rent = em.createNamedQuery("Rent.getPendingByMemberId", Rent.class)
                .setParameter("id", id).getResultList();
        return rent;
    }

    public List<Rent> getRentedByMemberId(int id) {
        EntityManager em = emf.createEntityManager();
        List<Rent> rent = em.createNamedQuery("Rent.getRentedByMemberId", Rent.class)
                .setParameter("id", id).getResultList();
        return rent;
    }

    public List<Rent> getHistoryByMemberId(int id) {
        EntityManager em = emf.createEntityManager();
        List<Rent> rent = em.createNamedQuery("Rent.getHistoryByMemberId", Rent.class)
                .setParameter("id", id).getResultList();
        return rent;
    }

    public List<Rent> getRentByCopyId(int id) {
        EntityManager em = emf.createEntityManager();
        List<Rent> rent = em.createNamedQuery("Rent.getByCopyId", Rent.class)
                .setParameter("id", id).getResultList();
        return rent;
    }

}