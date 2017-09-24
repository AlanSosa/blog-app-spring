package com.app.blog.controllers;

import com.app.blog.forms.RegisterUserForm;
import com.app.blog.models.Role;
import com.app.blog.models.User;
import com.app.blog.repositories.RoleRepository;
import com.app.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by bacon_lover on 20/05/17.
 */

@Controller
public class UsersController {

    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/user/login")
    public String login(Principal principal) {
        if (principal != null) return "redirect:/";

        return "user/login";
    }

    @GetMapping("/user/register")
    public String index(RegisterUserForm form, Principal principal) {
        if (principal != null) return "redirect:/";
        return "user/register";
    }

    @PostMapping("/user/register")
    public String save(@Valid RegisterUserForm registerForm, BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()) {
            String username = registerForm.getUsername();
            String fullname = registerForm.getFullname();
            String passwordHash = bCryptPasswordEncoder.encode(registerForm.getPassword() );

            // ROLE_USER - ROLE_ADMIN
            Role role = roleRepository.findByName("ROLE_USER");
            User user = new User(username, passwordHash, fullname, role);

            userRepository.save(user);
        }
        return "redirect:/user/login";
    }

    /*
    @GetMapping("/user/view/{id}")
    public String view(){
        return "user/view";
    }

    @GetMapping("/user/delete/{id}")
    public String delete(){
        return "redirect:/";
    }

    @GetMapping("/user/edit/{id}")
    public String edit(){
        return "user/register";
    }
    */

}
