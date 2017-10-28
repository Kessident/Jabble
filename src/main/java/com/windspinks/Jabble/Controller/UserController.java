package com.windspinks.Jabble.Controller;

import com.windspinks.Jabble.Model.User;
import com.windspinks.Jabble.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCrypt;


import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private UserRepo users;

    @Autowired
    public UserController(UserRepo users) {
        this.users = users;
    }

    @GetMapping("/login")
    public String getLogin(HttpSession session){
        if (isLoggedIn(session)) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(String username, String password, HttpSession session, Model model) {
        User attempt = users.findByUsername(username);
        if (attempt == null){
            model.addAttribute("errorMessage", "User does not exist");
            return "login";
        }
        boolean correctPassword = BCrypt.checkpw(password, attempt.getPassword());

        if (correctPassword){
            session.setAttribute("userID", attempt.getUserID());
            session.setAttribute("username", attempt.getUsername());
            return "redirect:/";
        } else {
            model.addAttribute("errorMessage", "Wrong password");
            return "login";
        }

    }

    @GetMapping("/signup")
    public String getSignup(HttpSession session){
        if (isLoggedIn(session)) {
            return "redirect:/";
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignUp(String username, String password, String passwordConfirm, Model model){
        User existing = users.findByUsername(username);
        if (existing == null){
             if (password.equals(passwordConfirm)){
                User newUser = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()));
                users.save(newUser);
                model.addAttribute("errorMessage", "Signed up successfully");
                return "redirect:/login";
             } else {
                 model.addAttribute("errorMessage", "Passwords must match");
                 return "signup";
             }
        } else {
            model.addAttribute("errorMessage", "User already exists");
            return "signup";
        }
    }

    private boolean isLoggedIn(HttpSession session){
        return session.getAttribute("userID") != null;
    }
}
