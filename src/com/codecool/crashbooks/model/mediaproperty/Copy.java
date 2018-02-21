package com.codecool.crashbooks.model.mediaproperty;

import com.codecool.crashbooks.model.Media;

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

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private StatusType status;


    public Copy() {
    }

    public Copy(StatusType status) {
        this.status = status;
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

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}
