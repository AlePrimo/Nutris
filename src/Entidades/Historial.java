
package Entidades;

import java.time.LocalDate;

public class Historial {
    
    private int idHistorial;
    private Paciente paciente;
    private Dieta dieta;
    private double pesoActual;
    
    private LocalDate fechaRegistro;

    public Historial() {
    }

    public Historial(Paciente paciente, Dieta dieta, double pesoActual, LocalDate fechaRegistro) {
        this.paciente = paciente;
        this.dieta = dieta;
        this.pesoActual = pesoActual;
        this.fechaRegistro = fechaRegistro;
    }

    public Historial(int idHistorial, Paciente paciente, Dieta dieta, double pesoActual, LocalDate fechaRegistro) {
        this.idHistorial = idHistorial;
        this.paciente = paciente;
        this.dieta = dieta;
        this.pesoActual = pesoActual;
        this.fechaRegistro = fechaRegistro;
    }

  

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    
    
    
    
    
    @Override
    public String toString() {
        return "Historial{" + "idHistorial=" + idHistorial + ", paciente=" + paciente + ", pesoActual=" + pesoActual + ", fechaRegistro=" + fechaRegistro + '}';
    }
    
    
    
    
    
    
    
    
    
}
