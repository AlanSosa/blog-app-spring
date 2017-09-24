package com.app.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

/**
 * Created by bacon_lover on 21/05/17.
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    //@Autowired
    //AccessDeniedHandler accessDeniedHandler;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEnconder;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEnconder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/" , "/posts", "/user/register", "/posts/view/**").permitAll()
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/user/login")
                    .failureUrl("/user/login?error=true")
                    .defaultSuccessUrl("/posts", true)
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                .and().httpBasic();
                /*
                .failureUrl("/user/login?error")
                .usernameParameter("username").passwordParameter("password")
                loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/homepage.html",true)
                .failureUrl("/login.html?error=true")
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .csrf().disable();*/
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/images/**", "/js/**", "/css/**");
    }
}