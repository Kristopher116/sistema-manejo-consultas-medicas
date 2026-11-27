package Implementaciones;

public class VisitaMedica {
    private String diagnostico;
    private String NombreDoctor;
    private String signosVitales;

public VisitaMedica(String diagnostico, String nombreDoctor, String signosVitales) {

        this.diagnostico = diagnostico;
        this.NombreDoctor = nombreDoctor;
        this.signosVitales = signosVitales;
    }

    // Getters
    public String getDiagnostico() {
        return diagnostico;
    }

    public String getNombreDoctor() {
        return NombreDoctor;
    }

    public String getSignosVitales() {
        return signosVitales;
    }

    @Override
    public String toString() {
        return "--------------------" +
                "\nVisitaMedica" +
                "\nSignos Vitales: " + signosVitales +
                "\nDiagnostico: " + diagnostico +
                "\nNombre Doctor: " + NombreDoctor +
                "\n-------------------";
    }
}
