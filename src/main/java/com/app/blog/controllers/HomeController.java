package com.app.blog.controllers;

import com.app.blog.models.Post;
import com.app.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by bacon_lover on 07/05/17.
 */

@Controller
public class HomeController {

    //@Autowired
    //private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/")
    public String index(Model model) {
        //postService = new PostServiceStubImpl();

        /*List<Post> latest5Posts = postService.findLatest5();
        model.addAttribute("latest5posts", latest5Posts);

        List<Post> latest3Posts = latest5Posts.stream()
                .limit(3)
                .collect(Collectors.toList());*/

        ArrayList<Post> postList = new ArrayList<>();
        postRepository.findAll().forEach(post -> postList.add(post));

        model.addAttribute("latest3posts", postList);
        return "index";
    }

}