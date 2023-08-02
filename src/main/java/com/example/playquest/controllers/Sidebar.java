package com.example.playquest.controllers;

import com.example.playquest.entities.User;
import com.example.playquest.repositories.UsersRepository;
import com.example.playquest.services.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class Sidebar {

    private final SessionManager sessionManager;
    private final UsersRepository usersRepository;


    @GetMapping(path = "/notifications")
    public String Explore(Model model, HttpServletRequest request) {
        // Check if the user is logged in or has an active session
        if (!sessionManager.isUserLoggedIn(request)) {
            return "redirect:/login";
        }


        Long userId = sessionManager.getUserId(request); // Assuming you have a method to retrieve the userId from the session
        User user = usersRepository.findById(userId).orElse(null); // Assuming you have a UserRepository for querying user details

        model.addAttribute("user", user);

        return "notifications";
    }

    @GetMapping(path = "/settings")
    public String Settings(Model model, HttpServletRequest request) {
        // Check if the user is logged in or has an active session
        if (!sessionManager.isUserLoggedIn(request)) {
            return "redirect:/login";
        }

        Long userId = sessionManager.getUserId(request); // Assuming you have a method to retrieve the userId from the session
        User user = usersRepository.findById(userId).orElse(null); // Assuming you have a UserRepository for querying user details


        model.addAttribute("user", user);

        return "settings";
    }

}
