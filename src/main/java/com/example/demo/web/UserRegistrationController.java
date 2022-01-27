package com.example.demo.web;

import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserRegistrationDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // allows Sring to recognise this class as MVC controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super(); 
        this.userService = userService;
    }

    //we need to create one more object here which creates an empty user object
    // our thymeleaf is getting user object from here
    @ModelAttribute("user") //this method returns a new object of user registration dto
    public UserRegistrationDto userRegistrationDto()
    {
        return new UserRegistrationDto();
    }

    // we are going to create a handler method that will handle HTTP post request, when user submit registration, that would be our post request.
    @PostMapping //this method will handle post http reuqest.
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto)
    {
            userService.save(registrationDto);
            return "redirect:/registration?success";
    }

    //now we need a method handler that will return registration.html page when we request for it by typing specified url/registration
    @GetMapping //http get method.
    public String showRegistrationForm(){
        return "registration";
    }
     

    
}
