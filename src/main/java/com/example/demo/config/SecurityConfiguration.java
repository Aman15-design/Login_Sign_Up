package com.example.demo.config;

import com.example.demo.service.UserService;

//import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //this will basically integrate spring security with spring MVC 

// the below extended class provides us with overrided methods for us to configure spring security according to our need
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{


    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // we are using spring data JPA and hibernate so in order to integrate spring data jpa and hibernate with spring security we need to provide a bin 
    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder((passwordEncoder()));
        return auth;
    }

    //we need to pass authentication provider to config method so now we will override the below method to do so
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authenticationProvider());
    }

   //here we have overriden confirgure method and we have provided access to below URLs:-
   //1.Registration
   //2.JS files
   //3.Css
   //4.img files.
   @Override
   protected void configure(HttpSecurity http) throws Exception
   {
       http.authorizeRequests().antMatchers(
           "/registration**","/js/**","/css/**","/img/**"
       ).permitAll()
       .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll();
   }
    
}
