package com.app.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//zimport org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

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

    //Añado este link,
    //http://docs.spring.io/spring-security/site/docs/current/apidocs/org/springframework/security/core/userdetails/jdbc/JdbcDaoImpl.html
    //Explica muy bien como hacer esta puta consulta, es de la documetnacion oficial.
    //Parece que la bola de Hindus piensan que el resto del mundo es un idiota, y no
    //Explican como jijos de su re chingada madre hacer esto, pase 4 dias
    //tratando como hacer esta cosa. Por fin la hice hacer funcionar
    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:8889/blog_spring");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("root");
        return driverManagerDataSource;
    }

    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource());
        jdbcImpl.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?");
        jdbcImpl.setAuthoritiesByUsernameQuery("SELECT users.username, role.name FROM users\n" +
                "JOIN role ON users.role_id = role.id\n" +
                "WHERE users.username = ?");
        return jdbcImpl;
    }

    @Bean(name= "bCryptPasswordEnconder")
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
