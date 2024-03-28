package com.opinionet.opinionetservice.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByReleaseYear(@Param("releaseYear") Integer releaseYear);
}
