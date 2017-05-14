package com.app.blog.controllers;

import com.app.blog.models.Post;
import com.app.blog.services.PostService;
import com.app.blog.services.PostServiceStubImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bacon_lover on 07/05/17.
 */

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String index(Model model){
        //postService = new PostServiceStubImpl();

        List<Post> latest5Posts = postService.findLatest5();
        model.addAttribute("latest5posts", latest5Posts);

        List<Post> latest3Posts = latest5Posts.stream()
                .limit(3)
                .collect(Collectors.toList());
        model.addAttribute("latest3posts", latest3Posts);
        return "index";
    }

}