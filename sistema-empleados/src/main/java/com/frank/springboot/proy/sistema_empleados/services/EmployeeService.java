package com.frank.springboot.proy.sistema_empleados.services;

import java.util.List;
import java.util.Optional;

import com.frank.springboot.proy.sistema_empleados.entities.Empleado;
import com.frank.springboot.proy.sistema_empleados.entities.dto.EmpleadoDto;

public interface EmployeeService {

    List<Empleado> findAll();

    Optional<Empleado> findById(Long id);

    Empleado create(EmpleadoDto empleado);

    Optional<Empleado> update(Long id, EmpleadoDto empleado);
    
    Optional<Empleado> delete(Long id);
}
