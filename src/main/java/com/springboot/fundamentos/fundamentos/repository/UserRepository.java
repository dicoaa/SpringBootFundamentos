package com.springboot.fundamentos.fundamentos.repository;

import com.springboot.fundamentos.fundamentos.dto.UserDto;
import com.springboot.fundamentos.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// parametros -> User es la entidad, Long es el tipo de dato del id
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Dentro de las comillas se hace la colsulta JPQL, u -> apodo/objeto de User
    @Query("Select u from User u WHERE u.email=?1")
    // Crear un opcional para dar mejor uso a los valores null
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u WHERE u.name like ?1%")
    //Lo q va a retornar y su respectivo tipo, findAndSort() -> metodo nuestro
    List<User> findAndSort(String name, Sort sort);

    //solo para una lista de usuarios
    List<User> findByName(String name);

    //para buscar un solo usuario
    Optional<User> findByEmailAndName(String email, String name);

    // Con sentencia LIKE
    List<User> findByNameLike(String name);

    // Con sentencia OR
    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthdateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    //sin el like
    List<User> findByNameContainingOrderByIdDesc(String name);

    //UserDto es una clase que apenas vamos a crear y en el estereotipo query ponemos
    //nuestra sentencia
    // Los named parameters es renombrar los parametros que teniamos
    // al inicio en el momento que estamos en la sentencia
    @Query("SELECT new com.springboot.fundamentos.fundamentos.dto.UserDto(u.id, u.name, u.birthdate)" +
            "FROM User u " +
            "WHERE u.birthdate=:parametroFecha " +
            "and u.email=:parametroEmail")
    Optional<UserDto> getAllByBirthdateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("parametroEmail") String email);

}

















