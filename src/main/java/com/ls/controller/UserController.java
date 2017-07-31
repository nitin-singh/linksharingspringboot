package com.ls.controller;

import com.ls.model.User;
import com.ls.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleUserCreateForm(@ModelAttribute("user") User user, BindingResult bindingResult) {
        log.info("Inside user registration action");
        if (bindingResult.hasErrors()) {
            log.info("Inside binding error action");
            return "home";
        }
        try {
            log.info("Calling save User method of service.");
            userService.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "Email already exists");
            return "home";
        }
        return "redirect:/home";
    }
}
