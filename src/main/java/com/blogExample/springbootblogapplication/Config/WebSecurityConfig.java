package com.blogExample.springbootblogapplication.Config;

import jakarta.servlet.annotation.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.text.BreakIterator;

@Configuration// try removing this and see what happened
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig {



    private static final String[] WHITELIST = {
            "/",
            "/register",
            "/h2-console/**"
    };


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests().requestMatchers(WHITELIST).permitAll()
                .requestMatchers(HttpMethod.GET,"/posts/*").permitAll()//try removing httpMethod.GEt and only use posts. Also try adding it in the whitelist
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/",true).failureUrl("/login?error").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
                .and()
                .httpBasic();

//        http.csrf().disable();  //disable this for h2-console
//        http.headers().frameOptions().disable(); //we disable this for h2-console

        return http.build();

    }

}
