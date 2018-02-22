package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.mediaproperty.Copy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

public class CopyService {
    private EntityManagerFactory emf;

    public CopyService(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public List<Copy> getAllCopy() {
        EntityManager em = emf.createEntityManager();
        List<Copy> copyList = em.createNamedQuery("Copy.getAllCopy", Copy.class).getResultList();
        em.close();
        return copyList;
    }

    public Copy getCopyById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Copy copy = em.createNamedQuery("Copy.getById", Copy.class)
                    .setParameter("id", id).getSingleResult();
            return copy;
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Copy> getCopyByStatus(String status) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Copy.getByStatus", Copy.class)
                .setParameter("status", status).getResultList();

    }

    public List<Copy> getCopyByMediaId(int mediaId) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Copy.getAvailableCopyByMediaId", Copy.class)
                .setParameter("id", mediaId)
                .getResultList();
    }

    public Copy getSingleAvailableCopy(int mediaId){
        return getCopyByMediaId(mediaId).get(0);
    }

}
