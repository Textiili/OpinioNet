package com.opinionet.opinionetservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String developer;
    private Integer releaseYear;
    private String description;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Game() {}

    public Game(String title, String developer, Integer releaseYear, String description, Float price, Genre genre) {
        this.title = title;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.description = description;
        this.price = price;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Game{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", developer='" + developer + '\'' +
        ", releaseYear=" + releaseYear +
        ", description='" + description + '\'' +
        ", price=" + price +
        ", genre=" + genre +
        '}';
    }
}
