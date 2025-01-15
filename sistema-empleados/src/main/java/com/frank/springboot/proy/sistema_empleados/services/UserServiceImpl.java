package com.frank.springboot.proy.sistema_empleados.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frank.springboot.proy.sistema_empleados.entities.Empleado;
import com.frank.springboot.proy.sistema_empleados.entities.Rol;
import com.frank.springboot.proy.sistema_empleados.entities.Usuario;
import com.frank.springboot.proy.sistema_empleados.entities.dto.UsuarioDto;
import com.frank.springboot.proy.sistema_empleados.repositories.RolRepository;
import com.frank.springboot.proy.sistema_empleados.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired 
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeService employeeService;

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) userRepository.findAll();
    }

    @Transactional
    @Override
    public Usuario create(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));

        Optional<Empleado> emp = employeeService.findById(usuarioDto.getEmpleadoId());
        if(!emp.isPresent()){
            throw new RuntimeException("Empleado no encontrado");
        }
        usuario.setEmpleado(emp.get());
        Set<Rol> roles = new HashSet<>();
        if(usuarioDto.isAdmin()){
            Optional<Rol> rolAdmin = rolRepository.findByName("ROLE_ADMIN");
            if(rolAdmin.isPresent()){
                roles.add(rolAdmin.get());
            }else{
                throw new RuntimeException("Rol Admin no encontrado");
            }
        }else{
            Optional<Rol> rolUser = rolRepository.findByName("ROLE_USER");
            if(rolUser.isPresent()){
                roles.add(rolUser.get());
            }else{
                throw new RuntimeException("ROl user no encontrado");
            }
        }
        usuario.setRoles(roles);

        return userRepository.save(usuario);    
    }

    @Override
    public Usuario actualizarUsuario(Long id, String username, String password) {
        Optional<Usuario> usuOptional = userRepository.findById(id);
        if(usuOptional.isPresent()){
            Usuario usu = usuOptional.get();
            if(username==null || username.isBlank()){
                throw new RuntimeException("El campo username no puede estar vacio");
            }
            if(password==null || password.isBlank()){
                throw new RuntimeException("El campo password no puede estar vacio");
            }
            usu.setUsername(username);
            usu.setPassword(password);

            return userRepository.save(usu);
        }else{
            throw new RuntimeException("Usuario no encontrado con el id : "+id);
        }
    }
    


    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Usuario> delete(Long id) {
        Optional<Usuario> usuarioOp = userRepository.findById(id);
        if (usuarioOp.isPresent()) {
            // Obtener el usuario
            Usuario usuario = usuarioOp.get();
            
            // Eliminar roles relacionados
            usuario.getRoles().clear(); // Eliminar roles relacionados

            // Ahora borrar el usuario
            userRepository.delete(usuario);
            
            // Confirmar la eliminación
            return Optional.of(usuario);
        }
        return Optional.empty();
    }
   


    
 
}
