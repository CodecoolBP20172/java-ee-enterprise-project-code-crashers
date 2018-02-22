package com.codecool.crashbooks.service;


import com.codecool.crashbooks.model.mediaproperty.Rent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;


public class RentService {
    EntityManagerFactory emf;

    public RentService(EntityManagerFactory emf) {
        this.emf = emf;
    }

//    @NamedQuery(name = "Rent.getById", query = "SELECT r FROM Rent r WHERE id = :id"),
//    //@NamedQuery(name = "Rent.getByMemberIdAndStatus", query = "SELECT r FROM Rent r WHERE member_id = :id AND "),
//    @NamedQuery(name = "Rent.getAllAuthor", query = "SELECT r FROM Rent r")

    public Rent getRentById(int id){
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
    public List<Rent> getRentByMemberIdAndStatus(int id, String status){
        EntityManager em = emf.createEntityManager();
        List<Rent> rent = em.createNamedQuery("Rent.getByMemberIdAndStatus", Rent.class)
                .setParameter("id", id).setParameter("status", status).getResultList();
        return rent;
    }

    public List<Rent> getRentByMemberId(int id){
        EntityManager em = emf.createEntityManager();
        List<Rent> rent = em.createNamedQuery("Rent.getByMemberId", Rent.class)
                .setParameter("id", id).getResultList();
        return rent;
    }

    public List<Rent> getRentByCopyId(int id){
        EntityManager em = emf.createEntityManager();
        List<Rent> rent = em.createNamedQuery("Rent.getByCopyId", Rent.class)
                .setParameter("id", id).getResultList();
        return rent;
    }





}
