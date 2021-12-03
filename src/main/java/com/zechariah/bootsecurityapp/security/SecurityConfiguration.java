package com.zechariah.bootsecurityapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
//                ADMIN Details
                .withUser("zechariah")
                .password(passwordEncoder().encode("zech123"))
                .roles("ADMIN").authorities("ACCESS_TEST1", "ACCESS_TEST2")

//                USER Details
                .and()
                .withUser("favour")
                .password(passwordEncoder().encode("fav123"))
                .roles("USER")

//                MANAGER Details
                .and()
                .withUser("manager")
                .password(passwordEncoder().encode("manager123"))
                .roles("MANAGER").authorities("ACCESS_TEST2");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/api/public/test1").hasAuthority("ADMIN")
                .antMatchers("/api/public/test2").hasAuthority("MANAGER")
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
