package com.opinionet.opinionetservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import com.opinionet.opinionetservice.domain.User;
import com.opinionet.opinionetservice.domain.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userlist")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userlist";
    }

    @PreAuthorize("hasAuthority('ADMIN')") //TODO!
    @GetMapping("/edituser/{id}")
    public String editUser(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("user", userRepository.findById(userId));
        return "userform";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/userlist";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute() User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            if (userRepository.findByUsername(user.getUsername()) != null) {
                bindingResult.rejectValue("username", "error.user", "Username already exists");
                return "register";
            }

            String encodedPassword = passwordEncoder.encode(user.getPasswordHash());
            user.setPasswordHash(encodedPassword);
            user.setRole("USER");
            userRepository.save(user);
            return "login"; 
        }
    }

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}

