package com.frank.springboot.proy.sistema_empleados.services;

import java.util.List;
import java.util.Optional;

import com.frank.springboot.proy.sistema_empleados.entities.Departamento;

public interface DepartmentService {

    List<Departamento> findAll();

    Departamento create(Departamento departamento);

    Optional<Departamento> findById(Long id);

    Optional<Departamento> update(Long id, Departamento departamento);

    Optional<Departamento> delete(Long id);
    
}
