package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.*;
import com.codecool.crashbooks.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RentService {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    CopyService copyService;

    @Autowired
    MediaService mediaService;

    public void saveRent(Rent rent) {
        rentRepository.save(rent);
    }

    public void createRent(Member member, Copy copy) {
        Rent rent = new Rent(member, copy);
        rentRepository.save(rent);
        copyService.changeCopyStatus(copy, CopyStatuses.PENDING);
    }

    public Rent getRentById(int id) {
        return rentRepository.findOne(id);
    }

    public List<Rent> getRentsByStatus(CopyStatuses status) {
        return rentRepository.findByCopy_Status(status);
    }

    public List<Rent> getRentsByMemberId(int id) {
        return rentRepository.findByMember_Id(id);
    }

    public List<Rent> getPendingRentsByMemberId(int id) {
        return rentRepository.findByMember_IdAndDateStartIsNull(id);
    }

    public List<Rent> getRentedRentsByMemberId(int id) {
        return rentRepository.findByMember_IdAndDateStartIsNotNullAndDateReturnedIsNull(id);
    }

    public List<Rent> getReturnedRentsByMemberId(int id) {
        return rentRepository.findByMember_IdAndDateReturnedIsNotNull(id);
    }

    public List<Rent> getRentsByCopyId(int id) {
        return rentRepository.findByCopy_Id(id);
    }

    public void startRent(Rent rent) {
        rent.setRentPeriod();
        copyService.changeCopyStatus(rent.getCopy(), CopyStatuses.RENTED);
        rentRepository.save(rent);
    }

    public void endRent(Rent rent) {
        rent.setDateReturned();
        copyService.changeCopyStatus(rent.getCopy(), CopyStatuses.AVAILABLE);
        rentRepository.save(rent);
    }

    public String getNextAvailableRentDate(int mediaId) {
        Media media = mediaService.getMediasBy(mediaId);
        Set<Copy> copyList = media.getCopies();
        List<Integer> copyIdList = copyList.stream().map(Copy::getId).collect(Collectors.toList());
        Rent rent = rentRepository.findFirstByCopy_IdInAndDateEndIsNotNullAndDateReturnedIsNullOrderByDateEndAsc(copyIdList);
        if (rent != null) {
            return rent.getDateEnd();
        } else {
            return null;
        }
    }
}
