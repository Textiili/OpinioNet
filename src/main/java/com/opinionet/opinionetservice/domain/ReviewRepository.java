package com.opinionet.opinionetservice.domain;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Optional<Review> findByUserAndGame(User user, Game game);
}
