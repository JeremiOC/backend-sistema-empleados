package com.frank.springboot.proy.sistema_empleados.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departamento_id;

    private String nombre_dep;

 

    public Departamento() {
    }

    public Departamento(Long departamento_id, String nombre_dep) {
        this.departamento_id = departamento_id;
        this.nombre_dep = nombre_dep;
    }

    public Departamento(String nombre_dep) {
        this.nombre_dep = nombre_dep;
    }

    public Long getId_departamento() {
        return departamento_id;
    }

    public void setId_departamento(Long departamento_id) {
        this.departamento_id = departamento_id;
    }

    public String getNombre_dep() {
        return nombre_dep;
    }

    public void setNombre_dep(String nombre_dep) {
        this.nombre_dep = nombre_dep;
    }

   
    
}
