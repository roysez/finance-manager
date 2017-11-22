package dev.roysez.financemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Model model) {
        model.addAttribute("error", "Something went wrong");
        return "index";

    }
}
