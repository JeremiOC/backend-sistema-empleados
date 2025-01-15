package com.frank.springboot.proy.sistema_empleados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frank.springboot.proy.sistema_empleados.entities.Departamento;
import com.frank.springboot.proy.sistema_empleados.entities.Empleado;
import com.frank.springboot.proy.sistema_empleados.entities.dto.EmpleadoDto;
import com.frank.springboot.proy.sistema_empleados.repositories.DepartmentRepository;
import com.frank.springboot.proy.sistema_empleados.repositories.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Empleado> findAll() {
        return (List<Empleado>) employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Empleado> findById(Long id) {
        return employeeRepository.findById(id); 
    }
    
    @Transactional
    @Override
    public Empleado create(EmpleadoDto empleadoDto) {
        Empleado empleado = new Empleado();
        empleado.setNombre_empleado(empleadoDto.getNombre_empleado());
        empleado.setApellidos_empleado(empleadoDto.getApellidos_empleado());
        empleado.setFec_ingreso(empleadoDto.getFec_ingreso());
        empleado.setSueldo(empleadoDto.getSueldo());
        Optional<Departamento> depOp = departmentRepository.findById(empleadoDto.getDepartamentoId());
        if(!depOp.isPresent()){
            throw new RuntimeException("Departamento con id : "+depOp+" no existe"); 
        }
        empleado.setDepartamento(depOp.get());
        return employeeRepository.save(empleado);
    }
    

@Transactional
@Override
public Optional<Empleado> update(Long id, EmpleadoDto empleadoDto) {
    Optional<Empleado> optionalEmpleado = employeeRepository.findById(id);

    if (optionalEmpleado.isPresent()) {
        Empleado emp = optionalEmpleado.get();
        
        
        emp.setNombre_empleado(empleadoDto.getNombre_empleado());
        
        emp.setApellidos_empleado(empleadoDto.getApellidos_empleado());
        
        emp.setSueldo(empleadoDto.getSueldo());
        

        if (empleadoDto.getDepartamentoId() != null) {
            Optional<Departamento> departamentoOpt = departmentRepository.findById(empleadoDto.getDepartamentoId());
            departamentoOpt.ifPresent(dep -> emp.setDepartamento(dep));
        }

        return Optional.of(employeeRepository.save(emp));
    }

    return Optional.empty();
}


    @Transactional
    @Override
    public Optional<Empleado> delete(Long id) {
        
        Optional<Empleado> optional= employeeRepository.findById(id);
        optional.ifPresent(empDel->{
            employeeRepository.delete(empDel);
        });

        return optional;
    }

}
