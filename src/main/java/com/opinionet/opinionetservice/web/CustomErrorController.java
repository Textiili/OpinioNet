package com.opinionet.opinionetservice.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String redirectAtIndexWhenErrorOccurs() {
        return "redirect:/";
    }
    //Päivitä?
}
