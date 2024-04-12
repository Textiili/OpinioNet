package com.opinionet.opinionetservice.web;

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
    @GetMapping("/games")
    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/genre/{id}")
    public @ResponseBody Optional<Game> getGameById(@PathVariable("id") Long id) {
		return gameRepository.findById(id);
	}

	@PostMapping("/games")
	Game newGame(@RequestBody Game newGame) {
		return gameRepository.save(newGame);
	}

	@PutMapping("/games/{id}")
	Game editGame(@RequestBody Game editedGame, @PathVariable Long id) {
		editedGame.setId(id);
		return gameRepository.save(editedGame);
	}

    //Rest-toiminnot genreille
    @GetMapping("/genres")
    public Iterable<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @GetMapping("/genres/{id}")
    public @ResponseBody Optional<Genre> getGenreById(@PathVariable("id") Long id) {
		return genreRepository.findById(id);
	}

	@PostMapping("/genres")
	Genre newGenre(@RequestBody Genre newGenre) {
		return genreRepository.save(newGenre);
	}

	@PutMapping("/genres/{id}")
	Genre editGenre(@RequestBody Genre editedGenre, @PathVariable Long id) {
		editedGenre.setId(id);
		return genreRepository.save(editedGenre);
	}

    //Rest-toiminnot platformeille
    @GetMapping("/platforms")
    public Iterable<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    @GetMapping("/platforms/{id}")
    public @ResponseBody Optional<Platform> getPlatformById(@PathVariable("id") Long id) {
		return platformRepository.findById(id);
	}

	@PostMapping("/platforms")
	Platform newPlatform(@RequestBody Platform newPlatform) {
		return platformRepository.save(newPlatform);
	}

	@PutMapping("/platforms/{id}")
	Platform editPlatform(@RequestBody Platform editedPlatform, @PathVariable Long id) {
		editedPlatform.setId(id);
		return platformRepository.save(editedPlatform);
	}
}

