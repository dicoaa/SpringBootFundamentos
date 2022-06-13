package com.springboot.fundamentos.fundamentos.configuration;

import com.springboot.fundamentos.fundamentos.bean.MyBeanWithProperties;
import com.springboot.fundamentos.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.springboot.fundamentos.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:connection.properties")
@EnableConfigurationProperties(UserPojo.class)
public class GeneralConfiguration {
    @Value("${value.name}")// Nos permite llamar los valores que hemos creado previamente
    private String name;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${value.random}")
    private String random;

    //Llamamos los valores de nuestro archivo connection
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${driver}")
    private String driver;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;


    @Bean
    public MyBeanWithProperties function(){

        return new MyBeanWithPropertiesImplement(name, apellido);
    }

    //Aca relacionamos todo lo que tiene que ver con BD
    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        //Llamamos el mismo metodo que configuramos en properties
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();

    }


}










