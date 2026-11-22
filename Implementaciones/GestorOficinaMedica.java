package Implementaciones;

import Empleo.Doctor;
import Empleo.Empleados;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GestorOficinaMedica implements SistemaOficinaMedica {

    private int id_Empleados = 1;
    private int id_Pacientes = 1;
    private int id_Citas = 1;

    private Map<String, Empleados> mapaEmpleados = new HashMap<>();
    private Map<String, Paciente> mapaPacientes = new HashMap<>();
    private Map<String, Citas> mapaCitas = new HashMap<>();
    private Map<String, LinkedList<VisitaMedica>> historiales = new HashMap<>();

    private Queue<Paciente> filaRegular = new LinkedList<>();
    private Queue<Paciente> filaEmergencias = new LinkedList<>();

    @Override 
    public void registrarPaciente(String nombre, int edad, boolean esEmergencia){
        String id = "PAC-" + id_Pacientes;
        id_Pacientes++;
        
        Paciente nuevoPaciente = new Paciente(id, nombre, edad, esEmergencia, "Aún sin registrar");
        mapaPacientes.put(id, nuevoPaciente);
        
        LinkedList<VisitaMedica> listaHistorial = new LinkedList<>();
        historiales.put(id, listaHistorial);
        
        System.out.println("El paciente" + nombre + " con ID " + id + "ha sido registrado exitosamente");
        
    }

    @Override
    public void registrarEmpleado(String nombre, int edad, String rol, String especialidad) {
        String id = "EMP-" + id_Empleados;
        id_Empleados++;
        
        if (rol.equalsIgnoreCase("Doctor")) {
        
            Doctor doctor = new Doctor(nombre, edad, id, especialidad);
            mapaEmpleados.put(id, doctor);
            
        	System.out.println("El doctor" + nombre + " con ID " + id + "ha sido registrado exitosamente");
            
        } 
        
        else {
        
            Empleados empleado = new Empleados(nombre, edad, id, rol);
            mapaEmpleados.put(id, empleado);
            
            System.out.println("El doctor" + nombre + " con ID " + id + "ha sido registrado exitosamente");
            
        }

    }


    @Override
    public void agendarCita(String idPaciente, String idDoctor, String fecha, String hora) {
        Paciente paciente = mapaPacientes.get(idPaciente);
        Empleados empleado = mapaEmpleados.get(idDoctor);

        if (paciente != null && empleado instanceof Doctor) {
        
            Doctor doctor = (Doctor) empleado;
            String idCita = "CITA-" + id_Citas;
            id_Citas++;
            
            Citas nuevaCita = new Citas(idCita, paciente, doctor, fecha, hora);
            mapaCitas.put(idCita, nuevaCita);
            
            System.out.println("La cita fue agendada: " + nuevaCita);
        } 
        
        else {
            System.out.println("No se pudo agendar la cita.");
        }
    }


    @Override
    public void cancelarCita(String idCita) {
        Citas cita = mapaCitas.get(idCita);
        
        if (cita != null) {
        
            System.out.println("La cita " + idCita + " no existe");
        } 
        
        cita.setEstado("Cancelada");
        System.out.println("La cita ha sido cancelada");
     
    }



    @Override
    public void agregarAFilaDeEspera(String idPaciente) {
        Paciente paciente = mapaPacientes.get(idPaciente);
        
        if (paciente == null) {
            System.out.println("El paciente no esta registrado.");
            return;
        }
        
        if (paciente.isEmergencia()) {
        
            filaEmergencias.add(paciente);
            System.out.println("El paciente" + paciente.getNombre() + " ha sido añadido a fila de emergencias.");
            
        } 
        
        else {
        
            filaRegular.add(paciente);
            System.out.println("El paciente" + paciente.getNombre() + " ha sido añadido a fila regular.");
        }
    }


    @Override
    public Paciente obtenerSiguientePaciente() {
    
        if (!filaEmergencias.isEmpty()) {
        
            return filaEmergencias.poll();
        }
        
        return filaRegular.poll();
    }


    @Override
    public void registrarVisitaMedica(String idCita, String signosVitales, String diagnostico) {
        Citas cita = mapaCitas.get(idCita);
        
        if (cita == null) {
        
			System.out.println("No existe la cita");
            return;
        }
        
        Paciente paciente = cita.getPaciente();
        Doctor doctor = cita.getDoctor();
        
        paciente.setSignosVitales(signosVitales);
        cita.setEstado("Completada");
        doctor.setDisponible(true);

        VisitaMedica visita = new VisitaMedica(diagnostico, doctor.getNombre());
            
        LinkedList<VisitaMedica> historial = historiales.get(paciente.getId());
            
        if(historial != null){
        	
        	historial.addFirst(visita);
        }
        
        System.out.println("La visita medica se ha registrado en el historial.");

    }


    @Override
    public List<VisitaMedica> obtenerHistorialPaciente(String idPaciente) {
        LinkedList<VisitaMedica> historial = historiales.get(idPaciente);

        if (historial == null){
            return new ArrayList<>();
        }
        
        return new ArrayList<>(historial);
    }

    @Override
    public List<Doctor> buscarDoctoresPorEspecialidad(String especialidad) {
        List<Doctor> resultado = new ArrayList<>();

        if (especialidad == null){
            return resultado;
        } 

        for (Empleados empleado : mapaEmpleados.values()){
            if (empleado instanceof Doctor){
                Doctor doctor = (Doctor) empleado;

                if (especialidad.equalsIgnoreCase(doctor.getEspecialidad())){
                    resultado.add(doctor);

            }
        }
        return new ArrayList<>(); //Dummy return
    }
    return resultado;
}

    @Override
    public List<Paciente> obtenerPacientesEnEspera() {
       return new ArrayList<>(); //Dummy return
    }

    @Override
    public List<Doctor> obtenerDoctoresDisponibles() {
        return new ArrayList<>(); //Dummy return
    }

    @Override
    public List<Citas> obtenerCitasPorFecha(String fechaHoy) {
        return new ArrayList<>(); //Dummy return
    }

    @Override
    public Paciente buscarPacientePorId(String idPaciente) {
        return null; //Dummy return
    }

    @Override
    public Empleados buscarEmpleadoPorId(String idEmpleado) {
        return null; //Dummy return
    }
}