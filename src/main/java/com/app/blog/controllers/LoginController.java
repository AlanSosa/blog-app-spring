package com.app.blog.controllers;

import com.app.blog.forms.LoginForm;
import com.app.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by bacon_lover on 07/05/17.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    //@Autowired
    //private NotificationService notifyService;

    @RequestMapping("/users/login")
    public String login(LoginForm loginForm){
        return "users/login";
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String loginPage(@Valid LoginForm loginForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            //notifyService.addErrorMessage("Please Fill the form correctly");
            model.addAttribute("error", "Please Fill the form Correctly");
            return "users/login";
        }

        if( !userService.aunthenticate(loginForm.getUsername(), loginForm.getPassword())){
            //notifyService.addErrorMessage("Invalid Login!");
            model.addAttribute("error", "Invalid Login");
            return "users/login";
        }

        //notifyService.addInfoMessage("Login Successful");
        System.out.println("Login Successful!");
        return "redirect:/";
    }

}
