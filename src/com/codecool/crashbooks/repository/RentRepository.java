package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.CopyStatuses;
import com.codecool.crashbooks.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Integer> {
    List<Rent> findByMember_Id(int memberId);

    List<Rent> findByMember_IdAndDateStartIsNull(int memberId);

    List<Rent> findByMember_IdAndDateStartIsNotNullAndDateReturnedIsNull(int memberId);

    List<Rent> findByMember_IdAndDateReturnedIsNotNull(int memberId);

    List<Rent> findByCopy_Id(int copyId);

    List<Rent> findByCopy_Status(CopyStatuses status);

    Rent findFirstByCopy_IdInAndDateEndIsNotNullAndDateReturnedIsNullOrderByDateEndAsc(List<Integer> copyIdList);

}
