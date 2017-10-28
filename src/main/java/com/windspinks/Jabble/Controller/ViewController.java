package com.windspinks.Jabble.Controller;

import com.windspinks.Jabble.Model.Message;
import com.windspinks.Jabble.Repositories.MessageRepo;
import com.windspinks.Jabble.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    private UserRepo users;
    private MessageRepo messages;

    @Autowired
    public ViewController(UserRepo users, MessageRepo messages) {
        this.users = users;
        this.messages = messages;
    }

    @GetMapping("/")
    public String getIndex(Model model, HttpSession session){
        List<Message> allMessages = new ArrayList<>();
        messages.findAll().forEach(allMessages::add);

        model.addAttribute("jabs", allMessages);
        return "index";
    }



    private boolean isLoggedIn(HttpSession session){
        return session.getAttribute("userID") != null;
    }
}
