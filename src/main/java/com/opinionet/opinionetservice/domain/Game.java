package com.opinionet.opinionetservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Cannot be blank spaces!")
    @Size(max=60, message = "Max 60 characters!")
    private String title;

    @NotBlank(message = "Cannot be blank spaces!")
    @Size(max=40, message = "Max 40 characters!")
    private String developer;

    @Positive(message = "Year cannot be negative or zero!")
    private Integer releaseYear;

    @Lob
    @Size(max=100000, message = "Max 10000 characters!")
    private String description;

    @PositiveOrZero(message = "Price must be positive or zero!")
    private Float price;

    private String bannerImageUrl;

    private String backgroundImageUrl;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties( {"games","id"})
    @JoinTable(
        name = "game_genre",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @JsonIgnoreProperties("game")
    @OneToMany(
        mappedBy = "game", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties( {"games","id"})
    @JoinTable(
        name = "game_platform",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<Platform> platforms = new HashSet<>();

    public Game() {
    }

    public Game(String title, String developer, Integer releaseYear, String description, Float price) {
        this();
        this.title = title;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.description = description;
        this.price = price;
    }

    // public Game( //Tarpeellinen?
    //     String title, 
    //     String developer, 
    //     Integer releaseYear, 
    //     String description, 
    //     Float price, 
    //     String bannerImageUrl,
    //     String backgroundImageUrl, 
    //     Genre genre
    //     ) 
    // {
    //     this.title = title;
    //     this.developer = developer;
    //     this.releaseYear = releaseYear;
    //     this.description = description;
    //     this.price = price;
    //     this.bannerImageUrl = bannerImageUrl;
    //     this.backgroundImageUrl = backgroundImageUrl;
    //     this. = genre;
    // }

    public Game(
        String title, 
        String developer, 
        Integer releaseYear, 
        String description, 
        Float price, 
        String bannerImageUrl,
        String backgroundImageUrl, 
        Genre genre, 
        Platform platform
        ) 
    {
        this.title = title;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.description = description;
        this.price = price;
        this.bannerImageUrl = bannerImageUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.genres.add(genre);
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

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
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
        "id=" + id + '\'' +
        ", title='" + title + '\'' +
        ", developer='" + developer + '\'' +
        ", releaseYear=" + releaseYear +
        ", description='" + description + '\'' +
        ", price=" + price + '\'' +
        ", bannerImageUrl=" + bannerImageUrl + '\'' +
        ", backgroundImageUrl=" + backgroundImageUrl + '\'' +
        ", genre=" + genres + '\'' +
        ", platforms=" + platforms + 
        '}';
    }
}