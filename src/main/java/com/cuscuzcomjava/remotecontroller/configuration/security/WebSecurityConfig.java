package com.cuscuzcomjava.remotecontroller.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;




@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()



                .antMatchers(HttpMethod.POST, "/login").permitAll()

                .antMatchers(HttpMethod.POST, "/actress/create").hasRole("COMMON_USER")
                .antMatchers(HttpMethod.PUT, "/actress/update").hasRole("COMMON_USER")
                .antMatchers(HttpMethod.DELETE, "/actress/delete").hasRole("COMMON_USER")
                .antMatchers(HttpMethod.GET, "/reserve/listByActress/{reserveActressId}").hasRole("COMMON_USER")

                .antMatchers(HttpMethod.GET, "/actress/list").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/actress/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/actress/getByStatus").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/actress/getMostRelevant").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/actress/getLessRelevant").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/actress/getMostExpensive").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/actress/getLessExpensive").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/producer/create").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/producer/update/{updateProducerId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/producer/delete").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/reserve/listByProducer/{reserveProducerId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/reserve/countByProducer/{countReserveProducerId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/reserve/getMostReservedDatesByProducer/{producerId}}").hasRole("ADMIN")



                .anyRequest().authenticated()
                .and()

                // filtra requisições de login
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                // filtra outras requisições para verificar a presença do JWT no header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);




    }
}
