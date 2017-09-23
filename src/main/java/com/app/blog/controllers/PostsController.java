package com.app.blog.controllers;

import com.app.blog.models.Post;
import com.app.blog.models.Role;
import com.app.blog.models.User;
import com.app.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by bacon_lover on 09/05/17.
 */

@Controller
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String list(Model model) {
        ArrayList<Post> postList = new ArrayList<>();
        postRepository.findAll().forEach(postList::add);
        /*for(int i = 0; i < 10; i++) {
            postList.add(new Post(1,
                    "Title " + i,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vel ipsum vitae dolor aliquet suscipit nec in nisl. Integer ultricies imperdiet mollis. Fusce dapibus justo vitae diam bibendum, vel semper lorem scelerisque. Cras porttitor nisi vel rhoncus venenatis. Mauris tempor sit amet nisi sit amet vulputate. Aliquam tempus eget elit ac tempor. Etiam a nibh vestibulum arcu viverra blandit vitae in lorem. Curabitur feugiat leo ligula, id elementum risus lacinia quis. Donec varius dignissim molestie. Quisque congue ipsum lectus, sit amet molestie augue accumsan et. Vivamus bibendum ligula id augue suscipit, sit amet rutrum lectus rhoncus. Nunc ut risus molestie, lobortis elit eget, dignissim sem. Morbi luctus ante dolor, ac mattis libero dignissim quis. Aenean quis ligula iaculis, tempor ligula condimentum, venenatis nisi. Integer dictum condimentum dui, at suscipit urna mattis nec. Duis rutrum diam non nibh accumsan ullamcorper.\n" +
                            "\n" +
                            "Vestibulum sed erat maximus, malesuada odio eget, volutpat ipsum. Donec scelerisque posuere massa. Fusce id lorem et lectus consequat laoreet non porttitor turpis. Donec eu suscipit eros. Donec sodales diam et nulla feugiat elementum. Praesent commodo, eros bibendum dapibus placerat, mi nisi pharetra quam, non pharetra felis odio ac dolor. Cras eleifend vulputate elit, ut tempus nunc efficitur quis.\n" +
                            "\n" +
                            "Fusce dictum congue purus, eu molestie dui maximus varius. Mauris rhoncus eget ipsum quis sagittis. Morbi dictum facilisis velit, id consequat est volutpat id. Etiam cursus libero quis tempus tincidunt. Integer molestie libero eget enim posuere luctus. Pellentesque sit amet libero et augue semper mollis ac id nunc. Morbi auctor bibendum nunc, sed blandit neque tincidunt sed. Phasellus finibus ornare quam et malesuada. Nam auctor laoreet vehicula. Vestibulum a mattis arcu. Sed lacinia lacus magna, vel congue mi tincidunt non. Vestibulum vestibulum dui eros, ac dapibus arcu sagittis eu. Nunc eu libero quam.\n",
                    new User(2, "joseP", "jose carabez")));
        }*/
        model.addAttribute("lastpost", postList);
        return "posts/list";
    }

    @GetMapping("/posts/create")
    public String create(Model model) {
        Post mPost = new Post();
        model.addAttribute("mPost", mPost);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String save(@ModelAttribute Post mPost) {
        if (mPost.getId() == null){
            System.out.println("ID es null");
        }
        mPost.setAuthor( new User(1, "DummyUser", " Dummy User" ));
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
        model.addAttribute("mPost", post);
        return "posts/create";
    }
}
