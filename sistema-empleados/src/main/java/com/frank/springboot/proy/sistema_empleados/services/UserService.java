package com.frank.springboot.proy.sistema_empleados.services;

import java.util.Optional;
import java.util.List;
import com.frank.springboot.proy.sistema_empleados.entities.Usuario;
import com.frank.springboot.proy.sistema_empleados.entities.dto.UsuarioDto;

public interface UserService {
    List<Usuario> findAll();
    Usuario create(UsuarioDto usuarioDto);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> delete(Long id);
    Usuario actualizarUsuario(Long id, String username, String password);
    
} 
