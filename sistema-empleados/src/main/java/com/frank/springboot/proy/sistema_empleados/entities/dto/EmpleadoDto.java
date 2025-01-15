package com.frank.springboot.proy.sistema_empleados.entities.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frank.springboot.proy.sistema_empleados.validaciones.CustomCurrencySerializer;

public class EmpleadoDto {
    private String nombre_empleado;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy" )
    private Date fec_ingreso;
    private String apellidos_empleado;
    private Long departamentoId;
    @JsonSerialize(using = CustomCurrencySerializer.class)
    private double sueldo;
    public String getNombre_empleado() {
        return nombre_empleado;
    }
    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }
    public Date getFec_ingreso() {
        return fec_ingreso;
    }
    public void setFec_ingreso(Date fec_ingreso) {
        this.fec_ingreso = fec_ingreso;
    }
    public String getApellidos_empleado() {
        return apellidos_empleado;
    }
    public void setApellidos_empleado(String apellidos_empleado) {
        this.apellidos_empleado = apellidos_empleado;
    }
    public Long getDepartamentoId() {
        return departamentoId;
    }
    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }
    public double getSueldo() {
        return sueldo;
    }
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    
}
