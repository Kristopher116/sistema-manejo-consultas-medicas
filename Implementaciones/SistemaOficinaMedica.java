package Implementaciones;

import Empleo.Doctor;
import Empleo.Empleados;
import java.util.List;

public interface SistemaOficinaMedica {

    void registrarPaciente(String nombre, int edad, boolean esEmergencia); 
    void registrarEmpleado(String nombre, int edad, String rol, String especialidad);

    void agendarCita(String idPaciente, String idDoctor, String fecha, String hora);
    void cancelarCita(String idCita);
    
    void agregarAFilaDeEspera(String idPaciente);
    Paciente obtenerSiguientePaciente();
    void registrarVisitaMedica(String idCita, String signosVitales, String diagnostico);
    void iniciarConsulta(String idCita);

    List<VisitaMedica> obtenerHistorialPaciente(String idPaciente);
    List<Doctor> buscarDoctoresPorEspecialidad(String especialidad);
    List<Paciente> obtenerPacientesEnEspera();
    List<Doctor> obtenerDoctoresDisponibles();
    List<Citas> obtenerCitasPorFecha(String fechaHoy);

    Paciente buscarPacientePorId(String idPaciente);
    Empleados buscarEmpleadoPorId(String idPaciente);
}