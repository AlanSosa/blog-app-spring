package com.app.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by bacon_lover on 21/05/17.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //@Value("${spring.datasource}")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEnconder;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEnconder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        /*Otra forma que encontre de autorizar REQUEST es así:

        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/employees/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH, "/employees/**").hasRole("ADMIN");

        Supongo que el Role lo toma de la base de datos, en el caso
        de esta app los roles están así:
        ROLE_USER
        ROLE_ADMIN
        * */
        /*httpSecurity.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .authorizeRequests().antMatchers("/users/login").permitAll()
                                    .antMatchers("/users/register").permitAll()
                                    .antMatchers("/posts/create").permitAll()
                .and()
                .formLogin().loginPage("/users/login").failureUrl("/users/login?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/users/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf();
*/
        httpSecurity.authorizeRequests().antMatchers("/").permitAll()
                    .and()
                    .formLogin().loginPage("/users/login").failureUrl("/users/login?error")
                    .usernameParameter("username").passwordParameter("password")
                    .and()
                    .exceptionHandling().accessDeniedPage("/403")
                    .and()
                    .csrf().disable();
    }

}