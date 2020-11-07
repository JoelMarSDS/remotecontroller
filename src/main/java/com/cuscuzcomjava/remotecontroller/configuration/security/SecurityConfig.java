package com.cuscuzcomjava.remotecontroller.configuration.security;

import com.cuscuzcomjava.remotecontroller.entity.User;
import com.cuscuzcomjava.remotecontroller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    UserService userServiceImp;

    @Autowired
    protected void configure(HttpSecurity http) throws Exception { //Controle de acesso as páginas
        http.csrf().disable().authorizeRequests() //Autorizando requisições abaixo

                .antMatchers("").hasRole("ADMIN")
                .antMatchers("").permitAll()
                .antMatchers("").permitAll()
                .antMatchers("").permitAll()
                .antMatchers("").permitAll()

                .anyRequest().authenticated() //para outros acessos deve haver autenticação

                .and()
                .formLogin()
                .loginPage("").defaultSuccessUrl("").permitAll()
                .permitAll()

                .and()
                .logout()
                .logoutSuccessUrl("")
                .permitAll();
    }


    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**","/img/**", "/images/**");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImp).passwordEncoder(new BCryptPasswordEncoder());
    }


}
