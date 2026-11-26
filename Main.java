// Main created
import Empleo.Doctor;
import Implementaciones.Citas;
import Implementaciones.GestorOficinaMedica;
import Implementaciones.Paciente;
import Implementaciones.SistemaOficinaMedica;
import Implementaciones.VisitaMedica;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaOficinaMedica sistema = new GestorOficinaMedica();
        boolean salir = false;

        
        System.out.println("========================================");
        System.out.println("Bienvenido al Sistema de Citas Médicas");
        System.out.println("========================================");
        

        while(!salir){
            System.out.println("========= Menú Principal ===========");
            System.out.println("1.  Registrar nuevo paciente");
            System.out.println("2.  Registrar nuevo empleado (Secretaria/Enfermera/Doctor)");
            System.out.println("3.  Agendar una cita");
            System.out.println("4.  Cancelar una cita");
            System.out.println("5.  Añadir paciente a la fila de espera");
            System.out.println("6.  Llamar/Atender al siguiente paciente (Prioritiza emergencias)");
            System.out.println("7.  Registrar visita médica (Signos vitales y Diagnóstico)");
            System.out.println("8.  Mostrar historial clínico de un paciente");
            System.out.println("9.  Buscar médico por especialidad");
            System.out.println("10. Mostrar todos los pacientes en espera");
            System.out.println("11. Mostrar médicos disponibles");
            System.out.println("12. Mostrar citas de hoy");
            System.out.println("0.  Salir");
            System.out.println("Ingrese un número para elegir la opción deseada: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: //Resitrar Paciente
                    System.out.print("Nombre del paciente: ");
                    String nombrePaciente = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edadPaciente = scanner.nextInt();
                    scanner.nextLine();
                    
                    //Determinar si es emergencia
                    System.out.println("¿Es una emergencia? ");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    int isEmergencia = scanner.nextInt();
                    scanner.nextLine();
                    
                    boolean emergencia;

                    if (isEmergencia == 1){
                        emergencia = true;
                    }
                    else if (isEmergencia == 2) {
                        emergencia = false;
                    }
                    else{
                        System.out.println("Opción inválida.");
                        break;
                    }
                    
                    sistema.registrarPaciente(nombrePaciente, edadPaciente, emergencia);
                    System.out.println("Se ha registrado exitosamente");
                    break;

                case 2: //Registrar empleado
                    System.out.print("Nombre del empleado: ");
                    String nombreEmpleado = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edadEmpleado = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("¿Cuál es su rol? (Secretaria / Enfermera / Doctor):  ");
                    String rol = scanner.nextLine();
                    String especialidad = null;

                    if(rol.equalsIgnoreCase("Doctor")){
                        System.out.print("¿Cuál es su especialidad? (Oftalmólogo general / Oftalmólogo pediátrico / Neurooftalmólogo / Retinólogo): ");
                        especialidad = scanner.nextLine();
                    }

                    sistema.registrarEmpleado(nombreEmpleado, edadEmpleado, rol, especialidad);
                    System.out.println("Se ha registrado exitosamente");
                    break;

                case 3: //Agendar Cita
                    System.out.print("ID del Paciente (ej. PAC-1): ");
                    String idPaciente = scanner.nextLine();
                    System.out.print("ID del Doctor (ej. EMP-1): ");
                    String idDoctor = scanner.nextLine();
                    System.out.print("Mes (ej. 1 - 12): ");
                    int mes = scanner.nextInt();
                    System.out.print("Día (ej. 1-31):" );
                    int día = scanner.nextInt();
                    System.out.print("Año (ej. 2026): ");
                    int año = scanner.nextInt();
                    System.out.print("Hora (ej. 1-12): ");
                    int horas = scanner.nextInt();
                    System.out.print("Minutos (00 - 59): ");
                    String minuto = scanner.nextLine();
                    System.out.println("1. AM");
                    System.out.println("2. PM");
                    int horario = scanner.nextInt();
                    scanner.nextLine();

                    String periodo;
                    if (horario == 1){
                        periodo = "AM";
                    }
                    else if (horario == 2){
                        periodo = "PM";
                    }
                    else {
                        System.out.println("Opción inválida.");
                        break;
                    }

                    String fecha = mes + "/" + día + "/" + año;
                    String hora = horas + ":" + minuto + " " + periodo;

                    sistema.agendarCita(idPaciente, idDoctor, fecha, hora);
                    System.out.println("La cita ha sido agendada");
                    break;

                case 4:
                    System.out.print("ID de la Cita a cancelar (ej. CITA-1): ");
                    String idCita = scanner.nextLine();
                    sistema.cancelarCita(idCita);
                    System.out.println("La cita ha sido cancelada");
                    break;

                case 5:
                    System.out.print("ID del Paciente que llegó a la oficina (ej. PAC-1): ");
                    String idPacienteFila = scanner.nextLine();
                    sistema.agregarAFilaDeEspera(idPacienteFila);
                    System.out.println("El paciente ha sido añadido a la fila de espera");
                    break;

                case 6:
                    Paciente siguiente = sistema.obtenerSiguientePaciente();

                    if(siguiente != null){
                        System.out.print("-> Pasando a consulta a: " + siguiente.getNombre() + " (ID: " + siguiente.getId() + " | Emergencia: " + siguiente.isEmergencia() + ")");
                    }

                    else{
                        System.out.print("No hay pacientes en la fila de espera.");
                    }
                    break;

                case 7:
                    System.out.print("ID de la Cita completada (ej. CITA-1): ");
                    String idCitaCompletada = scanner.nextLine();
                    System.out.print("Signos vitales tomados por la enfermera: ");
                    String signosVit = scanner.nextLine();
                    System.out.print("Diagnóstico del médico: ");
                    String diagnostico = scanner.nextLine();

                    sistema.registrarVisitaMedica(idCitaCompletada, signosVit, diagnostico);
                    break;

                case 8:
                    System.out.println("ID del Paciente (ej. PAC-1): ");
                    String idHist = scanner.nextLine();
                    List<VisitaMedica> historial = sistema.obtenerHistorialPaciente(idHist);
                    
                    if(historial.isEmpty()){
                       System.out.println("Este paciente no tiene visitas registradas.");
                    }

                    else{
                        System.out.println("--- HISTORIAL CLÍNICO ---");

                        for(VisitaMedica v : historial){
                            System.out.println(v);
                        }
                    }
                    break;

                case 9:
                    System.out.print("Ingrese la especialidad que este buscando: ");
                    String espBuscar = scanner.nextLine();
                       List<Doctor> doctoresEsp = sistema.buscarDoctoresPorEspecialidad(espBuscar);
                    
                    if(doctoresEsp.isEmpty()){
                       System.out.println("No hay doctores con esa especialidad.");
                    }

                    else{
                        for(Doctor d : doctoresEsp){
                            System.out.println("Dr. " + d.getNombre() + " | ID: " + d.getId() + " | Disponible: " + d.isDisponible());
                        }
                    }
                    break;

                case 10:
                    List<Paciente> enEspera = sistema.obtenerPacientesEnEspera();
                    
                    if(enEspera.isEmpty()){
                       System.out.println("La sala de espera está vacía.");
                    }

                    else{
                        System.out.println("--- PACIENTES EN ESPERA ---");

                        for(Paciente p : enEspera){
                            System.out.println(p.getId() + " - " + p.getNombre() + " (Emergencia: " + p.isEmergencia() + ")");
                        }
                    }
                    break;

                case 11:
                    List<Doctor> disponibles = sistema.obtenerDoctoresDisponibles();
                    
                    if(disponibles.isEmpty()){
                       System.out.println("No hay médicos disponibles en este momento.");
                    }

                    else{
                        System.out.println("--- MÉDICOS DISPONIBLES ---");

                        for(Doctor d : disponibles){
                            System.out.println(d.getId() + " - Dr. " + d.getNombre() + " (" + d.getEspecialidad() + ")");
                        }
                    }
                    break;

                case 12:
                    System.out.print("Ingrese la fecha de hoy (día/mes/año): ");
                    String hoy = scanner.nextLine();
                    List<Citas> citasHoy = sistema.obtenerCitasPorFecha(hoy);
                    if (citasHoy.isEmpty()) {
                        System.out.println("No hay citas registradas para esta fecha.");
                    } else {
                        System.out.println("--- CITAS DEL HOY " + hoy + " ---");
                        for (Citas c : citasHoy) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 0:
                    salir = true;
                    System.out.println("Cerrando el sistema.");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
//Test case para ver si Paciente funciona correctamente

        // Paciente paciente = new Paciente("123456789", "Juan Pérez", 30, true, "Presión arterial: 120/80, Frecuencia cardíaca: 70 bpm");
        // System.out.println("Información del paciente:");
        // System.out.println("ID: " + paciente.getId());
        // System.out.println("Nombre: " + paciente.getNombre());
        // System.out.println("Edad: " + paciente.getEdad());
        // System.out.println("Es emergencia: " + paciente.isEmergencia());
        // System.out.println("Signos vitales: " + paciente.getSignosVitales());

//Test case para ver si Doctor funciona correctamente
        
        // Doctor doc1 = new Doctor("Dr. Roberto", 42, "DOC-101", "Oftalmólogo pediátrico");

        // System.out.println("--- DATOS DEL MÉDICO ---");
        // System.out.println("Nombre: " + doc1.getNombre());
        // System.out.println("Edad: " + doc1.getEdad());
        // System.out.println("ID: " + doc1.getId());
        // System.out.println("Rol: " + doc1.getRol()); 
        // System.out.println("Especialidad: " + doc1.getEspecialidad());

        scanner.close();
        
    }
}