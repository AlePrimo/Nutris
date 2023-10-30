/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import AccesoADatos.ComidaData;
import Entidades.Comida;
import Util.AnimacionLBL;
import Util.ColorRGBError;
import Util.Efecto;
import Util.ManejadorAnimacionLBL;
import Util.SetConnValues;
import Util.Validador;
import Vistas.ComidaPanel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import nutris.Conexion;

/**
 *
 * @author Administrador
 */
public class ControladorComida {
    private String driverDB = SetConnValues.getTipoDB();

    private final ComidaPanel comidaPanel;
    private Comida comida;
    
    private final JTextField jTextFieldCodigoComida;
    private final JTextArea jTextDetalle;
    private final JTextField jTextFieldNombre;
    private final JTextField jTextFieldCalorias;
    
    private final JButton jButtonBuscar;
    private JButton jButtonEliminar;
    private JButton jButtonGuardar;
    private JButton jButtonNuevo;
    private JButton jButtonSalir;
    
    private final JLabel jLabelCalorias;
    private final JLabel jLabelCodigoComida;
    private final JLabel jLabelDetalle;
    private final JLabel jLabelNombre;
    private final JLabel jLabelEstado;
    
    private final JCheckBox jCheckBoxEstado;

    private AnimacionLBL animacionLBL;
    private final ColorRGBError crgbe;
    private final ManejadorAnimacionLBL manejadorAnimacionLBL;
    private final Efecto efecto;

    private boolean isFound;
    private boolean isCodComidaModified;
    private boolean isCaloriasModified;
    private boolean isNombreModified;
    private boolean isEstadoModified;
    private boolean isFechaNacModified;
    private boolean bufferState;
    private boolean nuevoMouseEntered;
    private boolean eliminarMouseEntered;
    private boolean guardarMouseEntered;
    private boolean salirMouseEntered;
    private boolean isThreadCodComida;
    private boolean isThreadCalorias;
    private boolean isThreadNombre;
    private boolean isOkCodComida;
    private boolean isOkCalorias;
    private boolean isOkNombre;
    private boolean isOk;

    public ControladorComida(ComidaPanel comidaPanel) {
        this.comidaPanel = comidaPanel;
        comida = new Comida();
        
        jTextFieldCodigoComida = comidaPanel.getjTextFieldCodigoComida();
        jTextDetalle = comidaPanel.getjTextDetalle();
        jTextFieldNombre = comidaPanel.getjTextFieldNombre();
        jTextFieldCalorias = comidaPanel.getjTextFieldCalorias();
        
        jButtonBuscar = comidaPanel.getjButtonBuscar();
        jButtonEliminar = comidaPanel.getjButtonEliminar();
        jButtonGuardar = comidaPanel.getjButtonGuardar();
        jButtonNuevo = comidaPanel.getjButtonNuevo();
        jButtonSalir = comidaPanel.getjButtonSalir();
        
        jLabelCalorias = comidaPanel.getjLabelCalorias();
        jLabelCodigoComida = comidaPanel.getjLabelCodigoComida();
        jLabelDetalle = comidaPanel.getjLabelDetalle();
        jLabelNombre = comidaPanel.getjLabelNombre();
        jLabelEstado = comidaPanel.getjLabelEstado();
        
        jCheckBoxEstado = comidaPanel.getjCheckBoxEstado();
        
        efecto = new Efecto();
        efecto.animacionBuscar(jButtonBuscar, 40);

        nuevoMouseEntered = false;
        eliminarMouseEntered = false;
        guardarMouseEntered = false;
        salirMouseEntered = false;

        isThreadCodComida = false;
        isThreadCalorias = false;
        isThreadNombre = false;

        crgbe = new ColorRGBError();
        manejadorAnimacionLBL = new ManejadorAnimacionLBL();

        isOkCodComida = false;
        isOkCalorias = false;
        isOkNombre = false;
        isOk = isOkCodComida&&isOkCalorias&&isOkNombre&& jCheckBoxEstado.isSelected();;
        cambiosEnCampos();
        limpiarForm();
    }

    private void limpiarForm() {
        jTextFieldCodigoComida.setText("");
        jTextFieldNombre.setText("");
        jTextDetalle.setText("");
        jTextFieldCalorias.setText("");
        jButtonGuardar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonNuevo.setEnabled(false);
        jButtonBuscar.setEnabled(false);

    }

    private void estadoBttnGuardar() {
        jButtonGuardar.setEnabled(isFound && (isCodComidaModified || isCaloriasModified || isNombreModified));
        isCodComidaModified = false;
        isCaloriasModified = false;
        isNombreModified = false;
    }

    private void cambiosEnCampos() {
        jTextFieldCodigoComida.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
               textoEnCodigoComidaCambiado(e);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCodigoComidaCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCodigoComidaCambiado(e);
            }
        });
        jTextFieldCalorias.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCaloriasCambiado(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCaloriasCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCaloriasCambiado(e);
            }
        });

        jTextFieldNombre.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnNombreCambiado(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnNombreCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnNombreCambiado(e);
            }
        });
        
        jTextFieldCalorias.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCaloriasCambiado(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCaloriasCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCaloriasCambiado(e);
            }
        });
    }

    public void salirComidaActionPerformed(java.awt.event.ActionEvent evt) {
//        comidaPanel.dispose();
        System.exit(0);
    }

    public void buscarComidaActionPerformed(java.awt.event.ActionEvent evt) {
        ComidaData comidaData = null;
        try {
            comidaData = new ComidaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
//            Logger.getLogger(ComidaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        comida = comidaData.buscarComida(Integer.parseInt(jTextFieldCodigoComida.getText()));

        if (comida != null) {
            jTextFieldNombre.setText(comida.getNombre());
            jTextDetalle.setText(comida.getDetalle());
            jTextFieldCalorias.setText(""+comida.getCalorias());
            jCheckBoxEstado.setSelected(comida.isEstado());
            jButtonEliminar.setEnabled(comida.isEstado());
            jButtonNuevo.setEnabled(false);
            isFound = true;
            bufferState = comida.isEstado();
        } else {
            isFound = false;
        }
    }

    public void nuevoComidaActionPerformed(java.awt.event.ActionEvent evt) {
        comida.setIdComida(Integer.parseInt(jTextFieldCodigoComida.getText()));
        comida.setNombre(jTextFieldNombre.getText());
        comida.setDetalle(jTextDetalle.getText());
        comida.setCalorias(Double.parseDouble(jTextFieldCalorias.getText()));
        comida.setEstado(jCheckBoxEstado.isSelected());
        ComidaData comidaData = null;
        try {
            comidaData = new ComidaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(ComidaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (comidaData.guardarComida(comida)) {
            limpiarForm();
        }
    }

    public void eliminarComidaActionPerformed(java.awt.event.ActionEvent evt) {
        ComidaData comidaData = null;
        try {
            comidaData = new ComidaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(ComidaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (comidaData.borrarComida(comida.getIdComida())) {
            limpiarForm();
        }
    }

    public void guardarComidaActionPerformed(java.awt.event.ActionEvent evt) {
        comida.setIdComida(Integer.parseInt(jTextFieldCodigoComida.getText()));
        comida.setDetalle(jTextDetalle.getText());
        comida.setNombre(jTextFieldNombre.getText());
        comida.setCalorias(Double.parseDouble(jTextFieldCalorias.getText()));
        comida.setEstado(jCheckBoxEstado.isSelected());
        ComidaData comidaData = null;
        try {
            comidaData = new ComidaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(ComidaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (comidaData.modificarComida(comida)) {
            limpiarForm();
        }
    }

    public void buttonBuscarMouseEntered(java.awt.event.MouseEvent evt) {
        efecto.animacionBuscar(jButtonBuscar, 50);

    }

    public void buttonBuscarMouseExited(java.awt.event.MouseEvent evt) {
        efecto.animacionBuscar(jButtonBuscar, 40);

    }

    public void buttonBuscarMousePressed(java.awt.event.MouseEvent evt) {
        efecto.animacionBuscar(jButtonBuscar, 20);
    }

    public void buttonBuscarMouseClicked(java.awt.event.MouseEvent evt) {
        efecto.animacionBuscar(jButtonBuscar, 50);
    }

    public void buttonBuscarFocusGained(java.awt.event.FocusEvent evt) {
        efecto.animacionBuscar(jButtonBuscar, 50);
    }

    public void buttonBuscarFocusLost(java.awt.event.FocusEvent evt) {
        efecto.animacionBuscar(jButtonBuscar, 40);
    }

    public void buttonBuscarKeyTyped(java.awt.event.KeyEvent evt) {
        efecto.animacionBuscar(jButtonBuscar, 20);
    }

    public void buttonBuscarKeyReleased(java.awt.event.KeyEvent evt) {
        efecto.animacionBuscar(jButtonBuscar, 40);
    }

    public void checkBoxEstadoActionPerformed(java.awt.event.ActionEvent evt) {
        if (isFound) {
            if (bufferState != jCheckBoxEstado.isSelected()) {
                isEstadoModified = true; //(comida.getDni()!=0&&prevState!=jCheckBoxEstado.isSelected())?true:false;
                System.out.println("isEstadoModified=" + isEstadoModified);
            } else {
                System.out.println("HOLA");
                isEstadoModified = false;
            }
            estadoBttnGuardar();
        }
    }

    public void buttonNuevoMouseEntered(java.awt.event.MouseEvent evt) {
        nuevoMouseEntered = true;
        jButtonNuevo = efecto.efectoBttn(jButtonNuevo, jButtonNuevo.isFocusOwner(), 1, 1);
    }

    public void buttonNuevoMouseExited(java.awt.event.MouseEvent evt) {
        nuevoMouseEntered = false;
        jButtonNuevo = efecto.efectoBttn(jButtonNuevo, jButtonNuevo.isFocusOwner(), -1, 3);
    }

    public void buttonEliminarMouseEntered(java.awt.event.MouseEvent evt) {
        eliminarMouseEntered = true;
        jButtonEliminar = efecto.efectoBttn(jButtonEliminar, jButtonEliminar.isFocusOwner(), 1, 1);
    }

    public void buttonEliminarMouseExited(java.awt.event.MouseEvent evt) {
        eliminarMouseEntered = false;
        jButtonEliminar = efecto.efectoBttn(jButtonEliminar, jButtonEliminar.isFocusOwner(), -1, 3);
    }

    public void buttonGuardarMouseEntered(java.awt.event.MouseEvent evt) {
        guardarMouseEntered = true;
        jButtonGuardar = efecto.efectoBttn(jButtonGuardar, jButtonGuardar.isFocusOwner(), 1, 1);
    }

    public void buttonGuardarMouseExited(java.awt.event.MouseEvent evt) {
        guardarMouseEntered = false;
        jButtonGuardar = efecto.efectoBttn(jButtonGuardar, jButtonGuardar.isFocusOwner(), -1, 3);
    }

    public void buttonSalirMouseEntered(java.awt.event.MouseEvent evt) {
        salirMouseEntered = true;
        jButtonSalir = efecto.efectoBttn(jButtonSalir, jButtonSalir.isFocusOwner(), 1, 2);
    }

    public void buttonSalirMouseExited(java.awt.event.MouseEvent evt) {
        salirMouseEntered = false;
        jButtonSalir = efecto.efectoBttn(jButtonSalir, jButtonSalir.isFocusOwner(), -1, 3);
    }

    public void buttonNuevoFocusGained(java.awt.event.FocusEvent evt) {
        jButtonNuevo = efecto.efectoBttn(jButtonNuevo, nuevoMouseEntered, 1, 1);
    }

    public void buttonNuevoFocusLost(java.awt.event.FocusEvent evt) {
        jButtonNuevo = efecto.efectoBttn(jButtonNuevo, nuevoMouseEntered, -1, 3);
    }

    public void buttonEliminarFocusGained(java.awt.event.FocusEvent evt) {
        jButtonEliminar = efecto.efectoBttn(jButtonEliminar, eliminarMouseEntered, 1, 1);
    }

    public void buttonEliminarFocusLost(java.awt.event.FocusEvent evt) {
        jButtonEliminar = efecto.efectoBttn(jButtonEliminar, eliminarMouseEntered, -1, 3);
    }

    public void buttonGuardarFocusGained(java.awt.event.FocusEvent evt) {
        jButtonGuardar = efecto.efectoBttn(jButtonGuardar, guardarMouseEntered, 1, 1);
    }

    public void buttonGuardarFocusLost(java.awt.event.FocusEvent evt) {
        jButtonGuardar = efecto.efectoBttn(jButtonGuardar, guardarMouseEntered, -1, 3);
    }

    public void buttonSalirFocusGained(java.awt.event.FocusEvent evt) {
        jButtonSalir = efecto.efectoBttn(jButtonSalir, salirMouseEntered, 1, 2);

    }

    public void buttonSalirFocusLost(java.awt.event.FocusEvent evt) {
        jButtonSalir = efecto.efectoBttn(jButtonSalir, salirMouseEntered, -1, 3);
    }

    public void textoEnCodigoComidaCambiado(javax.swing.event.DocumentEvent e) {
//        int dni;
        if (!jTextFieldCodigoComida.getText().equals("")) {
            if (Validador.validarNumeroEntero(jTextFieldCodigoComida.getText())) {
                isCodComidaModified = (""+comida.getIdComida()).equals(jTextFieldCodigoComida.getText()); //!= dni;
                isOkCodComida = true;
                estadoBttnGuardar();
                if (isThreadCodComida) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelCodigoComida);
                    isThreadCodComida = !isThreadCodComida;
                }
            } else {
                isOkCodComida = false;
                if (!isThreadCodComida) {
                    manejadorAnimacionLBL.crearAnimacion("lblcodcomida", jLabelCodigoComida, 3, crgbe);
                    manejadorAnimacionLBL.crearAnimacion("txtfielcodcomida", jTextFieldCodigoComida, 3, crgbe);
                    
                    isThreadCodComida = !isThreadCodComida;
                }
            }
        } else {
            isOkCodComida = false;
            if (isThreadCodComida) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelCodigoComida);
                    isThreadCodComida = !isThreadCodComida;
                }
        }
        isOk = isOkCodComida && isOkCalorias && isOkNombre && jCheckBoxEstado.isSelected();
        jButtonNuevo.setEnabled(isOk);
        jButtonBuscar.setEnabled(isOkCodComida);
    }

    public void textoEnCaloriasCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldCalorias.getText().equals("")){
        if (Validador.validarNumeroDecimal(jTextFieldCalorias.getText())) {
            isOkCalorias = true;

            String fieldCalorias = jTextFieldCalorias.getText().replace(',', '.');
            isCaloriasModified = (!("" + comida.getCalorias()).equals(fieldCalorias));
            estadoBttnGuardar();

            if (jTextFieldCalorias.getText().equals("")) {
                isOkCalorias = false;
            }
            if (isThreadCalorias) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelCalorias);
                isThreadCalorias = !isThreadCalorias;
            }
        } else {
            isOkCalorias = false;
            if (!isThreadCalorias) {
                manejadorAnimacionLBL.crearAnimacion("calorias", jLabelCalorias, 3, crgbe);
                isThreadCalorias = !isThreadCalorias;
            }
        }
        }else{
            isOkCalorias = false;
            if(isThreadCalorias){
                manejadorAnimacionLBL.detenerAnimacion(jLabelCalorias);
                isThreadCalorias = !isThreadCalorias;
            }
        }
        isOk = isOkCodComida && isOkCalorias && isOkNombre && jCheckBoxEstado.isSelected();
        jButtonNuevo.setEnabled(isOk);
    }

    public void textoEnNombreCambiado(javax.swing.event.DocumentEvent e) {
        if (Validador.validarTextoYEspacio(jTextFieldNombre.getText())) {
            isOkNombre = true;
            if (comida.getNombre() != null) {
                isNombreModified = (!comida.getNombre().equals(jTextFieldNombre.getText()));
                estadoBttnGuardar();
            }
            if (jTextFieldNombre.getText().equals("")) {
                isOkNombre = false;
            }
            if (isThreadNombre) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelNombre);
                isThreadNombre = !isThreadNombre;
            }
        } else {
            isOkNombre = false;
//            System.out.println("isThreadNombre: "+ isThreadNombre);
            if (!isThreadNombre) {
                manejadorAnimacionLBL.crearAnimacion("nombre", jLabelNombre, 3, crgbe);
                isThreadNombre = !isThreadNombre;
            }
        }
        isOk = isOkCodComida && isOkCalorias && isOkNombre && jCheckBoxEstado.isSelected();
        jButtonNuevo.setEnabled(isOk);
    }

    public void labelVolverMouseClicked(MouseEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ButtonLimpiarFormActionPerformed(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setComidaForm(Comida c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
