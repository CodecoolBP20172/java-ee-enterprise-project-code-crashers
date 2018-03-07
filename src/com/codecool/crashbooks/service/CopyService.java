package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Copy;
import com.codecool.crashbooks.model.CopyStatuses;
import com.codecool.crashbooks.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyService {

    @Autowired
    private CopyRepository copyRepository;

    public void saveCopy(Copy copy) {
        copyRepository.save(copy);
    }

    public List<Copy> getAllCopy() {
        return copyRepository.findAll();
    }

    public Copy getCopyById(int id) {
        return copyRepository.findOne(id);
    }

    public List<Copy> getCopiesByStatus(CopyStatuses status) {
        return copyRepository.findByStatus(status);
    }

    private List<Copy> getCopiesByMediaId(int mediaId) {
        return copyRepository.findByMedia_Id(mediaId);
    }

    public Copy getFirstAvailableCopy(int mediaId) {
        return getCopiesByMediaId(mediaId).get(0);
    }

    public void changeCopyStatus(Copy copy, CopyStatuses status) {
        copy.setStatus(status);
        copyRepository.save(copy);
    }

}
