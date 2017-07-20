package com.ls.controller;

import com.ls.domain.User;
import com.ls.dto.user.UserDTO;
import com.ls.repository.UserRepository;
import com.ls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO user) {
        User persistedUser = userRepository.findByUsername(user.getUsername());
        if (persistedUser == null) {
            persistedUser = userService.createUser(user);
        } else {
            System.out.print("UserAlready Exists");
        }
        System.out.print(persistedUser);
        return "profilePage";
    }

}
