package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.Copy;
import com.codecool.crashbooks.model.CopyStatuses;
import com.codecool.crashbooks.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopyRepository extends JpaRepository<Copy, Integer> {

    List<Copy> findByStatus(CopyStatuses status);

    List<Copy> findByMedia_Id(int mediaId);
}
