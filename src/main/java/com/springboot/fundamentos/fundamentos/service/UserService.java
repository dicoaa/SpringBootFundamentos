package com.springboot.fundamentos.fundamentos.service;

import com.springboot.fundamentos.fundamentos.entity.User;
import com.springboot.fundamentos.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //Peek es para mostrar en pantalla lo que voy registrando
    //Metodo para guardar informacion
    //Transactional permite hacer un rollback de de todas las
    // transacciones que se han registrado en la base de datos
    @Transactional
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> LOG.info("Usuario insertado: "+ user))
                .forEach(userRepository::save);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return
                userRepository.findById(id)
                .map(
                        user -> {
                            user.setEmail(newUser.getEmail());
                            user.setBirthdate(newUser.getBirthdate());
                            user.setName(newUser.getName());
                            return userRepository.save(user);
                        }
                ).get();

    }
}




















