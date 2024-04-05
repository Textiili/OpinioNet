package com.opinionet.opinionetservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.opinionet.opinionetservice.domain.Game;
import com.opinionet.opinionetservice.domain.GameRepository;
import com.opinionet.opinionetservice.domain.Review;
import com.opinionet.opinionetservice.domain.ReviewRepository;
import com.opinionet.opinionetservice.domain.User;
import com.opinionet.opinionetservice.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/reviews/{gameId}")
    public String showReviewList(@PathVariable Long gameId, Model model) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            List<Review> reviews = new ArrayList<>(game.getReviews());
            model.addAttribute("game", game);
            model.addAttribute("reviews", reviews);
            return "reviewlist";
        } else {
            return "error"; // Simplify?
        }
    }

    @GetMapping("/addreview/{gameId}")
    public String showReviewForm(@PathVariable Long gameId, Model model) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            model.addAttribute("game", game);
            model.addAttribute("review", new Review());
            return "reviewform";
        } else {
            return "error"; // Simplify?
        }
    }

    @PostMapping("/reviews/{gameId}")
    public String saveReview(@PathVariable Long gameId, @ModelAttribute Review review) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();

            // Retrieve the currently authenticated user using SecurityContextHolder
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();
            User currentUser = userRepository.findByUsername(currentUserName);

            // Set the user for the review
            review.setUser(currentUser);

            // Set the game for the review
            review.setGame(game);

            // Save the review
            reviewRepository.save(review);

            return "redirect:/reviews/" + gameId;
        } else {
            return "error"; // Handle game not found
        }
    }

    //TODO: Delete joka toimii reviewin kirjottajalle ja muokkaus joka toimii reviewi kirjottajalle!
}
