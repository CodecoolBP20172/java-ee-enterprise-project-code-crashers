package com.codecool.crashbooks.model.mediaProperty;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.Member;

import javax.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int stars;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "media_id")
    private Media media;

    public Rating() {
    }

    public Rating(int stars, Member member, Media media) {
        this.stars = stars;
        this.member = member;
        this.media = media;
    }


}
