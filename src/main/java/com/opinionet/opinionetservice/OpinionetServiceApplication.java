package com.opinionet.opinionetservice;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.opinionet.opinionetservice.domain.Genre;
import com.opinionet.opinionetservice.domain.GenreRepository;
import com.opinionet.opinionetservice.domain.Platform;
import com.opinionet.opinionetservice.domain.PlatformRepository;

@SpringBootApplication
public class OpinionetServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(OpinionetServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OpinionetServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner populateTestData(GenreRepository genreRepository, PlatformRepository platformRepository) {
        return (args) -> {
            List<Genre> genres = Arrays.asList(
                new Genre("Run & Gun"),
                new Genre("Horror"),
                new Genre("Shoot'Em Up"),
                new Genre("MOBA"),
                new Genre("Sokoban")
            );
            genres.forEach(genreRepository::save);

            List<Platform> platforms = Arrays.asList(
                new Platform("PC"),
                new Platform("PlayStation 2"),
                new Platform("Xbox 360"),
                new Platform("Nintendo Switch"),
                new Platform("Android")
            );
            platforms.forEach(platformRepository::save);

            log.info("All genres:");
            for (Genre genre : genreRepository.findAll()) {
                log.info(genre.toString());
            }

            log.info("All platforms:");
            for (Platform platform : platformRepository.findAll()) {
                log.info(platform.toString());
            }
        };
    }
}

