package Implementaciones;

public class VisitaMedica {
    private String diagnostico;
    private String NombreDoctor;

public VisitaMedica(String diagnostico, String nombreDoctor) {

        this.diagnostico = diagnostico;
        this.NombreDoctor = nombreDoctor;
    }

    // Getters
    public String getDiagnostico() {
        return diagnostico;
    }

    public String getNombreDoctor() {
        return NombreDoctor;
    }

    @Override
    public String toString() {
        return "--------------------" +
                "\nVisitaMedica" +
                "\nDiagnostico: " + diagnostico +
                "\nNombre Doctor: " + NombreDoctor +
                "\n-------------------";
    }
}
