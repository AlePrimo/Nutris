
package Entidades;


public class Comida {
    private int idComida;
    private String nombre,detalle;
    private double calorias;
    private boolean estado;

    public Comida() {
    }

    public Comida(String nombre, String detalle, double calorias, boolean estado) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.calorias = calorias;
        this.estado = estado;
    }

    public Comida(int idComida, String nombre, String detalle, double calorias, boolean estado) {
        this.idComida = idComida;
        this.nombre = nombre;
        this.detalle = detalle;
        this.calorias = calorias;
        this.estado = estado;
    }

   

    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Comida{" + "idComida=" + idComida + ", nombre=" + nombre + ", detalle=" + detalle + ", calorias=" + calorias + '}';
    }
    
    
    
    
    
}
