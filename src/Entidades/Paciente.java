
package Entidades;

import java.time.LocalDate;


public class Paciente extends Persona {
    
    private int idPaciente; 
    private double altura;
    private String domicilio,telefonoFijo;
    private boolean estado;

    public Paciente() {
    }    

    public Paciente(double altura, String domicilio, String telefonoFijo, boolean estado, String apellido, String nombre, String dni, String celular, String mail, LocalDate fechaNac) {
        super(apellido, nombre, dni, celular, mail, fechaNac);
        this.altura = altura;
        this.domicilio = domicilio;
        this.telefonoFijo = telefonoFijo;
        this.estado = estado;
    }

    public Paciente(int idPaciente, double altura, String domicilio, String telefonoFijo, boolean estado, String apellido, String nombre, String dni, String celular, String mail, LocalDate fechaNac) {
        super(apellido, nombre, dni, celular, mail, fechaNac);
        this.idPaciente = idPaciente;
        this.altura = altura;
        this.domicilio = domicilio;
        this.telefonoFijo = telefonoFijo;
        this.estado = estado;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
//        return "nombre: "+ getNombre() + " apellido "+ getApellido() +" idPaciente= " + idPaciente +" Altura=  "+altura+", domicilio= " + domicilio + ", telefonoFijo= " + telefonoFijo;
        return super.getDni()+" - "+super.getApellido()+", "+super.getNombre();
    }      
}
