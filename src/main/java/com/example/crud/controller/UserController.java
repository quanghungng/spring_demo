package com.example.crud.controller;

import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    //Navigate to register user page
    @RequestMapping(value = {"/registerPage"} , method = RequestMethod.GET)
    public String registerPage(Model model){
        model.addAttribute("user",new User());//Used for storing user info
        return "registerPage";
    }
    //Add new user
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user){
        user.setRole("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:home";
    }

}
