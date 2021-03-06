package com.codecool.crashbooks.model.mediaProperty;

import com.codecool.crashbooks.model.Media;
import com.codecool.crashbooks.model.Member;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reviewText;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "media_id")
    private Media media;

    public Review(String reviewText, Member member, Media media) {
        this.reviewText = reviewText;
        this.member = member;
        this.media = media;
    }

    public Review() {

    }

    public String getReviewText() {
        return reviewText;
    }

    public Member getMember() {
        return member;
    }
}
