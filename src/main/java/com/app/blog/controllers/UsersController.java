package com.app.blog.controllers;

import com.app.blog.models.User;
import com.app.blog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by bacon_lover on 20/05/17.
 */

@Controller
public class UsersController {

    UserRepository userRepo;

    @GetMapping("/users/create")
    public String create(Model model) {
        model.addAttribute("mPost", new User());
        return "users/create";
    }

    @PostMapping("/users/create")
    public String save(){

        return "redirect: /";
    }

    @GetMapping("/users/view/{id}")
    public String view(){
        return "users/view";
    }

    @GetMapping("/users/delete/{id}")
    public String delete(){
        return "redirect:/";
    }

    @GetMapping("/users/edit/{id}")
    public String edit(){
        return "users/create";
    }


}
