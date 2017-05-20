package com.app.blog.controllers;

import com.app.blog.forms.LoginForm;
import com.app.blog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by bacon_lover on 07/05/17.
 */
@Controller
public class LoginController {

    //@Autowired
    //private UserService userService;

    //@Autowired
    //private NotificationService notifyService;

    @GetMapping("/users/login")
    public String login(LoginForm loginForm){
        return "users/login";
    }

    @PostMapping("/users/login")
    public String loginPage(@Valid LoginForm loginForm, BindingResult bindingResult, Model model){
        System.out.println("Login Data : "+ loginForm.getUsername() + ", "+ loginForm.getPassword());
        /*if(bindingResult.hasErrors()){
            //notifyService.addErrorMessage("Please Fill the form correctly");
            model.addAttribute("error", "Please Fill the form Correctly");
            return "users/login";
        }*

        /*if( !userService.aunthenticate(loginForm.getUsername(), loginForm.getPassword())){
            //notifyService.addErrorMessage("Invalid Login!");
            model.addAttribute("error", "Invalid Login");
            return "users/login";
        }*/

        //notifyService.addInfoMessage("Login Successful");
        System.out.println("Login Successful!");
        return "redirect:/";
    }

}
