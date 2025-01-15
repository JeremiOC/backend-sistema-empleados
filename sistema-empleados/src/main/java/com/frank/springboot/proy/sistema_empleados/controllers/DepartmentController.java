package com.frank.springboot.proy.sistema_empleados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.frank.springboot.proy.sistema_empleados.entities.Departamento;
import com.frank.springboot.proy.sistema_empleados.repositories.DepartmentRepository;

@RestController
@RequestMapping("/dep")
public class DepartmentController {

    @Autowired
    private DepartmentRepository repository;
    @GetMapping
    public List<Departamento> findAll(){
        return (List<Departamento>) repository.findAll();
    }
}
