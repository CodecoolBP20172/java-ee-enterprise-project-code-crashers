package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Copy;
import com.codecool.crashbooks.model.CopyStatuses;
import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.Rent;
import com.codecool.crashbooks.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    CopyService copyService;

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
}
