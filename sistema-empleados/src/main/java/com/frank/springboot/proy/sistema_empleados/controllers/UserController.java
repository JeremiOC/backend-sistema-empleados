package com.frank.springboot.proy.sistema_empleados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


import java.util.Optional;


import com.frank.springboot.proy.sistema_empleados.entities.Usuario;
import com.frank.springboot.proy.sistema_empleados.entities.dto.UsuarioDto;
import com.frank.springboot.proy.sistema_empleados.services.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/findAll")
  public List<Usuario> findAll(){
        
        return userService.findAll();

    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result){
        if(result.hasErrors()){
            validation(result);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(usuarioDto));
    }
  
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario, BindingResult result) {
        if(result.hasErrors()){
            validation(result);
        }
        Optional<Usuario> userOp = userService.findById(id);
        if(userOp.isPresent()){
            try{
            Usuario usuUpdate = userService.actualizarUsuario(id, usuario.getUsername(), usuario.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(usuUpdate);

            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el campo: "+e.getMessage());
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }

    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Usuario> userOptional = userService.delete(id);
        
        if (userOptional.isPresent()) {
            return ResponseEntity.ok().body("Usuario eliminado exitosamente: " + userOptional.get().getUsername());
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }
    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Usuario> optional = userService.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok().body(optional.orElseThrow());
        }   
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> validation(BindingResult result){
        Map<String,String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err->{
            errores.put(err.getField(), "El campo : "+err.getField()+" "+err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().build();
    }

}
