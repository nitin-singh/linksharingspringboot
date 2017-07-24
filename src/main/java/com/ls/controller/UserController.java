package com.ls.controller;

import com.ls.domain.Resource;
import com.ls.domain.User;
import com.ls.dto.ResponseDTO;
import com.ls.dto.user.UserDTO;
import com.ls.repository.UserRepository;
import com.ls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("session")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO user, HttpSession session, Model model) {
        User persistedUser = userRepository.findByUsername(user.getUsername());
        if (persistedUser == null) {
            persistedUser = userService.createUser(user);
            if (persistedUser != null) {
                model.addAttribute("loggedInUser", persistedUser);
//                session.setAttribute("user", persistedUser);
                System.out.print(">>>>>>>>>>>>>>>.LOGIN SUCCESSFULL");
                return "profilePage";
            }
        } else {
            System.out.print("UserAlready Exists");
        }
        return "home";
    }

//    @RequestMapping(value = "/sanity", method = RequestMethod.GET)
//    public Map sanity() {
//    }
}
