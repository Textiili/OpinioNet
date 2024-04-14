package com.opinionet.opinionetservice.web;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.opinionet.opinionetservice.domain.Game;
import com.opinionet.opinionetservice.domain.GameRepository;
import com.opinionet.opinionetservice.domain.Genre;
import com.opinionet.opinionetservice.domain.GenreRepository;
import com.opinionet.opinionetservice.domain.Platform;
import com.opinionet.opinionetservice.domain.PlatformRepository; 

@RestController
public class RestServiceController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GenreRepository genreRepository; 

    @Autowired PlatformRepository platformRepository;

    //Rest-toiminnot peleille
    @GetMapping("/api/games")
    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/api/games/{id}")
    public @ResponseBody Optional<Game> getGameById(@PathVariable("id") Long id) {
		return gameRepository.findById(id);
	}

	@PostMapping("/api/games")
	Game newGame(@RequestBody Game newGame) {
        // if (newGame.getGenre() == null) {
        //     if (genreRepository.findByName("undefined") == null) {
        //         genreRepository.save(new Genre("undefined")); 
        //     }
        //     newGame.setGenre(genreRepository.findByName("undefined"));
        // }
        if (newGame.getGenres().isEmpty()) {
            Set<Genre> genres = new HashSet<>();
            if (genreRepository.findByName("undefined") == null) {
                genreRepository.save(new Genre("undefined"));
            }
            Genre genre = genreRepository.findByName("undefined");
            genres.add(genre);
            newGame.setGenres(genres);
        }
        
        if (newGame.getPlatforms().isEmpty()) {
            Set<Platform> platforms = new HashSet<>();
            if (platformRepository.findByName("undefined") == null) {
                platformRepository.save(new Platform("undefined"));
            }
            Platform platform = platformRepository.findByName("undefined");
            platforms.add(platform);
            newGame.setPlatforms(platforms);
        }
		return gameRepository.save(newGame);
	}

	@PutMapping("/api/games/{id}")
	Game editGame(@RequestBody Game editedGame, @PathVariable Long id) {
		editedGame.setId(id);
		return gameRepository.save(editedGame);
	}

    //Rest-toiminnot genreille
    @GetMapping("/api/genres")
    public Iterable<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @GetMapping("/api/genres/{id}")
    public @ResponseBody Optional<Genre> getGenreById(@PathVariable("id") Long id) {
		return genreRepository.findById(id);
	}

	@PostMapping("/api/genres")
	Genre newGenre(@RequestBody Genre newGenre) {
		return genreRepository.save(newGenre);
	}

	@PutMapping("/api/genres/{id}")
	Genre editGenre(@RequestBody Genre editedGenre, @PathVariable Long id) {
		editedGenre.setId(id);
		return genreRepository.save(editedGenre);
	}

    //Rest-toiminnot platformeille
    @GetMapping("/api/platforms")
    public Iterable<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    @GetMapping("/api/platforms/{id}")
    public @ResponseBody Optional<Platform> getPlatformById(@PathVariable("id") Long id) {
		return platformRepository.findById(id);
	}

	@PostMapping("/api/platforms")
	Platform newPlatform(@RequestBody Platform newPlatform) {
		return platformRepository.save(newPlatform);
	}

	@PutMapping("/api/platforms/{id}")
	Platform editPlatform(@RequestBody Platform editedPlatform, @PathVariable Long id) {
		editedPlatform.setId(id);
		return platformRepository.save(editedPlatform);
	}
}

