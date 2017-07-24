package com.ls.controller;

import com.ls.domain.User;
import com.ls.dto.user.UserDTO;
import com.ls.repository.UserRepository;
import com.ls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Scope("session")
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new UserDTO());
        return "home";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute String loginUsername, @ModelAttribute String loginPassword, HttpSession session, Model model) {
        User user = userRepository.findByUsername(loginUsername);
        if (user != null) {
            model.addAttribute("loggedInUser", user);
            session.setAttribute("user", user);
            System.out.print(">>>>>>>>>>>>>>>.LOGIN SUCCESSFUL");
            return "profilePage";
        } else {
            System.out.print(">>>>>>>>>>>>>>>UNABLE TO LOGIN");
            return "home";
        }
    }
}
