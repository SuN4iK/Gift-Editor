package com.example.demo.config;

import com.example.demo.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//Аннотации для конфига и энеблить защиту
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    //инициализировать класс
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    //Фильтрация запросов на сервер, разрешается тоько заходить на / /registratioon /login незалоиннеым пользщователям
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/", "/registration", "/login").permitAll()
                        .anyRequest().authenticated()
                ).formLogin(form->form.loginPage("/login").defaultSuccessUrl("/profile", true).permitAll())
                .logout(logout->logout.logoutSuccessUrl("/").permitAll())
                .userDetailsService(userDetailsService);
        return http.build();
    }
    //конфа энкодера
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
