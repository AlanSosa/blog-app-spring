package com.app.blog.controllers;

import com.app.blog.forms.RegisterUserForm;
import com.app.blog.models.User;
import com.app.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.validation.Valid;

/**
 * Created by bacon_lover on 20/05/17.
 */

@Controller
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/register")
    public String index(RegisterUserForm registerUserForm) {
        return "users/register";
    }

    @PostMapping("/users/register")
    public String save(@Valid RegisterUserForm registerForm, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors())
            model.addAttribute("error", "Please Fill the form Correctly");
        else
        {
            try{
                User user = new User();
                //BCryptPasswordEncoder passEnconder = new BCryptPasswordEncoder();

                user.setUsername( registerForm.getUsername());
                user.setFullName( registerForm.getFullname());
                //user.setPasswordHash( passEnconder.encode( registerForm.getPassword() ));
                user.setPasswordHash( registerForm.getPassword() );
                userRepository.save(user);
                model.addAttribute("error", "User Created Succesfully!");
            }
            catch(JpaSystemException exception){
                System.out.println(exception.getMessage());
                model.addAttribute("error", "Oops! something went wrong");
            }
        }
        return "users/register";
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
        return "users/register";
    }


}
