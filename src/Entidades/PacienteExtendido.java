/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class PacienteExtendido extends Paciente{
    
    private LocalDate fechaNac2;

    public PacienteExtendido() {
    }

    public PacienteExtendido(LocalDate fechaNac2) {
        this.fechaNac2 = fechaNac2;
    }

    public PacienteExtendido(LocalDate fechaNac2, double altura, String domicilio, String telefonoFijo, boolean estado, String apellido, String nombre, String dni, String celular, String mail, LocalDate fechaNac) {
        super(altura, domicilio, telefonoFijo, estado, apellido, nombre, dni, celular, mail, fechaNac);
        this.fechaNac2 = fechaNac2;
    }

    public PacienteExtendido(LocalDate fechaNac2, int idPaciente, double altura, String domicilio, String telefonoFijo, boolean estado, String apellido, String nombre, String dni, String celular, String mail, LocalDate fechaNac) {
        super(idPaciente, altura, domicilio, telefonoFijo, estado, apellido, nombre, dni, celular, mail, fechaNac);
        this.fechaNac2 = fechaNac2;
    }

    public LocalDate getFechaNac2() {
        return fechaNac2;
    }

    public void setFechaNac2(LocalDate fechaNac2) {
        this.fechaNac2 = fechaNac2;
    }

    @Override
    public String toString() {
        return "PacienteExtendido{" + "fechaNac2=" + fechaNac2 + '}';
    }
    
}
