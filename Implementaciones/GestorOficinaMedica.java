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

            for (Citas cita: mapaCitas.values()){
                if (!"Cancelada".equalsIgnoreCase(cita.getEstado())){

                    if (fecha.equals(cita.getFecha()) && hora.equals(cita.getHora())){
                        
                        if (idDoctor.equals(cita.getDoctor().getId())){
                            System.out.println("El doctor ya tiene una cita agendada a esa hora.");
                            return;
                        }

                        if (idPaciente.equals(cita.getPaciente().getId())){
                            System.out.println("El paciente ya tiene una cita en ese horario.");
                            return;
                        }
                    }
                }
            }

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
        
        if (cita == null) {
        
            System.out.println("La cita " + idCita + " no existe");
            return;
        } 
        
        if ("Completada".equalsIgnoreCase(cita.getEstado())){
            System.out.println("No se puede cancelar una cita completada.");
            return;
        }

        if ("Cancelada".equalsIgnoreCase(cita.getEstado())){
            System.out.println("Esta cita ya está cancelada.");
            return;
        }

        if("En Consulta".equalsIgnoreCase(cita.getEstado())){
            System.out.println("No se puede cancelar una consulta en curso.");
            return;
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

        if (filaRegular.contains(paciente) || filaEmergencias.contains(paciente)) {
            System.out.print("El paciente ya está en la fila de espera. ");
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
        
        if ("Cancelada".equalsIgnoreCase(cita.getEstado())){
            System.out.println("No se puede registrar una visita de una cita cancelada.");
            return;
        }

        if("Completada".equalsIgnoreCase(cita.getEstado())){
            System.out.println("Esta cita ya tiene una visita registrada.");
            return;
        }

        if(!"En Consulta".equalsIgnoreCase(cita.getEstado())){
            System.out.println("Primero debes iniciar la consulta con la opción 6.");
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
    }
    return resultado;
}

    @Override
    public List<Paciente> obtenerPacientesEnEspera() {
        List<Paciente> resultado = new ArrayList<>();

        resultado.addAll(filaEmergencias);
        resultado.addAll(filaRegular);
        return resultado;
       
    }

    @Override
    public List<Doctor> obtenerDoctoresDisponibles() {
        List<Doctor> resultado = new ArrayList<>();

            for (Empleados empleado : mapaEmpleados.values()){
                if (empleado instanceof Doctor) {
                    Doctor doctor = (Doctor) empleado;
                
                    if (doctor.isDisponible()){
                        resultado.add(doctor);
                    }
                }
            }
            return resultado;
    }

    @Override
    public List<Citas> obtenerCitasPorFecha(String fechaHoy) {
        List<Citas> resultado = new ArrayList<>();

        if (fechaHoy == null){
            return resultado;
        }

        for (Citas cita: mapaCitas.values()){
            if (fechaHoy.equals(cita.getFecha())){
                resultado.add(cita);
            }
        }

        return resultado;
    }

    @Override
    public Paciente buscarPacientePorId(String idPaciente) {
        return mapaPacientes.get(idPaciente);
    }

    @Override
    public Empleados buscarEmpleadoPorId(String idEmpleado) {
        return mapaEmpleados.get(idEmpleado); 
    }

    @Override 
    public void iniciarConsulta(String idCita){
        Citas cita = mapaCitas.get(idCita);

        if (cita == null){
            System.out.println("La cita no existe.");
            return;
        }

        if (!"Pendiente".equalsIgnoreCase(cita.getEstado()) && !"Confirmada".equalsIgnoreCase(cita.getEstado())){
            System.out.println("La cita no está pendiente de atención.");
            return;
        }

        Doctor doctor = cita.getDoctor();
        Paciente paciente = cita.getPaciente();

        if (!doctor.isDisponible()){
            System.out.println("El doctor está ocupado.");
            return;
        }

        Paciente siguiente;

        if (!filaEmergencias.isEmpty()){
            siguiente = filaEmergencias.peek();
        }

        else {
            siguiente = filaRegular.peek();
        }

        if (siguiente == null || !siguiente.getId().equals(paciente.getId())){
            System.out.println("El paciente debe estar al frente de la fila.");
            return;
        }

        obtenerSiguientePaciente();

        doctor.setDisponible(false);
        cita.setEstado("En Consulta");

        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("Doctor asignado: " + doctor.getNombre());
        System.out.println("Consulta iniciada. ");

    }
}