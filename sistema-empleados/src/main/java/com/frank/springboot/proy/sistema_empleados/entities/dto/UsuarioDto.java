package com.frank.springboot.proy.sistema_empleados.entities.dto;

public class UsuarioDto {

    private String username; // Nombre de usuario
    private String password; // Contraseña
    private Long empleadoId;  // ID del empleado asociado
    private boolean admin;    // Si es administrador o no

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
