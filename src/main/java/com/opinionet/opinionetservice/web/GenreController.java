package com.opinionet.opinionetservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.opinionet.opinionetservice.domain.Genre;
import com.opinionet.opinionetservice.domain.GenreRepository;

@Controller
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/genrelist")
    public String genreList(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        return "genrelist";
    }

    @GetMapping("/addgenre")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "genreform";
    }

    @GetMapping("/editgenre/{id}")
    public String editGenre(@PathVariable("id") Long genreId, Model model) {
        model.addAttribute("genre", genreRepository.findById(genreId));
        return "genreform";
    }

    @PostMapping("/savegenre")
    public String saveGenre(@ModelAttribute Genre genre) {
        genreRepository.save(genre);
        return "redirect:/genrelist";
    }

    @GetMapping("/deletegenre/{id}")
    public String deleteGenre(@PathVariable("id") Long genreId) {
        genreRepository.deleteById(genreId);
        return "redirect:/genrelist";
    }
}

