package com.codecool.crashbooks.model.mediaproperty;

import com.codecool.crashbooks.model.Member;

import javax.persistence.*;

@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "copy_id")
    private Copy copy;

    public Rent(Member member, Copy copy) {
        this.member = member;
        this.copy = copy;
    }

    public int getId() {
        return id;
    }
    
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

}
