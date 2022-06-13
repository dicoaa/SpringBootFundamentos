package com.springboot.fundamentos.fundamentos.caseuse;

import com.springboot.fundamentos.fundamentos.entity.User;
import com.springboot.fundamentos.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}




















