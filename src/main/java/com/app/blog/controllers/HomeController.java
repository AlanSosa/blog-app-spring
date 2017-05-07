package com.app.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bacon_lover on 07/05/17.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
