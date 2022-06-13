package com.springboot.fundamentos.fundamentos.controller;

import com.springboot.fundamentos.fundamentos.caseuse.CreateUser;
import com.springboot.fundamentos.fundamentos.caseuse.DeleteUser;
import com.springboot.fundamentos.fundamentos.caseuse.GetUser;
import com.springboot.fundamentos.fundamentos.caseuse.UpdateUser;
import com.springboot.fundamentos.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    // Servicios de: create, read-get, update, delete

    //inyectamos por dependencia nuestro caso de uso
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;


    //Inyectamos por constructor
    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }

    //Creamos metodo para devolver todos los usuarios
    @GetMapping("/")
    List<User> get(){
        //Llamamos nuestra dependencia
        return getUser.getAll();
        //debemos agregar la ruta por donde sera consumido este servicio
        //para esto usamos la anotacion requestMapping y ponemos la
        //ruta como parametro
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/actualizar")
    ResponseEntity<User> replaceUser(@RequestBody User newUser){
        return new ResponseEntity<>(updateUser.update(newUser, newUser.getId()), HttpStatus.OK);
    }



}















