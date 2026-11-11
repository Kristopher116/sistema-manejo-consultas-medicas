package Empleo;


import Implementaciones.Persona;

public class Empleados extends Persona {
    private String rol; // "Secretaria", "Enfermera", "Doctor"

    public Empleados(String nombre, int edad, String id, String rol) {
        super(nombre, edad, id);
        this.rol = rol;
    }

    // Getters
    public String getRol() {
        return rol;
    }

    // Setters
    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
