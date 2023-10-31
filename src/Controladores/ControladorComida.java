/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import AccesoADatos.ComidaData;
import Entidades.Comida;
import Util.ColorRGBError;
import Util.Efecto;
import Util.ManejadorAnimacionLBL;
import Util.SetConnValues;
import Util.Validador;
import Vistas.ComidaPanel;
import Vistas.ComidaPanelMain;
import java.awt.CardLayout;
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
    private final String driverDB = SetConnValues.getTipoDB();

    private final ComidaPanelMain comidaPanelMain;
    private final ComidaPanel comidaPanel;
    private Comida comida;
    
    private final JTextField jTextFieldCodigoComida;
    private final JTextField jTextFieldNombre;
    private final JTextField jTextFieldCalorias;
    private final JTextArea jTextDetalle;
    
    private final JButton jButtonLimpiarForm;
    private final JButton jButtonBuscar;
    private JButton jButtonEliminar;
    private JButton jButtonGuardar;
    private JButton jButtonNuevo;
    private JButton jButtonSalir;
    
    private final JLabel jLabelVolver;
    private final JLabel jLabelCodigoComida;
    private final JLabel jLabelNombre;
    private final JLabel jLabelCalorias;
    private final JLabel jLabelDetalle;
    private final JLabel jLabelEstado;
    
    private final JCheckBox jCheckBoxEstado;

//    private AnimacionLBL animacionLBL;
    private final ColorRGBError crgbe;
    private final ManejadorAnimacionLBL manejadorAnimacionLBL;
    private final Efecto efecto;

    private boolean isFound;
    private boolean isCodComidaModified;
    private boolean isNombreModified;
    private boolean isCaloriasModified;
    private boolean isDetalleModified;
    private boolean isEstadoModified;
    
    private boolean bufferState;
    private boolean nuevoMouseEntered;
    private boolean eliminarMouseEntered;
    private boolean guardarMouseEntered;
    private boolean salirMouseEntered;
    
    private boolean isThreadCodComida;
    private boolean isThreadNombre;
    private boolean isThreadCalorias;
    private boolean isThreadDetalle;
    
    private boolean isOkCodComida;
    private boolean isOkNombre;
    private boolean isOkCalorias;
    private boolean isOkDetalle;
//    private boolean isOk;
    private int desdePanel;

    public ControladorComida(ComidaPanel comidaPanel) {
        this.comidaPanel = comidaPanel;
        comidaPanelMain = comidaPanel.getComidaPanelMain();
        comida = null;
        
        jTextFieldCodigoComida = comidaPanel.getjTextFieldCodigoComida();
        jTextFieldNombre = comidaPanel.getjTextFieldNombre();
        jTextDetalle = comidaPanel.getjTextDetalle();
        jTextFieldCalorias = comidaPanel.getjTextFieldCalorias();
        
        jButtonLimpiarForm = comidaPanel.getjButtonLimpiarForm();
        jButtonBuscar = comidaPanel.getjButtonBuscar();
        jButtonEliminar = comidaPanel.getjButtonEliminar();
        jButtonGuardar = comidaPanel.getjButtonGuardar();
        jButtonNuevo = comidaPanel.getjButtonNuevo();
        jButtonSalir = comidaPanel.getjButtonSalir();
        
        jLabelVolver = comidaPanel.getjLabelVolver();
        jLabelCodigoComida = comidaPanel.getjLabelCodigoComida();
        jLabelNombre = comidaPanel.getjLabelNombre();
        jLabelDetalle = comidaPanel.getjLabelDetalle();
        jLabelCalorias = comidaPanel.getjLabelCalorias();
        jLabelEstado = comidaPanel.getjLabelEstado();
        
        jCheckBoxEstado = comidaPanel.getjCheckBoxEstado();
        
        efecto = new Efecto();
        efecto.animacionBuscar(jButtonBuscar, 40);

        nuevoMouseEntered = false;
        eliminarMouseEntered = false;
        guardarMouseEntered = false;
        salirMouseEntered = false;

        isThreadCodComida = false;
        isThreadNombre = false;
        isThreadCalorias = false;

        crgbe = new ColorRGBError();
        manejadorAnimacionLBL = new ManejadorAnimacionLBL();

        isOkCodComida = false;
        isOkNombre = false;
        isOkCalorias = false;
//        isOk = isOkCodComida&&isOkCalorias&&isOkNombre&& jCheckBoxEstado.isSelected();;
        cambiosEnCampos();
        setEnabledJBttnNuevo();
        limpiarForm();
    }

    private void limpiarForm() {
        jTextFieldCodigoComida.setText("");
        jTextFieldNombre.setText("");
        jTextDetalle.setText("");
        jTextFieldCalorias.setText("");
        jCheckBoxEstado.setSelected(true);
        jButtonGuardar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonNuevo.setEnabled(false);
        jButtonBuscar.setEnabled(false);
        jButtonLimpiarForm.setEnabled(false);
        jTextFieldCodigoComida.setEditable(true);

    }

    private void estadoBttnGuardar() {
        jButtonGuardar.setEnabled(isFound && (isCodComidaModified || isCaloriasModified || isNombreModified));
        isCodComidaModified = false;
        isCaloriasModified = false;
        isNombreModified = false;
    }
    
    private void setEnabledJBttnNuevo() {
        jButtonNuevo.setEnabled(isOkNombre && isOkDetalle && isOkCalorias && jCheckBoxEstado.isSelected());
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
        
        jTextDetalle.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDetalleCambiado(e);
            }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDetalleCambiado(e);
            }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDetalleCambiado(e);
            }
        });
    }
    
    public void buttonLimpiarFormActionPerformed(ActionEvent evt) {
        limpiarForm();
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
            setComidaForm(comida);
            jTextFieldCodigoComida.setEditable(false);
        } else {
            isFound = false;
        }
    }

    public void nuevoComidaActionPerformed(java.awt.event.ActionEvent evt) {
        nuevoOGuardar();
    }

    public void guardarComidaActionPerformed(java.awt.event.ActionEvent evt) {
        nuevoOGuardar();
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
        jCheckBoxEstado.setText(jCheckBoxEstado.isSelected()?"Activa":"Inactiva");
        if (isFound) {
            if (bufferState != jCheckBoxEstado.isSelected()) {
                isEstadoModified = true; //(comida.getDni()!=0&&prevState!=jCheckBoxEstado.isSelected())?true:false;
            } else {
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
        if (!jTextFieldCodigoComida.getText().equals("")) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarNumeroEntero(jTextFieldCodigoComida.getText())&&jTextFieldCodigoComida.getText().length()<11) {
                if (comida == null) {
                    isCodComidaModified = true;
                    isOkCodComida = true;
                } else {
                    isCodComidaModified = ("" + comida.getIdComida()).equals(jTextFieldCodigoComida.getText()); //!= dni;
                    isOkCodComida = true;
//                estadoBttnGuardar();
                }
                if (isThreadCodComida) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelCodigoComida);
                    isThreadCodComida = !isThreadCodComida;
                }
            } else {
                isOkCodComida = false;
                if (!isThreadCodComida) {
                    manejadorAnimacionLBL.crearAnimacion("lblcodcomida", jLabelCodigoComida, 3, crgbe);
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
        setEnabledJBttnNuevo();
        setEditableTextFieldCodComida();
        jButtonBuscar.setEnabled(isOkCodComida);
    }

    public void textoEnCaloriasCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldCalorias.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarNumeroDecimal(jTextFieldCalorias.getText()) && jTextFieldCalorias.getText().length() < 11) {
                if (comida == null) {
                    isCaloriasModified = true;
                    isOkCalorias = true;
                } else {
                    String fieldCalorias = jTextFieldCalorias.getText().replace(',', '.');
                    isCaloriasModified = (!("" + comida.getCalorias()).equals(fieldCalorias));
                    isOkCalorias = true;
                    estadoBttnGuardar();
                }
//            if (jTextFieldCalorias.getText().equals("")) {
//                isOkCalorias = false;
//            }
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
        } else {
            isOkCalorias = false;
            if (isThreadCalorias) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelCalorias);
                isThreadCalorias = !isThreadCalorias;
            }
        }
        setEnabledJBttnNuevo();
        setEditableTextFieldCodComida();
    }

    public void textoEnNombreCambiado(javax.swing.event.DocumentEvent e) {
        if (Validador.validarTextoYEspacio(jTextFieldNombre.getText())&&jTextFieldNombre.getText().length()<100) {
            isOkNombre = true;
            if (comida != null) {
                isNombreModified = !comida.getNombre().equals(jTextFieldNombre.getText());
                estadoBttnGuardar();
            }
            if (jTextFieldNombre.getText().isEmpty()) {
                isOkNombre = false;
            } else jButtonLimpiarForm.setEnabled(true);
            if (isThreadNombre) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelNombre);
                isThreadNombre = !isThreadNombre;
            }
        } else {
            isOkNombre = false;
            if (!isThreadNombre) {
                manejadorAnimacionLBL.crearAnimacion("nombre", jLabelNombre, 3, crgbe);
                isThreadNombre = !isThreadNombre;
            }
        }
        setEnabledJBttnNuevo();
        setEditableTextFieldCodComida();
    }
    
    public void textoEnDetalleCambiado(javax.swing.event.DocumentEvent e) {
        if (jTextDetalle.getText().length()<501) {
            isOkDetalle = true;
            if (comida != null) {
                isDetalleModified = !comida.getNombre().equals(jTextDetalle.getText());
                estadoBttnGuardar();
            }
            if (jTextDetalle.getText().isEmpty()) {
                isOkDetalle = false;
            } else jButtonLimpiarForm.setEnabled(true);
            if (isThreadDetalle) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelDetalle);
                isThreadDetalle = !isThreadDetalle;
            }
        } else {
            isOkDetalle = false;
            if (!isThreadDetalle) {
                manejadorAnimacionLBL.crearAnimacion("nombre", jLabelDetalle, 3, crgbe);
                isThreadDetalle = !isThreadDetalle;
            }
        }
        setEnabledJBttnNuevo();
        setEditableTextFieldCodComida();
    }

    public void labelVolverMouseClicked(MouseEvent evt) {
        CardLayout cardLayout = comidaPanelMain.getCardLayout();
        desdePanel = comidaPanel.getDesdePanel();
        switch (desdePanel) {
            case 0:
                cardLayout.show(comidaPanelMain, "comidaMenu");
                break;
            case 1:
                cardLayout.show(comidaPanelMain, "comidaDietaAdv");
        }
    }

    public void ButtonLimpiarFormActionPerformed(ActionEvent evt) {
        limpiarForm();
    }

    public void setComidaForm(Comida comida) {
        this.comida = comida;
        if(jTextFieldCodigoComida.getText().isEmpty()) jTextFieldCodigoComida.setText(comida.getIdComida()+"");
        jTextFieldNombre.setText(comida.getNombre());
        jTextDetalle.setText(comida.getDetalle());
        jTextFieldCalorias.setText("" + comida.getCalorias());
        jCheckBoxEstado.setSelected(comida.isEstado());
        jButtonEliminar.setEnabled(comida.isEstado());
        jButtonNuevo.setEnabled(false);
        isFound = true;
        bufferState = comida.isEstado();
    }

    private void nuevoOGuardar() {
//        comida.setIdComida(Integer.parseInt(jTextFieldCodigoComida.getText()));
        if (jButtonNuevo.isEnabled() && !jButtonGuardar.isEnabled()) comida = new Comida();
        comida.setNombre(jTextFieldNombre.getText());
        comida.setDetalle(jTextDetalle.getText());
        String fieldCalorias = jTextFieldCalorias.getText().replace(',', '.');
        comida.setCalorias(Double.parseDouble(fieldCalorias));
        comida.setEstado(jCheckBoxEstado.isSelected());
        ComidaData comidaData = null;
        try {
            comidaData = new ComidaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(ComidaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (jButtonNuevo.isEnabled() && !jButtonGuardar.isEnabled()) if (comidaData.guardarComida(comida)) limpiarForm();
        if (!jButtonNuevo.isEnabled() && jButtonGuardar.isEnabled()) if (comidaData.modificarComida(comida)) limpiarForm();
    }

    private void setEditableTextFieldCodComida() {
        if(!(isOkCodComida||isOkNombre||isOkCalorias||isOkDetalle)) jTextFieldCodigoComida.setEditable(true);
    }

}
