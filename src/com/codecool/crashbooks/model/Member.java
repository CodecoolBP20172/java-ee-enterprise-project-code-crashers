package com.codecool.crashbooks.model;

import com.codecool.crashbooks.model.mediaproperty.Rent;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Member.getMemberById", query = "SELECT m FROM Member m WHERE id =:id"),
        @NamedQuery(name = "Member.getMemberByName", query = "SELECT m FROM Member m WHERE name = :name")
})
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
