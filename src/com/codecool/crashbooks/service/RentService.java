package com.codecool.crashbooks.service;


import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.Copy;
import com.codecool.crashbooks.model.Rent;
import com.codecool.crashbooks.model.CopyStatuses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;


public class RentService {
    private final EntityManagerFactory emf;
    private final CopyService copyService;

    public RentService(EntityManagerFactory emf, CopyService copyService) {
        this.emf = emf;
        this.copyService = copyService;
    }

    public void createRent(Member member, Copy copy) {
        if (member.getId() != 0) {
            EntityManager em = emf.createEntityManager();

            Rent rent = new Rent(member, copy);
            Copy rentCopy = em.find(Copy.class, copy.getId());
            rentCopy.setStatus(CopyStatuses.PENDING);

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
            return em.createNamedQuery("Rent.getById", Rent.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rent> getRentsByStatus( CopyStatuses status) {
            EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Rent.getByStatus", Rent.class)
                .setParameter("status", status).getResultList();
    }

    public List<Rent> getRentsByMemberId(int id) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Rent.getByMemberId", Rent.class)
                .setParameter("id", id).getResultList();
    }

    public List<Rent> getPendingRentsByMemberId(int id) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Rent.getPendingByMemberId", Rent.class)
                .setParameter("id", id).getResultList();
    }

    public List<Rent> getRentedRentsByMemberId(int id) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Rent.getRentedByMemberId", Rent.class)
                .setParameter("id", id).getResultList();
    }

    public List<Rent> getReturnedRentsByMemberId(int id) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Rent.getReturnedByMemberId", Rent.class)
                .setParameter("id", id).getResultList();
    }

    public List<Rent> getRentsByCopyId(int id) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Rent.getByCopyId", Rent.class)
                .setParameter("id", id).getResultList();
    }

}
