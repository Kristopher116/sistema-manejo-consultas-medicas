package Implementaciones;


public class Paciente extends Persona {
    private Boolean esEmergencia; // Lo tengo por si es prioridad agendarlo primero
    private String signosVitales; //  para tener un registro de su estado de salud

    public Paciente(String id, String nombre, int edad, boolean EsEmergencia, String signosVitales) {
        super(nombre, edad, id);
        this.esEmergencia = EsEmergencia;
        this.signosVitales = signosVitales;
    }
    public Boolean isEmergencia() {
        return esEmergencia;
    }
    public String getSignosVitales() {
        return signosVitales;
    }
    public void setSignosVitales(String signosVitales) {
        this.signosVitales = signosVitales;
    }
}
