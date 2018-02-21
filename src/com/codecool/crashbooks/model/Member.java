package com.codecool.crashbooks.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Membership membership;

    public Member(String name, String password) {
        this.name = name;
        this.password = password;
        this.membership = Membership.FREE;
    }

    public Member() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

}
