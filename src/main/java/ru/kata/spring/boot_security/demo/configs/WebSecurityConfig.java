

package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.services.UserService;

    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        private final SuccessUserHandler successUserHandler;
        private final UserService userService;

        @Autowired
        public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService) {
            this.successUserHandler = successUserHandler;
            this.userService = userService;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/", "/user/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/", "/admin/**").hasRole("ADMIN")
                    .and()
                    .formLogin().successHandler(successUserHandler)
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
        }

        @Bean
        public static PasswordEncoder getPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
            daoAuthenticationProvider.setUserDetailsService(userService);
            return daoAuthenticationProvider;
        }
    }