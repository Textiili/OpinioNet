package com.opinionet.opinionetservice.domain;

import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByName(String nameOfGenre);
}
