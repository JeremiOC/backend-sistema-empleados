package com.frank.springboot.proy.sistema_empleados.repositories;

import org.springframework.data.repository.CrudRepository;

import com.frank.springboot.proy.sistema_empleados.entities.Empleado;

public interface EmployeeRepository extends CrudRepository<Empleado,Long> {
    
}