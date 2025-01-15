package com.frank.springboot.proy.sistema_empleados.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.frank.springboot.proy.sistema_empleados.entities.Usuario;

public interface UserRepository extends CrudRepository<Usuario,Long>{

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.username = :username, u.password = :password WHERE u.user_id = :id")
    void actualizarUsuario(@Param("id") Long id, @Param("username") String username, @Param("password") String password);

    Optional<Usuario> findByUsername(String username);
}
