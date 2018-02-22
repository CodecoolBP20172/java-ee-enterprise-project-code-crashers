package com.codecool.crashbooks.model.mediaproperty;

import com.codecool.crashbooks.model.Member;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "Rent.getById", query = "SELECT r FROM Rent r WHERE id = :id"),
        @NamedQuery(name = "Rent.getByMemberIdAndStatus", query = "SELECT r FROM Rent r WHERE member_id = :id AND "),
        @NamedQuery(name = "Rent.getAllAuthor", query = "SELECT r FROM Rent r")
})
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

    Date dateStart;
    Date dateEnd;
    Date dateReturned;
    double fine;

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

    public Date getDateStart() {
        return dateStart;
    }

    public void setRentPeriod() {
        this.dateStart = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateStart);
        calendar.add(Calendar.MONTH, 1);
        this.dateEnd = calendar.getTime();
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateReturned() {
        this.dateReturned = new Date();
    }

}
