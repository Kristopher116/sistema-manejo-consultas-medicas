// Main created
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("========================================");
        System.out.println("Bienvenido al Sistema de Citas Médicas");
        System.out.println("========================================");

        System.out.println("\nEnter your name:\n");
        String name = scanner.nextLine();
        System.out.println("\nHola, " + name + "! Bienvenido al Sistema de Citas Médicas.");
        scanner.close();
        // Se puede cambiar si quieres esto era un test a ver si corre el programa
    }
}