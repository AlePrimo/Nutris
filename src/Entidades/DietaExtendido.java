/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author Administrador
 */
public class DietaExtendido extends Dieta{
    private LocalDate fechaNac2;

    public DietaExtendido() {
    }

    public DietaExtendido(LocalDate fechaNac2) {
        this.fechaNac2 = fechaNac2;
    }

    public DietaExtendido(LocalDate fechaNac2, String nombre, Paciente paciente, LocalDate fechaInicio, LocalDate fechaFinal, double pesoInicial, double pesoActual, boolean estado) {
        super(nombre, paciente, fechaInicio, fechaFinal, pesoInicial, pesoActual, estado);
        this.fechaNac2 = fechaNac2;
    }

    public DietaExtendido(LocalDate fechaNac2, int idDieta, String nombre, Paciente paciente, LocalDate fechaInicio, LocalDate fechaFinal, double pesoInicial, double pesoActual, boolean estado) {
        super(idDieta, nombre, paciente, fechaInicio, fechaFinal, pesoInicial, pesoActual, estado);
        this.fechaNac2 = fechaNac2;
    }
    
    public LocalDate getFechaNac2() {
        return fechaNac2;
    }

    public void setFechaNac2(LocalDate fechaNac2) {
        this.fechaNac2 = fechaNac2;
    }
    
}
