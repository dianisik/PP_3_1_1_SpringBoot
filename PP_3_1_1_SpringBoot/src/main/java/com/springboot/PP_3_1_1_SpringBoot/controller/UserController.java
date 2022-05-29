package com.springboot.PP_3_1_1_SpringBoot.controller;

import com.springboot.PP_3_1_1_SpringBoot.model.User;
import com.springboot.PP_3_1_1_SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String listUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/users/new")
    public String newUser(@ModelAttribute User user) {
        return "new";
    }

    @PostMapping(value = "/userAdd")
    public String addUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/edit";
    }

    @PostMapping(value = "/userUpdate")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/remove/{id}")
    public String removeUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}