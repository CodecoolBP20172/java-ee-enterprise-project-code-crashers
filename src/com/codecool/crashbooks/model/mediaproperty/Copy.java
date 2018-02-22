package com.codecool.crashbooks.model.mediaproperty;

import com.codecool.crashbooks.model.Media;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Copy.getById", query = "SELECT a FROM Copy a WHERE id = :id"),
        @NamedQuery(name = "Copy.getByStatus", query = "SELECT a FROM Copy a WHERE status = :status"),
        @NamedQuery(name = "Copy.getAvailableCopyByMediaId", query = "SELECT a FROM Copy a WHERE media_id = :id AND status = AVAILABLE"),
        @NamedQuery(name = "Copy.getAllCopy", query = "SELECT a FROM Copy a")
})
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
    @Column(unique = true)
    private StatusType status;

    public Copy() {
    }

    public Copy(Media media) {
        this.media = media;
        this.status = StatusType.AVAILABLE;
    }


    public int getId() {
        return id;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }
}
