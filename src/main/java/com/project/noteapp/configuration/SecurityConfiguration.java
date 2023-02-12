package com.project.noteapp.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration{
    private PasswordEncoder passwordEncoder;


//    @Bean
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1").password("Admin_1").roles("ADMIN")
//                .and()
//                .withUser("user2").password("Admin_2").roles("SUPER_ADMIN");
//    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests()
                .requestMatchers("/{user_id}/**").permitAll()
                .requestMatchers("/login").permitAll()
                .anyRequest()
                .authenticated();

        return http.build();
    }
}
