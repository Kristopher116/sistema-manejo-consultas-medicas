package Empleo;

public class Doctor extends Empleados {
    private String especialidad;
    private boolean disponible; 

    public Doctor(String nombre, int edad, String id, String especialidad) {
        super(nombre, edad, id, "Doctor");
        this.especialidad = especialidad; // Oftalmólogo general, Oftalmólogo pediátrico, Neurooftalmólogo, Retinólogo
        this.disponible = true; // para ver si se puede atender a un paciente
    }

    // Getters
    public String getEspecialidad() {
        return especialidad;
    }
    public boolean isDisponible() {
        return disponible;
    }
    // Setters
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
