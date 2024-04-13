package com.opinionet.opinionetservice.web;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.prepost.PreAuthorize;

import com.opinionet.opinionetservice.domain.Game;
import com.opinionet.opinionetservice.domain.GameRepository;
import com.opinionet.opinionetservice.domain.GenreRepository;
import com.opinionet.opinionetservice.domain.Platform;
import com.opinionet.opinionetservice.domain.PlatformRepository; 

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GenreRepository genreRepository; 

    @Autowired PlatformRepository platformRepository;

    @GetMapping("")
    public String gameList(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "index";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addgame")
    public String addGame(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("platforms", platformRepository.findAll());
        return "gameform";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editgame/{id}")
    public String editGame(@PathVariable("id") Long gameId, Model model) {
        model.addAttribute("game", gameRepository.findById(gameId));
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("platforms", platformRepository.findAll()); 
        return "gameform";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/savegame")
    public String saveGame(@ModelAttribute Game game, @RequestParam("platformIds") Optional<List<Long>> platformIdsOptional) {
        Set<Platform> platforms = new HashSet<>();
        if (platformIdsOptional.isPresent()) {
            List<Long> platformIds = platformIdsOptional.get();
            for (Long platformId : platformIds) {
                platforms.add(platformRepository.findById(platformId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid platform ID: " + platformId)));
            }
            game.setPlatforms(platforms);
            gameRepository.save(game);
        } else {
            if (platformRepository.findByName("undefined") == null) {
                platformRepository.save(new Platform("undefined"));
            }
            Platform platform = platformRepository.findByName("undefined");
            platforms.add(platform);
            game.setPlatforms(platforms);
            gameRepository.save(game);
        }
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deletegame/{id}")
    public String deleteGame(@PathVariable("id") Long gameId) {
        gameRepository.deleteById(gameId);
        return "redirect:/";
    }
    //TODO: Validation
}

