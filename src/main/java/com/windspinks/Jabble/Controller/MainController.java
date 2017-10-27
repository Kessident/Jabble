package com.windspinks.Jabble.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/")
    public String getIndex(Model model, HttpSession session){
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpSession session){
        model.addAttribute("login", "login");
        return "index";
    }

    @GetMapping("/signup")
    public String getSignup(Model model, HttpSession session){
        model.addAttribute("signup", "signup");
        return "index";
    }
}
