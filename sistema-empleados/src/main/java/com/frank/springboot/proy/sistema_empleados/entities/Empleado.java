package com.frank.springboot.proy.sistema_empleados.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frank.springboot.proy.sistema_empleados.validaciones.CustomCurrencySerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id_empleado;

    @NotBlank(message = "El campo de nombres no puede estar vacio")
    private String nombre_empleado;

    @NotBlank(message = "El campo de apellidos no puede estar vacio")
    private String apellidos_empleado;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy" )
    private Date fec_ingreso;

    @JsonSerialize(using = CustomCurrencySerializer.class)
    private double sueldo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;
    
    @OneToOne(mappedBy = "empleado")
    @JsonIgnore
    private Usuario usuario;
    
    public Empleado() {
    }

    public Long getId_empleado() {
        return id_empleado;
    }
    public void setId_empleado(Long id_empleado) {
        this.id_empleado = id_empleado;
    }
    public String getNombre_empleado() {
        return nombre_empleado;
    }
    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }
    public String getApellidos_empleado() {
        return apellidos_empleado;
    }
    public void setApellidos_empleado(String apellidos_empleado) {
        this.apellidos_empleado = apellidos_empleado;
    }
    public Date getFec_ingreso() {
        return fec_ingreso;
    }
    public void setFec_ingreso(Date fec_ingreso) {
        this.fec_ingreso = fec_ingreso;
    }
    public double getSueldo() {
        return sueldo;
    }
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    public Departamento getDepartamento() {
        return departamento;
    }
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_empleado == null) ? 0 : id_empleado.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Empleado other = (Empleado) obj;
        if (id_empleado == null) {
            if (other.id_empleado != null)
                return false;
        } else if (!id_empleado.equals(other.id_empleado))
            return false;
        return true;
    }
    
    




}
