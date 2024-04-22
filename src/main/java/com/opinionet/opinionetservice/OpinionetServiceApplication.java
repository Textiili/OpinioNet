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
                new Genre("Turn-based RPG"),
                new Genre("Roguelike")
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
                new Game("Void Stranger", "System Erasure", 2023, "Void Stranger is a 2D sokoban-style puzzle game where every step counts.\nDescend into the forgotten labyrinth teeming with fiendish foes and traps that defy reason. A swift defeat lurks at every corner for those who neither study their surroundings nor think their moves through with care.\nTrust you wits and slowly, surely, you will conquer the mysteries before you.\nYou do remember why you are here, right?\nOr have you lost something?\nSomething very important to you.", 
                11.79f,"/images/voidStrangerBanner.jpg","/images/voidStrangerBackground.jpg", genres.get(0),platforms.get(0)),

                new Game("Inscryption", "Daniel Mullins Games", 2021, "From the creator of Pony Island and The Hex comes the latest mind melting, self-destructing love letter to video games. Inscryption is an inky black card-based odyssey that blends the deckbuilding roguelike, escape-room style puzzles, and psychological horror into a blood-laced smoothie.\nDarker still are the secrets inscribed upon the cards...", 
                19.99f,"/images/inscryptionBanner.jpg","/images/inscryptionBackground.jpg",genres.get(1),platforms.get(0)),

                new Game("Cuphead", "Studio MDHR Entertainment Inc.", 2001, "Cuphead is a classic shooter and action game with a focus on boss fights. Inspired by 1930s cartoons, the game's visuals and sounds have been accurately recreated using period techniques. The game includes hand-drawn animations, backgrounds created with watercolors and original jazz recordings.\nPlay as Cuphead or Mugman (single player or local friend) as you travel through strange worlds, acquire new weapons, learn powerful super moves and find hidden secrets as you try to pay the hell out of your debt!", 
                19.99f,"/images/cupheadBanner.jpg","/images/cupheadBackground.jpg",genres.get(2),platforms.get(2)),

                new Game("Hollow Knight", "Team Cherry", 2017, "Forge your own path in Hollow Knight! An epic action adventure through a vast ruined kingdom of insects and heroes. Explore twisting caverns, battle tainted creatures and befriend bizarre bugs, all in a classic, hand-drawn 2D style.",
                14.79f,"/images/hollowKnightBanner.jpg","/images/hollowKnightBackground.jpg",genres.get(3),platforms.get(0)),

                new Game("Undertale", "tobyfox", 2007, "Welcome to UNDERTALE. In this RPG, you control a human who falls underground into the world of monsters. Now you must find your way out... or stay trapped forever.((Healthy Dog's Warning: Game contains imagery that may be harmful to players with photosensitive epilepsy or similar condition.))\nfeatures:\nKilling is unnecessary: negotiate out of danger using the unique battle system.\nTime your attacks for extra damage, then dodge enemy attacks in a style reminiscent of top-down shooters.\nOriginal art and soundtrack brimming with personality.\nSoulful, character-rich story with an emphasis on humor.\nCreated mostly by one person.\nBecome friends with all of the bosses!\nAt least 5 dogs.\nYou can date a skeleton.\nHmmm... now there are 6 dogs...?\nMaybe you won't want to date the skeleton.\nI thought I found a 7th dog, but it was actually just the 3rd dog.\nIf you play this game, can you count the dogs for me...? I'm not good at it.", 
                9.99f,"/images/undertaleBanner.jpg","/images/undertaleBackground.jpg",genres.get(4), platforms.get(0)),

                new Game("Going Under", "Aggro Crab", 2020, "Going Under is a dungeon crawler exploring the cursed ruins of failed startups. You are an unpaid intern in the dystopian city of Neo-Cascadia. You have to survive the strange dungeons that lurk beneath your company, your weapon the scum found in the corners of the office.", 
                19.99f,"/images/goingUnderBanner.jpg","/images/goingUnderBackground.jpg",genres.get(5), platforms.get(1))
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