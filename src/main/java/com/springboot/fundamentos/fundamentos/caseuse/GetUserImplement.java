package com.springboot.fundamentos.fundamentos.caseuse;

import com.springboot.fundamentos.fundamentos.entity.User;
import com.springboot.fundamentos.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser{
    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}












