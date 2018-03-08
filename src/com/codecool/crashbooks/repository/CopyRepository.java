package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.Copy;
import com.codecool.crashbooks.model.CopyStatuses;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CopyRepository extends JpaRepository<Copy, Integer> {

    List<Copy> findByStatus(CopyStatuses status);

    @Transactional
    List<Copy> findByMedia_Id(int mediaId);
}
