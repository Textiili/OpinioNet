package com.opinionet.opinionetservice.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

//TODO: Validation
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

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "game_platform",
               joinColumns = @JoinColumn(name = "game_id"),
               inverseJoinColumns = @JoinColumn(name = "platform_id"))
    private Set<Platform> platforms = new HashSet<>();

    public Game() {
        this.genre = new Genre("-");
        this.platforms.add(new Platform("-"));
    }

    public Game(String title, String developer, Integer releaseYear, String description, Float price) {
        this.title = title;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.description = description;
        this.price = price;
        this.genre = new Genre("-");
        this.platforms.add(new Platform("-"));
    }

    public Game(String title, String developer, Integer releaseYear, String description, Float price, Genre genre, Platform platform) {
        this.title = title;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.description = description;
        this.price = price;
        this.genre = genre;
        this.platforms.add(platform);
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

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
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
        ", platforms=" + platforms +
        '}';
    }
}
