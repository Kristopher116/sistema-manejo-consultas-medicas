// Main created
import java.util.Scanner;
import Implementaciones.Paciente;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("========================================");
        System.out.println("Bienvenido al Sistema de Citas Médicas");
        System.out.println("========================================");
        

        //Test case para ver si Paciente funciona correctamente 
        Paciente paciente = new Paciente("123456789", "Juan Pérez", 30, true, "Presión arterial: 120/80, Frecuencia cardíaca: 70 bpm");
        System.out.println("Información del paciente:");
        System.out.println("ID: " + paciente.getId());
        System.out.println("Nombre: " + paciente.getNombre());
        System.out.println("Edad: " + paciente.getEdad());
        System.out.println("Es emergencia: " + paciente.isEmergencia());
        System.out.println("Signos vitales: " + paciente.getSignosVitales());

        scanner.close();
        
    }
}