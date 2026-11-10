package Implementaciones;

public abstract class Persona {
    protected String nombre;
    protected int edad;
    protected String id;

    public Persona(String nombre, int edad, String id) {
        this.nombre = nombre;
        this.edad = edad;
        this. id = id;
    }
    // Getters
    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }
    public String getId() {
        return id;
    }
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
}
