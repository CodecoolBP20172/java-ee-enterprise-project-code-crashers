package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.memberProperty.Membership;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Rent> rents;
    @Enumerated(EnumType.STRING)
    private Membership membership;

    public Member(String name, String password) {
        this.name = name;
        this.password = password;
        this.membership = Membership.FREE;
    }

    public Member() {
    }

    public static Member create(String name, String password) {
        return new Member(name, password);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

}
