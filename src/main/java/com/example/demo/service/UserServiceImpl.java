package com.example.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.web.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service //we use this annotations to annotate service classes
public class UserServiceImpl implements UserService{

    
    // here we will override the method that we created in our service interface
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    @Override
    public User save(UserRegistrationDto registrationDto) {
         // TODO Auto-generated method stub
         User user=new User(registrationDto.getFirstName(),registrationDto.getLastName(),registrationDto.getEmail(),passwordEncoder.encode(registrationDto.getPassword()),Arrays.asList(new Role("ROLE_USER")));

         return userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user=userRepository.findByEmail(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("Invalid Username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    //create a methods that will map roles to authority
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
    {
        //here will map roles to authority
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        // we have converted role in streams and on top of stream we just map a role and we converted role in simplegrantedauthority and we collected stream into list and than returned this list.
        // authority is spring security term
    }

   
}