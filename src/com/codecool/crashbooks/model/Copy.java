package com.codecool.crashbooks.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "media_id")
    private Media media;

    @OneToMany(mappedBy = "copy", fetch = FetchType.LAZY)
    private List<Rent> rents;

    @Enumerated(EnumType.STRING)
    private CopyStatuses status;

    public Copy() {
    }

    public Copy(Media media) {
        this.media = media;
        this.status = CopyStatuses.AVAILABLE;
    }

    public Media getMedia() {
        return media;
    }

    public int getId() {
        return id;
    }

    public CopyStatuses getStatus() {
        return status;
    }

    public void setStatus(CopyStatuses status) {
        this.status = status;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public static Copy create(Media media) {
        return new Copy(media);
    }
}
