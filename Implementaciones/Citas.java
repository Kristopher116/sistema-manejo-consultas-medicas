package Implementaciones;

import Empleo.Doctor;
public class Citas {
    private String IdCita;
    private Paciente paciente;
    private Doctor doctor;
    private String fecha;   // Ejemplo: "03/10/2006" y cuando la cita se programa.
    private String hora; // Ejemplo: "6:09 A.M."
    private String estado; // "Pendiente", "Confirmada", "Cancelada", "Completada"
    public Citas(String IdCita, Paciente paciente, Doctor doctor, String fecha, String hora) {
        this.IdCita = IdCita;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "Pendiente"; // Por defecto, la cita se crea como pendiente
    }

    // Getters 
    public String getIdCita() {
        return IdCita;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public String getFecha() {
        return fecha;
    }
    public String getHora() {
        return hora;
    }
    public String getEstado() {
        return estado;
    }
    // Setters
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override 
    public String toString() {
        return "--------------------------"
                + "\nCita " +
                "\nIdCita: " + IdCita +
                "\nPaciente: " + paciente.getNombre() +
                "\nDoctor: " + doctor.getNombre() +
                "\nFecha: " + fecha +
                "\nHora: " + hora +
                "\nEstado: " + estado +
                "\n----------------------------";
    }
}