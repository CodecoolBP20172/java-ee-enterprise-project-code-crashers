package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Integer> {
}
