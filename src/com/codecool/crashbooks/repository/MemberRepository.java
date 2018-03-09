package com.codecool.crashbooks.repository;

import com.codecool.crashbooks.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByName(String name);
}
