package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Copy;
import com.codecool.crashbooks.model.CopyStatuses;
import com.codecool.crashbooks.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

public class CopyService {

    @Autowired
    private CopyRepository copyRepository;

    /*private final EntityManagerFactory emf;

    public CopyService(EntityManagerFactory emf) {
        this.emf = emf;
    }*/

    public List<Copy> getAllCopy() {
        /*EntityManager em = emf.createEntityManager();
        List<Copy> copyList = em.createNamedQuery("Copy.getAllCopy", Copy.class).getResultList();
        em.close();
        return copyList;*/
        return copyRepository.findAll();
    }

    public Copy getCopyById(int id) {
        /*EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Copy.getById", Copy.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }*/
        return copyRepository.findOne(id);
    }

    public List<Copy> getCopiesByStatus(CopyStatuses status) {
        /*EntityManager em = emf.createEntityManager();
        List<Copy> copyList = em.createNamedQuery("Copy.getByStatus", Copy.class)
                .setParameter("status", status).getResultList();
        em.close();
        return copyList;*/
        return copyRepository.findByStatus(status);
    }

    private List<Copy> getCopiesByMediaId(int mediaId) {
        /*EntityManager em = emf.createEntityManager();
        List<Copy> copyList = em.createNamedQuery("Copy.getAvailableCopyByMediaId", Copy.class)
                .setParameter("id", mediaId)
                .getResultList();
        em.close();
        return copyList;*/
        return copyRepository.findByMedia_Id(mediaId);
    }

    public Copy getFirstAvailableCopy(int mediaId) {
        return getCopiesByMediaId(mediaId).get(0);
    }

    public void changeCopyStatus(Copy copy, CopyStatuses status) {
        //Copy copy = copyRepository.find(copy.getId()); //TODO if method receives id
        copy.setStatus(status);
        copyRepository.save(copy);
    }

}
