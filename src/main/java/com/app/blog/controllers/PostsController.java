package com.app.blog.controllers;

import com.app.blog.models.Post;
import com.app.blog.repositories.PostRepository;
import com.app.blog.services.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bacon_lover on 09/05/17.
 */

@Controller
public class PostsController {

    //@Autowired
    //private PostService postService;
    //@Autowired
    //private NotificationService notifyService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts/create")
    public String create(Model model) {
        model.addAttribute("mPost", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String save(@ModelAttribute Post mPost) {
        if (mPost.getId() == null){
            System.out.println("ID es null");
        }
        postRepository.save(mPost);
        return "redirect:/";
    }

    @GetMapping("/posts/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        postRepository.delete(id);
        return "redirect:/";
    }

    @GetMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Post post = postRepository.findOne(id);
        if(post==null){
            return "redirect:/";
        }
        model.addAttribute("post", post);
        return "posts/view";
    }

    @GetMapping("/posts/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Post post = postRepository.findOne(id);
        /*if(post==null){
            return "redirect:/";
        }*/
        model.addAttribute("mPost", post);
        return "posts/create";
    }
}
