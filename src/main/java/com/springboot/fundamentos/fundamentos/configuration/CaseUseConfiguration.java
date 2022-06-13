package com.springboot.fundamentos.fundamentos.configuration;

import com.springboot.fundamentos.fundamentos.caseuse.GetUser;
import com.springboot.fundamentos.fundamentos.caseuse.GetUserImplement;
import com.springboot.fundamentos.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }


}
















