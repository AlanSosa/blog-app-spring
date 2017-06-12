package com.app.blog.controllers;

import com.app.blog.forms.RegisterUserForm;
import com.app.blog.models.Role;
import com.app.blog.models.User;
import com.app.blog.repositories.RoleRepository;
import com.app.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bacon_lover on 20/05/17.
 */

@Controller
public class UsersController {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
            String username = registerForm.getUsername();
            String fullname = registerForm.getFullname();
            String passwordHash = bCryptPasswordEncoder.encode( registerForm.getPassword() );
            //String passwordHash = registerForm.getPassword();
            try{

                //Role role = new Role("ROLE_USER");
                Role role = roleRepo.findOne((long) 1);
                User user = new User(username, passwordHash, fullname, role);
                /*Set users = new HashSet<User>(){{
                    add(user);
                }};
                role.setUsers(users);
                roleRepo.save(role);*/
                userRepo.save(user);
                model.addAttribute("error", "User Created Succesfully!");
            }
            catch(EntityExistsException e){
                Role role = roleRepo.findOne((long) 1);
                User user = new User(username, passwordHash, fullname, role);
                userRepo.save(user);
                model.addAttribute("error", e.getMessage());
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
