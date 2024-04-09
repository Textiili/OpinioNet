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
                new Genre("Sokoban"),
                new Genre("Roguelike Deck-Building"),
                new Genre("Shoot'Em Up"),
                new Genre("Metroidvania"),
                new Genre("Turn-based RPG")
            );
            genres.forEach(genreRepository::save);

            List<Platform> platforms = Arrays.asList(
                new Platform("PC"),
                new Platform("PlayStation 4"),
                new Platform("Xbox One"),
                new Platform("Nintendo Switch")
            );
            platforms.forEach(platformRepository::save);

            List<Game> games = Arrays.asList(
                new Game("Void Stranger", "System Erasure", 2023, "description", 11.79f,"/images/voidStrangerBanner.jpg", genres.get(0), platforms.get(0)),
                new Game("Inscryption", "Daniel Mullins Games", 2021, "description", 19.99f,"/images/inscryptionBanner.jpg", genres.get(1), platforms.get(0)),
                new Game("Cuphead", "Studio MDHR Entertainment Inc.", 2001, "description", 19.99f,"/images/cupheadBanner.jpg", genres.get(2), platforms.get(2)),
                new Game("Hollow Knight", "Team Cherry", 2017, "description", 14.79f,"/images/hollowKnightBanner.jpg", genres.get(3), platforms.get(0)),
                new Game("Undertale", "tobyfox", 2007, "description", 9.99f,"/images/undertaleBanner.jpg", genres.get(4), platforms.get(0))
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
//TODO: Update test data!