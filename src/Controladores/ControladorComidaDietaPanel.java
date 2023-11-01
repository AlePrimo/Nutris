/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import AccesoADatos.ComidaData;
import AccesoADatos.DietaComidaData;
import AccesoADatos.DietaData;
import Entidades.Comida;
import Entidades.Dieta;
import Entidades.Horario;
import Util.SetConnValues;
import Vistas.ComidaDietaPanel;
import Vistas.ComidaPanelMain;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import nutris.Conexion;
import nutris.Nutris;

/**
 *
 * @author Administrador
 */
public class ControladorComidaDietaPanel {

    private final String driverDB = SetConnValues.getTipoDB();

    private final ComidaPanelMain comidaPanelMain;
    private final ComidaDietaPanel comidaDietaPanel;
    private ComidaData comidaData;
    private DietaData dietaData;
    private DietaComidaData dietaComidaData;
    private Comida comida;
    private List<Comida> comidas;
    private Dieta dieta;
    private List<Dieta> dietas;

    private final JButton jButtonSalir;
    private JButton jButtonNuevaComida;
    private JButton jButtonNuevaDieta;

    private JLabel jLabelVolver;
    private JLabel jLabelAgregarComidaDieta;
    private JLabel jLabelIconoAgregar;
    private JLabel jLabelIconoQuitar;
    private JLabel jLabelQuitarComidaDieta;
    private JLabel jLabelIzq;
    private JLabel jLabelDer;

    private JCheckBox jCheckBoxComidaDieta;
    private JCheckBox jCheckBoxConSin;
    private JComboBox<Horario> jComboBoxHorario;

    private JList jListComida;
    private JList jListDieta;
    private DefaultListModel<Comida> listModelComida;
    private DefaultListModel<Dieta> listModelDieta;

    private int desdePanel;
    private JPanel jPanelAgregarQuitar;
    private int n1;
    private int n2;
    private int selectedIndexComida;
    private int selectedIndexDieta;

//    private final Efecto efecto;
    public ControladorComidaDietaPanel(ComidaDietaPanel comidaDietaPanel) {
        this.comidaDietaPanel = comidaDietaPanel;
        comidaPanelMain = comidaDietaPanel.getComidaPanelMain();
        jPanelAgregarQuitar = comidaDietaPanel.getjPanelAgregarQuitar();
        comida = null;
        comidas = new ArrayList<>();

        jButtonSalir = comidaDietaPanel.getjButtonSalir();
        jButtonNuevaComida = comidaDietaPanel.getjButtonNuevaComida();
        jButtonNuevaDieta = comidaDietaPanel.getjButtonNuevaDieta();

        jLabelVolver = comidaDietaPanel.getjLabelVolver();
        jLabelAgregarComidaDieta = comidaDietaPanel.getjLabelAgregarComidaDieta();
        jLabelIconoQuitar = comidaDietaPanel.getjLabelIconoQuitar();
        jLabelQuitarComidaDieta = comidaDietaPanel.getjLabelQuitarComidaDieta();
        jLabelIconoAgregar = comidaDietaPanel.getjLabelIconoAgregar();
        jLabelIzq = comidaDietaPanel.getjLabelIzq();
        jLabelDer = comidaDietaPanel.getjLabelDer();

        jCheckBoxComidaDieta = comidaDietaPanel.getjCheckBoxComidaDieta();
        jCheckBoxConSin = comidaDietaPanel.getjCheckBoxConSin();
        jComboBoxHorario = comidaDietaPanel.getjComboBoxHorario();

        jListComida = comidaDietaPanel.getjListComida();
        jListDieta = comidaDietaPanel.getjListDieta();
        listModelComida = comidaDietaPanel.getListModelComida();
        listModelDieta = comidaDietaPanel.getListModelDieta();

        selectedIndexComida = 0;
        selectedIndexComida = 0;
        try {
            comidaData = new ComidaData(Conexion.getConexion(driverDB));
            dietaData = new DietaData(Conexion.getConexion(driverDB));
            dietaComidaData = new DietaComidaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(ControladorComidaDietaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        evaluarCondiciones();
        dietas = dietaData.listarDietas();
        jListDieta.setSelectedIndex(0);
        System.out.println("dieta: "+dieta);
        System.out.println("comida: "+comida);
         
    }

    private void evaluarCondiciones() {
            CardLayout cardLayout = (CardLayout) jPanelAgregarQuitar.getLayout();
        if (jCheckBoxComidaDieta.isSelected()) {
            n1 = 0;
            jCheckBoxComidaDieta.setText("Dietas");
            jLabelIzq.setText("Dieta");
            jLabelDer.setText("Comida");
            jLabelAgregarComidaDieta.setText("Comida");
            jLabelQuitarComidaDieta.setText("Comida");
            if (jCheckBoxConSin.isSelected()) {
                n2 =1;
                jCheckBoxConSin.setText("con");
                cardLayout.show(jPanelAgregarQuitar, "cardQuitar");
            }
            else {
                n2 =2;
                jCheckBoxConSin.setText("sin");
                cardLayout.show(jPanelAgregarQuitar, "cardAgregar");
            }
        }else{
            n2 = 0;
            jCheckBoxComidaDieta.setText("Comidas");
            jLabelIzq.setText("Comida");
            jLabelDer.setText("Dieta");
            jLabelAgregarComidaDieta.setText("Dieta");
            jLabelQuitarComidaDieta.setText("Dieta");
            if (jCheckBoxConSin.isSelected()) {
                n1 = 1;
                jCheckBoxConSin.setText("con");
                cardLayout.show(jPanelAgregarQuitar, "cardQuitar");
            }
            else {
                n1 = 2;
                jCheckBoxConSin.setText("sin");
                cardLayout.show(jPanelAgregarQuitar, "cardAgregar");
            }
        }
        cargarDieta(n1);
        System.out.println("evDieta "+dieta);
        cargarComida(n2);
    }

    private void cargarComida(int n) {
//        if(n>0)listModelComida.clear();
        System.out.println("index Dieta: "+jListDieta.getSelectedIndex());
        System.out.println("nComida: "+n);
        switch (n) {
            case 0:
                comidas = comidaData.obtenerComidas();
                break;
            case 1:
                System.out.println("switchComida: "+jListDieta.getSelectedIndex());
                comidas = dietaComidaData.ListarComidasEnDieta(jListDieta.getSelectedIndex());  //(dieta.getIdDieta());  //(jListDieta.getSelectedIndex());
                break;
            case 2:
                comidas = dietaComidaData.ListarNoComidasEnDieta(jListDieta.getSelectedIndex());    //(dieta.getIdDieta());    //(jListDieta.getSelectedIndex());
        }
        comidas.forEach(c -> listModelComida.addElement(c));
        
    }

    private void cargarDieta(int n) {
//        if(n>0)listModelDieta.clear();
        System.out.println("nDieta: "+n);
        switch (n) {
            case 0:
                dietas = dietaData.listarDietas();
                System.out.println("dietaSwitch: "+dieta);
                break;
            case 1:
                dietas = dietaComidaData.ListarDietasDeComida(comida.getIdComida());   //(jListComida.getSelectedIndex());
                break;
            case 2:
                dietas = dietaComidaData.ListarNoDietasDeComida(comida.getIdComida());  //(jListComida.getSelectedIndex());
        }
        dietas.forEach(d -> listModelDieta.addElement(d));
    }

    public void salirComidaDietaActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    public void nuevoComidaDietaActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void eliminarComidaDietaActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void guardarComidaDietaActionPerformed(ActionEvent evt) {
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
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonSalirMouseExited(MouseEvent evt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buttonSalirFocusLost(FocusEvent evt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void labelVolverMouseClicked(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void listComidaMouseClicked(MouseEvent evt) {
//        if (evt.getClickCount() == 2) {
        selectedIndexComida = jListComida.getSelectedIndex();
        if (selectedIndexComida >= 0) {
            comida = (Comida) jListComida.getModel().getElementAt(selectedIndexComida);
            System.out.println("Comida seleccionada: " + comida);
        if (n1 > 0) {
            listModelDieta.clear();
            evaluarCondiciones();
        }
        if(comida != null && dieta != null) System.out.println("horario: "+dietaComidaData.obtenerHorarioDietaComida(comida.getIdComida(), dieta.getIdDieta()));
        if(comida != null && dieta != null) jComboBoxHorario.setSelectedItem(dietaComidaData.obtenerHorarioDietaComida(comida.getIdComida(), dieta.getIdDieta()));
        }
//        }
        
    }

    public void listDietaMouseClicked(MouseEvent evt) {
//        if (evt.getClickCount() == 2) {
        selectedIndexDieta = jListDieta.getSelectedIndex();
        if (selectedIndexDieta >= 0) {
            dieta = (Dieta) jListDieta.getModel().getElementAt(selectedIndexDieta);
            System.out.println("Dieta seleccionada: " + dieta);
        if (n2 > 0) {
            listModelComida.clear();
            evaluarCondiciones();
        }
        if(comida != null && dieta != null) System.out.println("horario: "+dietaComidaData.obtenerHorarioDietaComida(comida.getIdComida(), dieta.getIdDieta()));
        if(comida != null && dieta != null) jComboBoxHorario.setSelectedItem(dietaComidaData.obtenerHorarioDietaComida(comida.getIdComida(), dieta.getIdDieta()));
        }
//        }
    }

    public void checkBoxComidaDietaActionPerformed(ActionEvent evt) {
        evaluarCondiciones();
    }

    public void CheckBoxConSinActionPerformed(ActionEvent evt) {
        evaluarCondiciones();
    }

}
