package com.springboot.fundamentos.fundamentos.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding //Para poder inyectar lo siguiente como dependencia, poder construir el pojo a partir de las propiedades
@ConfigurationProperties(prefix = "user") //para representar la propiedad a nivel del archivo properties
public class UserPojo {
    private String email;
    private String password;
    private String age;

    public UserPojo(String email, String password, String age) {
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
