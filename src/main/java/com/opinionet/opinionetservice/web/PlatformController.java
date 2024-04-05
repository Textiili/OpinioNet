package com.opinionet.opinionetservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.opinionet.opinionetservice.domain.Platform;
import com.opinionet.opinionetservice.domain.PlatformRepository;
import com.opinionet.opinionetservice.domain.GameRepository;
import com.opinionet.opinionetservice.domain.Game;

@Controller
public class PlatformController {

    @Autowired
    private PlatformRepository platformRepository;
    
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/platformlist")
    public String platformList(Model model) {
        model.addAttribute("platforms", platformRepository.findAll());
        return "platformlist";
    }

    @GetMapping("/addplatform")
    public String addPlatform(Model model) {
        model.addAttribute("platform", new Platform());
        return "platformform";
    }

    @GetMapping("/editplatform/{id}")
    public String editPlatform(@PathVariable("id") Long platformId, Model model) {
        model.addAttribute("platform", platformRepository.findById(platformId));
        return "platformform";
    }

    @PostMapping("/saveplatform")
    public String savePlatform(@ModelAttribute Platform platform) {
        platformRepository.save(platform);
        return "redirect:/platformlist";
    }

    @GetMapping("/deleteplatform/{id}")
    public String deletePlatform(@PathVariable("id") Long platformId) {

        Platform platform = platformRepository.findById(platformId).orElse(null);
        if (platform != null) {

            for (Game game : platform.getGames()) {
                game.getPlatforms().remove(platform);
                gameRepository.save(game);
            }
            
            platformRepository.deleteById(platformId);
        }
        return "redirect:/platformlist";
    }
}
