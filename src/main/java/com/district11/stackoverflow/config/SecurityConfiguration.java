package com.district11.stackoverflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/auth/login-form")
                .loginProcessingUrl("/process_login")
                .failureUrl("/auth/login-form?error")
                .and()
                .exceptionHandling().accessDeniedPage("/auths/access")
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/members/my-page").hasRole("USER")
                        .antMatchers("⁄**").permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        UserDetails userDetails =
                User.withDefaultPasswordEncoder()
                        .username("abc@gmail.com")
                        .password("abc")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}
