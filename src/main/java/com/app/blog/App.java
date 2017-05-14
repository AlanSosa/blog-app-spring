package com.app.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * Created by bacon_lover on 07/05/17.
 */

@SpringBootApplication
public class App
{
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return(container -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");

            container.addErrorPages(error404Page);
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
