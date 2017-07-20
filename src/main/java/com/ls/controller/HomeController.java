package com.ls.controller;

import com.ls.domain.User;
import com.ls.dto.user.UserDTO;
import com.ls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new UserDTO());
        return "home";
    }
}
