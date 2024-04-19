package com.opinionet.opinionetservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
//TODO: Validation!
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

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
            return "reviewpage";
        } else {
            return "/error"; 
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/review/{gameId}")
    public String showReviewForm(@PathVariable Long gameId, Model model) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            //Tarkistetaan onko käyttäjä vielä arvioinut peliä
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();
            User currentUser = userRepository.findByUsername(currentUserName);
            Optional<Review> existingReview = reviewRepository.findByUserAndGame(currentUser, game);
            if (existingReview.isPresent()) {
                //Jos käyttäjä on jo arvioinut pelin, käyttäjä voi muokata arviotaan
                model.addAttribute("game", game);
                model.addAttribute("review", existingReview.get());
                return "reviewform";
            } else {
                //Jos käyttäjä ei ole arvioinut peliä, hän voi arvioida sen :D
                model.addAttribute("game", game);
                model.addAttribute("review", new Review());
                return "reviewform";
            }
        } else {
            return "/error";
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/reviews/{gameId}")
    public String saveReview(@ModelAttribute Review review, @PathVariable Long gameId) {
        
        Optional<Game> optionalGame = gameRepository.findById(gameId);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();
            User currentUser = userRepository.findByUsername(currentUserName);

            Optional<Review> existingReview = reviewRepository.findByUserAndGame(currentUser, game);
            if (existingReview.isPresent()) {
                // If the user has already left a review for this game, update the existing review
                Review existing = existingReview.get();
                existing.setRating(review.getRating());
                existing.setReviewText(review.getReviewText());
                reviewRepository.save(existing);
            } else {
                // If the user has not left a review for this game, create a new review
                review.setUser(currentUser);
                review.setGame(game);
                reviewRepository.save(review);
            }

            return "redirect:/reviews/" + gameId;
        } else {
            return "/error";
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/deletereview/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId) {
    Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            Long gameId = review.getGame().getId();
            reviewRepository.delete(review);
            return "redirect:/reviews/" + gameId; 
        } else {
            return "/error";
        }
    }

}
