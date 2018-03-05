package com.codecool.crashbooks.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "Rent.getById", query = "SELECT r FROM Rent r WHERE id = :id"),
        @NamedQuery(name = "Rent.getByStatus", query = "SELECT r FROM Rent r JOIN r.copy c WHERE c.status = :status"),
        @NamedQuery(name = "Rent.getPendingByMemberId", query = "SELECT r FROM Rent r WHERE member_id = :id AND datestart IS NULL"),
        @NamedQuery(name = "Rent.getRentedByMemberId", query = "SELECT r FROM Rent r WHERE member_id = :id AND datestart IS NOT NULL AND datereturned IS NULL"),
        @NamedQuery(name = "Rent.getReturnedByMemberId", query = "SELECT r FROM Rent r WHERE member_id = :id AND datereturned IS NOT NULL"),
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

    public String getDateStart() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateStart != null ? dateFormat.format(dateStart) : "";
    }

    public void setRentPeriod() {
        this.dateStart = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateStart);
        calendar.add(Calendar.MONTH, 1);
        this.dateEnd = calendar.getTime();
    }

    public String getDateEnd() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateEnd != null ? dateFormat.format(dateEnd) : "";
    }

    public void setDateReturned() {
        this.dateReturned = new Date();
    }

    public String getDateReturned() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateReturned != null ? dateFormat.format(dateReturned) : "";
    }

    public double getFine() {
        return fine;
    }

    public static Rent create(Member member, Copy copy) {
        return new Rent(member, copy);
    }
}
