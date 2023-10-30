
package Entidades;

import java.time.LocalDate;


public class Dieta {
    
    private int idDieta;
    private String nombre;
    private Paciente paciente;
    private LocalDate fechaInicio, fechaFinal;
    private double pesoInicial, pesoActual;
    private boolean estado;

   public Dieta() {
    }

    public Dieta(String nombre, Paciente paciente,  LocalDate fechaInicio, LocalDate fechaFinal, double pesoInicial, double pesoActual, boolean estado) {
        this.nombre = nombre;
        this.paciente = paciente;
      
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.pesoInicial = pesoInicial;
        this.pesoActual = pesoActual;
        this.estado = estado;
    }

    public Dieta(int idDieta, String nombre, Paciente paciente, LocalDate fechaInicio, LocalDate fechaFinal, double pesoInicial, double pesoActual, boolean estado) {
        this.idDieta = idDieta;
        this.nombre = nombre;
        this.paciente = paciente;
    
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.pesoInicial = pesoInicial;
        this.pesoActual = pesoActual;
        this.estado = estado;
    }



    public int getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(int idDieta) {
        this.idDieta = idDieta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

   

    

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public double getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(double pesoInicial) {
        this.pesoInicial = pesoInicial;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return idDieta + "-" + nombre + "||" + (estado?"Terminada":"En Curso");
    }
    
    
    
    
}
