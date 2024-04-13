package com.opinionet.opinionetservice.domain;

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//TODO: Validation!
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Lob
    private String reviewText;
    
    private Float rating;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonIgnoreProperties("reviews")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Review() {
        this.createdAt = new Date();
    }

    public Review(String reviewText, float rating, User user, Game game) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.user = user;
        this.game = game;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Review{" +
        "id=" + id +
        ", reviewText='" + reviewText + '\'' +
        ", rating=" + rating +
        ", createdAt=" + createdAt +
        ", user=" + user.getUsername() + 
        ", game=" + game +
        '}';
    }
}
