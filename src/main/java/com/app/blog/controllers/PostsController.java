package com.app.blog.controllers;

import com.app.blog.models.Post;
import com.app.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bacon_lover on 09/05/17.
 */

@Controller
public class PostsController {

    @Autowired
    private PostService postService;

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model){

        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "posts/view";
    }
}
