package com.frank.springboot.proy.sistema_empleados.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frank.springboot.proy.sistema_empleados.entities.Empleado;
import com.frank.springboot.proy.sistema_empleados.entities.dto.EmpleadoDto;
import com.frank.springboot.proy.sistema_empleados.services.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Empleado> findAll(){

        return employeeService.findAll();

    }
    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> view(@PathVariable("id") Long id){
        Optional<Empleado> empleadoOptional = employeeService.findById(id);
        if(empleadoOptional.isPresent()){
            return ResponseEntity.ok(empleadoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> create(@Validated @RequestBody EmpleadoDto empleado,BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(empleado));

    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @Validated @RequestBody EmpleadoDto empleado, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result); // Devolver la respuesta si hay errores
        }
    
        Optional<Empleado> optionalEmp = employeeService.update(id, empleado);
        if (optionalEmp.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalEmp.get()); // Cambiar a OK si se actualiza correctamente
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Empleado> optional = employeeService.delete(id);
        if(optional.isPresent()){
            return ResponseEntity.ok(optional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    public ResponseEntity<?> validation(BindingResult result){
        Map<String,String> errores = new HashMap<>();

        result.getFieldErrors().forEach(err->{
            errores.put(err.getField(), "El campo de : "+err.getField()+" "+err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errores);

    }
}
