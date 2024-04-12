package com.opinionet.opinionetservice.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String redirectToErrorPage(HttpServletRequest request, Model model) {
        Object errorMessage = request.getAttribute("jakarta.servlet.error.message");
        model.addAttribute("errorMessage", errorMessage != null ? errorMessage.toString() : "Unknown error");
        return "error"; //TODO: Fix unknown error
    }
}
