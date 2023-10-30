
package Entidades;


public class DietaComida {
    
    private int idDietaComida;
    private Comida comida;
    private Dieta dieta;
    private Horario horario;

    public DietaComida() {
    }

    public DietaComida(Comida comida, Dieta dieta, Horario horario) {
        this.comida = comida;
        this.dieta = dieta;
        this.horario=horario;
    }

    public DietaComida(int idDietaComida, Comida comida, Dieta dieta,Horario horario) {
        this.idDietaComida = idDietaComida;
        this.comida = comida;
        this.dieta = dieta;
        this.horario=horario;
    }

    public int getIdDietaComida() {
        return idDietaComida;
    }

    public void setIdDietaComida(int idDietaComida) {
        this.idDietaComida = idDietaComida;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "idDietaComida=" + idDietaComida + ", comida=" + comida + ", dieta=" + dieta ;
    }    
    
}
