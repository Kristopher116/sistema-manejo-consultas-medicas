package Implementaciones;


public class Paciente extends Persona {
    private Boolean EsEmergencia; // Lo tengo por si es prioridad agendarlo primero
    private String SignosVitales; //  para tener un registro de su estado de salud

    public Paciente(String id, String nombre, int edad, boolean EsEmergencia, String signosVitales) {
        super(nombre, edad, id);
        this.EsEmergencia = EsEmergencia;
        this.SignosVitales = signosVitales;
    }
    public Boolean isEmergencia() {
        return EsEmergencia;
    }
    public String getSignosVitales() {
        return SignosVitales;
    }
    public void setSignosVitales(String signosVitales) {
        this.SignosVitales = signosVitales;
    }
}
