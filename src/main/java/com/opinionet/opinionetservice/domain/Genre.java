// package com.opinionet.opinionetservice.domain;

// import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;

// @Entity
// public class Genre {
//     @Id
//     @GeneratedValue(strategy=GenerationType.AUTO)
//     private Long id;

//     @NotBlank(message = "Cannot be blank spaces!")
//     @Size(max=40, message= "Max 40 characters!")
//     private String name;

//     @JsonIgnore
//     @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
//     private List<Game> games;

//     public Genre() {}

//     public Genre(String name) {
//         this.name = name;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public List<Game> getGames() {
//         return games;
//     }

//     public void setGames(List<Game> games) {
//         this.games = games;
//     }

//     @Override
//     public String toString() {
//         return "Genre [id=" + id + ", name=" + name + "]";
//     }
// }
package com.opinionet.opinionetservice.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Cannot be blank spaces!")
    @Size(max=40, message= "Max 40 characters!")
    private String name;

    @ManyToMany(mappedBy = "genres", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JsonIgnore
    private Set<Game> games = new HashSet<>();

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Genre [id=" + id + ", name=" + name + "]";
    }
}
