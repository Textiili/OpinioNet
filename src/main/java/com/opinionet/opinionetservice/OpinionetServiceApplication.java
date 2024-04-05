package com.opinionet.opinionetservice;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.opinionet.opinionetservice.domain.Game;
import com.opinionet.opinionetservice.domain.GameRepository;
import com.opinionet.opinionetservice.domain.Genre;
import com.opinionet.opinionetservice.domain.GenreRepository;
import com.opinionet.opinionetservice.domain.Platform;
import com.opinionet.opinionetservice.domain.PlatformRepository;
import com.opinionet.opinionetservice.domain.User;
import com.opinionet.opinionetservice.domain.UserRepository;

@SpringBootApplication
public class OpinionetServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(OpinionetServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OpinionetServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner populateTestData(
            GenreRepository genreRepository,
            PlatformRepository platformRepository,
            GameRepository gameRepository,
            UserRepository userRepository,
            Environment environment) {
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

            List<Game> games = Arrays.asList(
                    new Game("Super Mario", "Nintendo", 1985, "description", 9.99f, genres.get(0), platforms.get(3)),
                    new Game("Resident Evil", "Capcom", 1996, "description", 19.99f, genres.get(1), platforms.get(0)),
                    new Game("Halo: Combat Evolved", "Bungie", 2001, "description", 29.99f, genres.get(2), platforms.get(2)),
                    new Game("League of Legends", "Riot Games", 2009, "description", 0f, genres.get(3), platforms.get(0)),
                    new Game("Portal", "Valve", 2007, "description", 14.99f, genres.get(4), platforms.get(0))
            );
            games.forEach(gameRepository::save);

            if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
                User testAdmin = new User("testAdmin", "$2a$12$htE0gmIiZc15h7CBpzdl4OqNRuRGX.6JUBmt5RMX5NE2egNrxF8h2", "ADMIN");
                userRepository.save(testAdmin);
            } else {
                User admin = new User("admin", environment.getProperty("ADMIN_PASSWORD"), "ADMIN");
                userRepository.save(admin);
                User user = new User("user", environment.getProperty("USER_PASSWORD"), "USER");
                userRepository.save(user);
            }
            
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
