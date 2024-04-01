package com.opinionet.opinionetservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.opinionet.opinionetservice.domain.Game;
import com.opinionet.opinionetservice.domain.GameRepository;
import com.opinionet.opinionetservice.domain.GenreRepository; 

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GenreRepository genreRepository; 

    @GetMapping("/gamelist")
    public String gameList(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "gamelist";
    }

    @GetMapping("/addgame")
    public String addGame(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("genres", genreRepository.findAll()); 
        return "gameform";
    }

    @GetMapping("/editgame/{id}")
    public String editGame(@PathVariable("id") Long gameId, Model model) {
        model.addAttribute("game", gameRepository.findById(gameId));
        model.addAttribute("genres", genreRepository.findAll()); 
        return "gameform";
    }

    @PostMapping("/savegame")
    public String saveGame(@ModelAttribute Game game) {
        gameRepository.save(game);
        return "redirect:/gamelist";
    }

    @GetMapping("/deletegame/{id}")
    public String deleteGame(@PathVariable("id") Long gameId) {
        gameRepository.deleteById(gameId);
        return "redirect:/gamelist";
    }
}

