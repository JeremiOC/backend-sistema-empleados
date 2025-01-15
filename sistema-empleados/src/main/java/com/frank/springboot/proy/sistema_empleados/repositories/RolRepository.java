package com.frank.springboot.proy.sistema_empleados.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import com.frank.springboot.proy.sistema_empleados.entities.Rol;


public interface RolRepository extends CrudRepository<Rol,Long>{
    @Query("select r from Rol r where r.nombre_rol = ?1")
    Optional<Rol> findByName(String nombre_rol);


}
