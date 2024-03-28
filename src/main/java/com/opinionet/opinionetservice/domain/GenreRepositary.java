package com.opinionet.opinionetservice.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GenreRepositary extends CrudRepository<Genre, Long> {
}
