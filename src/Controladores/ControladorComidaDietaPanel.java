/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Comida;
import Util.Efecto;
import Util.SetConnValues;
import Vistas.ComidaDietaPanel;
import Vistas.ComidaPanelMain;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Administrador
 */
public class ControladorComidaDietaPanel {
    
    private final String driverDB = SetConnValues.getTipoDB();
    
    private final ComidaPanelMain comidaPanelMain;
    private final ComidaDietaPanel comidaDietaPanel;
    private final Comida comida;
    private final List<Comida> comidas;
    
//    private final JButton jButtonLimpiarForm;
//    private final JButton jButtonBuscar;
    private final JButton jButtonEliminar;
    private final JButton jButtonGuardar;
    private final JButton jButtonNuevo;
    private final JButton jButtonSalir;
    
    private JLabel jLabelVolver;
    
    private JLis
    
//    private final Efecto efecto;

    public ControladorComidaDietaPanel(ComidaDietaPanel comidaDietaPanel) {
        this.comidaDietaPanel = comidaDietaPanel;
        comidaPanelMain = comidaDietaPanel.getComidaPanelMain();
        comida = null;
        comidas = new ArrayList<>();
        
        jButtonSalir = comidaDietaPanel.getjButtonSalir();
        jButtonGuardar = comidaDietaPanel.getjButtonGuardar();
        jButtonEliminar = comidaDietaPanel.getjButtonEliminar();
        jButtonNuevo = comidaDietaPanel.getjButtonNuevo();
        
        
    }

    public void salirMateriaActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void nuevoMateriaActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void eliminarMateriaActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void guardarMateriaActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonNuevoMouseEntered(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonNuevoMouseExited(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonEliminarMouseEntered(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonEliminarMouseExited(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonGuardarMouseEntered(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonGuardarMouseExited(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonSalirMouseEntered(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonSalirMouseExited(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonNuevoFocusGained(FocusEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonNuevoFocusLost(FocusEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonEliminarFocusGained(FocusEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonEliminarFocusLost(FocusEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonGuardarFocusGained(FocusEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonGuardarFocusLost(FocusEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonSalirFocusGained(FocusEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonSalirFocusLost(FocusEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
