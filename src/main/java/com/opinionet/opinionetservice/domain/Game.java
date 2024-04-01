package com.opinionet.opinionetservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    public Game() {}

    public Game(String title, String developer, Integer releaseYear, String description, Float price) {
        this.title = title;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.description = description;
        this.price = price;
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

    @Override
    public String toString() {
        return "Game{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", developer='" + developer + '\'' +
        ", releaseYear=" + releaseYear +
        ", description='" + description + '\'' +
        ", price=" + price +
        '}';
    }
}
