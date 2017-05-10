package com.app.blog.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bacon_lover on 09/05/17.
 */
@Controller
public class RedirectUnknownUrls implements ErrorController {

    @GetMapping("/error")
    public void redirectNonExistentUrlsToHome(HttpServletResponse response) throws IOException {
        response.sendRedirect("errors/404");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}