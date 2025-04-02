package com.johndeere.adzone.okta;



import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebsecurityConfig  extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity security) throws Exception{

        security.cors().and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS,"**").permitAll()  
        .antMatchers(HttpMethod.POST,"**").permitAll()
        .antMatchers(HttpMethod.PUT, "**").permitAll()
        .antMatchers("/actuator/**").permitAll()
        .anyRequest().permitAll()
        .and()
        .csrf().disable();			

    }

}  