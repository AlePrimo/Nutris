//
package Entidades;

import java.time.LocalDate;


public class Persona {
    private String apellido, nombre;
    private String dni;
    private String celular, mail;
    private LocalDate fechaNac;

    public Persona() {
    }

    public Persona(String apellido, String nombre, String dni, String celular, String mail, LocalDate fechaNac) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.celular = celular;
        this.mail = mail;
        this.fechaNac = fechaNac;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return "Persona{" + "apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + ", celular=" + celular + ", mail=" + mail + ", fechaNac=" + fechaNac + '}';
    }
    
    
}
