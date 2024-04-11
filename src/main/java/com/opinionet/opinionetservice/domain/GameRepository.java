package com.opinionet.opinionetservice.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByReleaseYear(@Param("releaseYear") Integer releaseYear);
}
