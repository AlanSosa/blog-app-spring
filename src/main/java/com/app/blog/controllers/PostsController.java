package com.app.blog.controllers;

import com.app.blog.forms.NewPostForm;
import com.app.blog.models.Post;
import com.app.blog.models.Role;
import com.app.blog.models.User;
import com.app.blog.repositories.PostRepository;
import com.app.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by bacon_lover on 09/05/17.
 */

@Controller
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = {"/", "/posts"})
    public String list(Model model, @RequestParam(value = "item", required = false, defaultValue = "6") int item) {
        ArrayList<Post> postList = new ArrayList<>();
        postRepository.findAll().forEach(postList::add);
        // TODO Attribute item is for pagination change the name
        // posts?item=1

        model.addAttribute("lastpost", postList);
        return "posts/list";
    }

    @GetMapping("/posts/create")
    public String create(NewPostForm newPostForm) {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String save(@Valid NewPostForm postForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "posts/create";
        }
        User autor = userRepository.findByUsername(principal.getName());
        Post newPost = new Post(postForm.getId(), postForm.getTitle(), postForm.getBody(), autor);
        postRepository.save(newPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        postRepository.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Post post = postRepository.findOne(id);
        if(post==null){
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        return "posts/view";
    }

    @GetMapping("/posts/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Post post = postRepository.findOne(id);
        model.addAttribute("mPost", post);
        return "posts/create";
    }
}
