package com.example.securingweb.Controllers;

import com.example.securingweb.Entity.User;
import com.example.securingweb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class LoginConfig implements WebMvcConfigurer {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/reg")
    public String regPage(){
        return "registration";
    }

    @PostMapping("/reg")
    public String regPostPage(User user,
                              Model model){
        if (userService.createUser(user)){
            model.addAttribute("mes", "Успешная регистрация");
            return "login";
        }
        else {
            model.addAttribute("mes", "Пользователь с email: " + user.getUsername() + " уже зарегистрирован!");
            return "registration";
        }
    }

    @GetMapping("/logout")
    public String logoutMain(){
        return "mainPage";
    }

}
