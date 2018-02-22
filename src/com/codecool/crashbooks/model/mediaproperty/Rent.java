package com.codecool.crashbooks.model.mediaproperty;

import com.codecool.crashbooks.model.Member;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "Rent.getById", query = "SELECT r FROM Rent r WHERE id = :id"),
        @NamedQuery(name = "Rent.getByMemberIdAndStatus", query = "SELECT r FROM Rent r JOIN r.copy c WHERE member_id = :id AND c.status = :status"),
        @NamedQuery(name = "Rent.getByMemberId", query = "SELECT r FROM Rent r WHERE member_id = :id"),
        @NamedQuery(name = "Rent.getByCopyId", query = "SELECT r FROM Rent r WHERE copy_id = :id"),
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

    private Date dateStart;
    private Date dateEnd;
    private Date dateReturned;
    private double fine;

    public Rent() {
    }

    public Rent(Member member, Copy copy) {
        this.member = member;
        this.copy = copy;
        copy.setStatus(StatusType.PENDING);
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setRentPeriod() {
        this.dateStart = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateStart);
        calendar.add(Calendar.MONTH, 1);
        this.dateEnd = calendar.getTime();
        copy.setStatus(StatusType.RENTED);
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateReturned() {
        this.dateReturned = new Date();
        copy.setStatus(StatusType.AVAILABLE);
    }

    public Date getDateReturned() {
        return dateReturned;

    }

    public void setDateManuallytoRented() {
        this.dateStart = new Date();
    }

    public void setDateManuallytoHistory() {
        this.dateStart = new Date();
        this.dateEnd = new Date();
        this.dateReturned = new Date();
    }


}
