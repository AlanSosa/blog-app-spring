package com.app.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by bacon_lover on 07/05/17.
 */

@Controller
public class HomeController {

    @RequestMapping(name = "/", method = GET)
    public String index(Model model){
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        model.addAttribute("list", list);
        return "index";
    }

}
