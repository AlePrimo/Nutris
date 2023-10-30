/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import AccesoADatos.PacienteData;
import Entidades.Paciente;
import Util.ColorRGBError;
import Util.Efecto;
import Util.ManejadorAnimacionLBL;
import Util.SetConnValues;
import Util.Validador;
import Vistas.PacientePanel;
import Vistas.PacientePanelMain;
import com.toedter.calendar.JDateChooser;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import nutris.Conexion;

/**
 *
 * @author Administrador
 */
public class ControladorPaciente {
    
    private String driverDB = SetConnValues.getTipoDB();

    private final PacientePanel pacientePanel;
    private PacientePanelMain pacientePanelMain;
    private Paciente paciente;

    private final JTextField jTextFieldDni;
    private final JTextField jTextFieldApellido;
    private final JTextField jTextFieldNombre;
    private final JTextField jTextFieldDomicilio;
    private final JTextField jTextFieldTelefono;
    private final JTextField jTextFieldCelular;
    private final JTextField jTextFieldEmail;
    private final JTextField jTextFieldAltura;
    private final JTextField dateTextField;

    private final JCheckBox jCheckBoxEstado;

    private final JDateChooser jDateChooserFNac;

    private final JButton jButtonLimpiarForm;
    private final JButton jButtonBuscar;
    private JButton jButtonEliminar;
    private JButton jButtonGuardar;
    private JButton jButtonNuevo;
    private JButton jButtonSalir;

    private final JLabel jLabelVolver;
    private final JLabel jLabelDni;
    private final JLabel jLabelApellido;
    private final JLabel jLabelNombre;
    private final JLabel jLabelDomicilio;
    private final JLabel jLabelTelefono;
    private final JLabel jLabelCelular;
    private final JLabel jLabelEmail;
    private final JLabel jLabelAltura;
    private final JLabel jLabelFechaNac;
    private final JLabel jLabelEstado;

    private final ColorRGBError crgbe;
    private final ManejadorAnimacionLBL manejadorAnimacionLBL;
    private final Efecto efecto;

    private boolean isFound;
    private boolean isDniModified;
    private boolean isApellidoModified;
    private boolean isNombreModified;
    private boolean isEstadoModified;
    private boolean isFechaNacModified;
    private boolean bufferState;
    private boolean nuevoMouseEntered;
    private boolean eliminarMouseEntered;
    private boolean guardarMouseEntered;
    private boolean salirMouseEntered;
    private boolean isThreadDni;
    private boolean isThreadApellido;
    private boolean isThreadNombre;
    private boolean isOkDni;
    private boolean isOkApellido;
    private boolean isOkNombre;
//    private boolean isOk;
    private boolean isOkFecha;
    private boolean isThreadFecha;
    private boolean isOkDomicilio;
    private boolean isDomicilioModified;
    private boolean isThreadDomicilio;
    private boolean isTelModified;
    private boolean isOkTel;
    private boolean isThreadTel;
    private boolean isCelModified;
    private boolean isOkCel;
    private boolean isThreadCel;
    private boolean isOkEmail;
    private boolean isEmailModified;
    private boolean isThreadEmail;
    private boolean isOkAltura;
    private boolean isAlturaModified;
    private boolean isThreadAltura;
    private int desdePanel;

    public ControladorPaciente(PacientePanel pacientePanel) {
        pacientePanelMain = pacientePanel.getPacientePanelMain();
        this.pacientePanel = pacientePanel;
        paciente = null;

        jTextFieldDni = pacientePanel.getjTextFieldDni();
        jTextFieldApellido = pacientePanel.getjTextFieldApellido();
        jTextFieldNombre = pacientePanel.getjTextFieldNombre();
        jTextFieldDomicilio = pacientePanel.getjTextFieldDomicilio();
        jTextFieldTelefono = pacientePanel.getjTextFieldTelefono();
        jTextFieldCelular = pacientePanel.getjTextFieldCelular();
        jTextFieldEmail = pacientePanel.getjTextFieldEmail();
        jTextFieldAltura = pacientePanel.getjTextFieldAltura();
        jDateChooserFNac = pacientePanel.getjDateChooserFNac();
        dateTextField = (JTextField) jDateChooserFNac.getDateEditor().getUiComponent();
        jCheckBoxEstado = pacientePanel.getjCheckBoxEstado();

        jButtonLimpiarForm = pacientePanel.getjButtonLimpiarForm();
        jButtonBuscar = pacientePanel.getjButtonBuscar();
        jButtonEliminar = pacientePanel.getjButtonEliminar();
        jButtonGuardar = pacientePanel.getjButtonGuardar();
        jButtonNuevo = pacientePanel.getjButtonNuevo();
        jButtonSalir = pacientePanel.getjButtonSalir();

        jLabelVolver = pacientePanel.getjLabelVolver();
        jLabelDni = pacientePanel.getjLabelDni();
        jLabelApellido = pacientePanel.getjLabelApellido();
        jLabelNombre = pacientePanel.getjLabelNombre();
        jLabelDomicilio = pacientePanel.getjLabelDomicilio();
        jLabelTelefono = pacientePanel.getjLabelTelefono();
        jLabelCelular = pacientePanel.getjLabelCelular();
        jLabelEmail = pacientePanel.getjLabelEmail();
        jLabelAltura = pacientePanel.getjLabelAltura();
        jLabelFechaNac = pacientePanel.getjLabelFechaNac();
        jLabelEstado = pacientePanel.getjLabelEstado();

        desdePanel = pacientePanel.getDesdePanel();

        efecto = new Efecto();
        efecto.animacionBuscar(jButtonBuscar, 40);

        nuevoMouseEntered = false;
        eliminarMouseEntered = false;
        guardarMouseEntered = false;
        salirMouseEntered = false;

        isThreadDni = false;
        isThreadApellido = false;
        isThreadNombre = false;

        crgbe = new ColorRGBError();
        manejadorAnimacionLBL = new ManejadorAnimacionLBL();

        isOkDni = false;
        isOkApellido = false;
        isOkNombre = false;
        isOkDomicilio = false;
        isOkTel = false;
        isOkCel = false;
        isOkEmail = false;
        isOkAltura = false;
        isOkFecha = false;
//        isOk = isOkDni&&isOkApellido&&isOkNombre;
        cambiosEnCampos();
        setEnabledJBttnNuevo();
        limpiarForm();
    }

    private void limpiarForm() {
        jTextFieldDni.setText("");
        jTextFieldApellido.setText("");
        jTextFieldNombre.setText("");
        jTextFieldDomicilio.setText("");
        jTextFieldTelefono.setText("");
        jTextFieldCelular.setText("");
        jTextFieldEmail.setText("");
        jTextFieldAltura.setText("");
        dateTextField.setText("");
        jCheckBoxEstado.setSelected(true);
        jButtonGuardar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonNuevo.setEnabled(false);
        jButtonBuscar.setEnabled(false);
        jButtonLimpiarForm.setEnabled(false);
    }

    private void estadoBttnGuardar() {
        jButtonGuardar.setEnabled(isFound && (isDniModified || isApellidoModified || isNombreModified || isDomicilioModified || isTelModified || isCelModified || isEmailModified || isAlturaModified || isEstadoModified || isFechaNacModified));
        isDniModified = false;
        isFechaNacModified = false;
    }

    private void setEnabledJBttnNuevo() {
        jButtonNuevo.setEnabled(isOkDni && isOkApellido && isOkNombre && isOkFecha && isOkAltura && jCheckBoxEstado.isSelected() && (isOkTel || isOkCel || isOkEmail));
    }

    public void setPacienteForm(Paciente paciente) {
        this.paciente = paciente;
//        desdePanel = num;
        if (jTextFieldDni.getText().isEmpty()) {
            jTextFieldDni.setText(paciente.getDni());
        }
        jTextFieldApellido.setText(paciente.getApellido());
        jTextFieldNombre.setText(paciente.getNombre());
        jTextFieldDomicilio.setText(paciente.getDomicilio());
        jTextFieldTelefono.setText(paciente.getTelefonoFijo());
        jTextFieldCelular.setText(paciente.getCelular());
        jTextFieldEmail.setText(paciente.getMail());
        jTextFieldAltura.setText("" + paciente.getAltura());
        Date date = java.sql.Date.valueOf(paciente.getFechaNac());
        jDateChooserFNac.setDate(date);
        jCheckBoxEstado.setSelected(paciente.isEstado());
        jButtonEliminar.setEnabled(paciente.isEstado());
        jButtonNuevo.setEnabled(false);
        isFound = true;
        bufferState = paciente.isEstado();
    }

    public void ButtonLimpiarFormActionPerformed(ActionEvent evt) {
        limpiarForm();
    }

    private void cambiosEnCampos() {
        jTextFieldDni.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDniCambiado(e);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDniCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDniCambiado(e);
            }
        });
        jTextFieldApellido.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnApellidoCambiado(e);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnApellidoCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnApellidoCambiado(e);
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

        jTextFieldDomicilio.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDomicilioCambiado(e);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDomicilioCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDomicilioCambiado(e);
            }
        });

        jTextFieldTelefono.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnTelCambiado(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnTelCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnTelCambiado(e);
            }
        });

        jTextFieldCelular.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCelCambiado(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCelCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCelCambiado(e);
            }
        });

        jTextFieldEmail.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnMailCambiado(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnMailCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnMailCambiado(e);
            }
        });

        jTextFieldAltura.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnAlturaCambiado(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnAlturaCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnAlturaCambiado(e);
            }
        });

        dateTextField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                cambioDeFecha(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                cambioDeFecha(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                cambioDeFecha(e);
            }
        });
    }

    public void salirPacienteActionPerformed(java.awt.event.ActionEvent evt) {
//        pacientePanel.dispose();
        System.exit(0);
    }

    public void buscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {
        PacienteData pacienteData = null;
        try {
            pacienteData = new PacienteData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
//            Logger.getLogger(PacientePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        paciente = pacienteData.buscarPacientePorDni(jTextFieldDni.getText());

        if (paciente != null) {
//            jTextFieldApellido.setText(paciente.getApellido());
//            jTextFieldNombre.setText(paciente.getNombre());
//            jTextFieldDomicilio.setText(paciente.getDomicilio());
//            jTextFieldTelefono.setText(paciente.getTelefonoFijo());
//            jTextFieldCelular.setText(paciente.getCelular());
//            jTextFieldEmail.setText(paciente.getMail());
//            jTextFieldAltura.setText(""+paciente.getAltura());
//            Date date = java.sql.Date.valueOf(paciente.getFechaNac());
//            jDateChooserFNac.setDate(date);
//            jCheckBoxEstado.setSelected(paciente.isEstado());
            setPacienteForm(paciente);
//            jButtonEliminar.setEnabled(paciente.isEstado());
//            jButtonNuevo.setEnabled(false);
//            isFound = true;
//            bufferState = paciente.isEstado();
        } else {
            isFound = false;
        }
    }

    public void nuevoPacienteActionPerformed(java.awt.event.ActionEvent evt) {
        paciente = new Paciente();
        paciente.setDni(jTextFieldDni.getText());
        paciente.setApellido(jTextFieldApellido.getText());
        paciente.setNombre(jTextFieldNombre.getText());
        paciente.setDomicilio(jTextFieldDomicilio.getText());
        paciente.setTelefonoFijo(jTextFieldTelefono.getText());
        paciente.setCelular(paciente.getCelular());
        paciente.setMail(jTextFieldEmail.getText());
        String sFieldAltura = jTextFieldAltura.getText().replace(',', '.');
        double fieldAltura = Double.parseDouble(sFieldAltura);
        paciente.setAltura(fieldAltura);
        java.util.Date date = jDateChooserFNac.getDate();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        paciente.setFechaNac(localDate);
        paciente.setEstado(jCheckBoxEstado.isSelected());
        PacienteData pacienteData = null;
        try {
            pacienteData = new PacienteData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(PacientePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pacienteData.ingresarPaciente(paciente)) {
            limpiarForm();
        }
    }

    public void eliminarPacienteActionPerformed(java.awt.event.ActionEvent evt) {
        PacienteData pacienteData = null;
        try {
            pacienteData = new PacienteData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(PacientePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pacienteData.bajaPaciente(paciente.getIdPaciente())) {
            limpiarForm();
        }
    }

    public void guardarPacienteActionPerformed(java.awt.event.ActionEvent evt) {
        paciente.setDni(jTextFieldDni.getText());
        paciente.setApellido(jTextFieldApellido.getText());
        paciente.setNombre(jTextFieldNombre.getText());
        paciente.setDomicilio(jTextFieldDomicilio.getText());
        paciente.setTelefonoFijo(jTextFieldTelefono.getText());
        paciente.setCelular(paciente.getCelular());
        paciente.setMail(jTextFieldEmail.getText());
        String sFieldAltura = jTextFieldAltura.getText().replace(',', '.');
        double fieldAltura = Double.parseDouble(sFieldAltura);
        paciente.setAltura(fieldAltura);
        java.util.Date date = jDateChooserFNac.getDate();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        paciente.setFechaNac(localDate);
        paciente.setEstado(jCheckBoxEstado.isSelected());
        PacienteData pacienteData = null;
        try {
            pacienteData = new PacienteData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(PacientePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pacienteData.modificarPaciente(paciente)) {
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

    public void dateChooserFNacPropertyChange(java.beans.PropertyChangeEvent evt) {
//        Optional<java.util.Date> fechaAnterior = null;
//        Optional<java.util.Date> nuevaFecha = null;
//        Optional<java.util.Date> fechaOriginal = null;
//        if ("date".equals(evt.getPropertyName())) {
//            Optional<java.util.Date> fechaAnterior = Optional.ofNullable((java.util.Date) evt.getOldValue());
//            Optional<java.util.Date> nuevaFecha = Optional.ofNullable((java.util.Date) evt.getNewValue());
//            Optional<java.util.Date> fechaOriginal = Optional.ofNullable((paciente.getFechaNac() != null) ? java.sql.Date.valueOf(paciente.getFechaNac()) : null);
//            if (nuevaFecha.isPresent() && fechaOriginal.isPresent()) {
//                isFechaNacModified = !(nuevaFecha.get().equals(fechaOriginal.get()));
//                estadoBttnGuardar();
//            }
//        }
    }

    public void checkBoxEstadoActionPerformed(java.awt.event.ActionEvent evt) {
        if (isFound) {
            if (bufferState != jCheckBoxEstado.isSelected()) {
                isEstadoModified = true; //(paciente.getDni()!=0&&prevState!=jCheckBoxEstado.isSelected())?true:false;
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

    public void textoEnDniCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldDni.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarNumeroEntero(jTextFieldDni.getText()) && (jTextFieldDni.getText().length() > 5 && jTextFieldDni.getText().length() < 10)) {
                if (paciente == null) {
                    isDniModified = true;
                    isOkDni = true;
                } else {
                    isDniModified = !paciente.getDni().equals(jTextFieldDni.getText()); //!= dni;
                    isOkDni = true;
                    estadoBttnGuardar();
                }
                if (isThreadDni) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelDni);
                    isThreadDni = !isThreadDni;
                }
            } else {
                isOkDni = false;
                if (!isThreadDni) {
                    manejadorAnimacionLBL.crearAnimacion("lbldni", jLabelDni, 3, crgbe);
                    manejadorAnimacionLBL.crearAnimacion("txtfielddni", jTextFieldDni, 3, crgbe);
                    isThreadDni = !isThreadDni;
                }
            }
        } else {
            isOkDni = false;
            if (isThreadDni) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelDni);
                isThreadDni = !isThreadDni;
            }
        }
        setEnabledJBttnNuevo();
        jButtonBuscar.setEnabled(isOkDni);
    }

    public void textoEnApellidoCambiado(javax.swing.event.DocumentEvent e) {
        if (Validador.validarTextoYEspacio(jTextFieldApellido.getText()) && jTextFieldApellido.getText().length() < 101) {
            isOkApellido = true;
            if (paciente != null) {
                isApellidoModified = (!paciente.getApellido().equalsIgnoreCase(jTextFieldApellido.getText()));
                estadoBttnGuardar();
            }
            if (jTextFieldApellido.getText().isEmpty()) {
                isOkApellido = false;
            } else {
                jButtonLimpiarForm.setEnabled(true);
            }
            if (isThreadApellido) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelApellido);
                isThreadApellido = !isThreadApellido;
            }
        } else {
            isOkApellido = false;
            if (!isThreadApellido) {
                manejadorAnimacionLBL.crearAnimacion("apellido", jLabelApellido, 3, crgbe);
                isThreadApellido = !isThreadApellido;
            }
        }
        setEnabledJBttnNuevo();
    }

    public void textoEnNombreCambiado(javax.swing.event.DocumentEvent e) {
        if (Validador.validarTextoYEspacio(jTextFieldNombre.getText()) && jTextFieldNombre.getText().length() < 101) {
            isOkNombre = true;
            if (paciente != null) {
                isNombreModified = (!paciente.getNombre().equalsIgnoreCase(jTextFieldNombre.getText()));
                estadoBttnGuardar();
            }
            if (jTextFieldNombre.getText().isEmpty()) {
                isOkNombre = false;
            } else {
                jButtonLimpiarForm.setEnabled(true);
            }
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
    }

    public void textoEnDomicilioCambiado(javax.swing.event.DocumentEvent e) {
        if (Validador.validarTextoYEspacioV2(jTextFieldDomicilio.getText()) && jTextFieldDomicilio.getText().length() < 256) {
            isOkDomicilio = true;
            if (paciente != null) {
                isDomicilioModified = (!paciente.getDomicilio().equalsIgnoreCase(jTextFieldDomicilio.getText()));
                estadoBttnGuardar();
            }
            if (jTextFieldDomicilio.getText().isEmpty()) {
                isOkDomicilio = false;
            } else {
                jButtonLimpiarForm.setEnabled(true);
            }
            if (isThreadDomicilio) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelDomicilio);
                isThreadDomicilio = !isThreadDomicilio;
            }
        } else {
            isOkDomicilio = false;
            if (!isThreadDomicilio) {
                manejadorAnimacionLBL.crearAnimacion("domicilio", jLabelDomicilio, 3, crgbe);
                isThreadDomicilio = !isThreadDomicilio;
            }
        }
        setEnabledJBttnNuevo();
    }

    public void textoEnTelCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldTelefono.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarNumeroTel(jTextFieldTelefono.getText())) {
                if (paciente == null) {
                    isTelModified = true;
                    isOkTel = true;
                } else {
                    isTelModified = !paciente.getTelefonoFijo().equals(jTextFieldTelefono.getText()); //!= dni;
                    isOkTel = true;
                    estadoBttnGuardar();
                }
                if (isThreadTel) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelTelefono);
                    isThreadTel = !isThreadTel;
                }
            } else {
                isOkTel = false;
                if (!isThreadTel) {
                    manejadorAnimacionLBL.crearAnimacion("lbltel", jLabelTelefono, 3, crgbe);
                    manejadorAnimacionLBL.crearAnimacion("txtfieldtel", jTextFieldTelefono, 3, crgbe);
                    isThreadTel = !isThreadTel;
                }
            }
        } else {
            isOkTel = false;
            if (isThreadTel) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelTelefono);
                isThreadTel = !isThreadTel;
            }
        }
        setEnabledJBttnNuevo();
    }

    public void textoEnCelCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldCelular.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarNumeroCel(jTextFieldCelular.getText())) {
                if (paciente == null) {
                    isCelModified = true;
                    isOkCel = true;
                } else {
                    isCelModified = !paciente.getCelular().equals(jTextFieldCelular.getText()); //!= dni;
                    isOkCel = true;
                    estadoBttnGuardar();
                }
                if (isThreadCel) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelCelular);
                    isThreadCel = !isThreadCel;
                }
            } else {
                isOkCel = false;
                if (!isThreadCel) {
                    manejadorAnimacionLBL.crearAnimacion("lblcel", jLabelCelular, 3, crgbe);
                    manejadorAnimacionLBL.crearAnimacion("txtfieldcel", jTextFieldCelular, 3, crgbe);
                    isThreadCel = !isThreadCel;
                }
            }
        } else {
            isOkCel = false;
            if (isThreadCel) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelCelular);
                isThreadCel = !isThreadCel;
            }
        }
        setEnabledJBttnNuevo();
    }

    public void textoEnMailCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldEmail.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarEMail(jTextFieldEmail.getText())) {
                isOkEmail = true;
                if (paciente != null) {
                    isEmailModified = (!paciente.getMail().equalsIgnoreCase(jTextFieldEmail.getText()));
                    estadoBttnGuardar();
                }
                if (jTextFieldEmail.getText().equals("")) {
                    isOkEmail = false;
                }
                if (isThreadEmail) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelEmail);
                    isThreadEmail = !isThreadEmail;
                }
            } else {
                isOkEmail = false;
                if (!isThreadEmail) {
                    manejadorAnimacionLBL.crearAnimacion("email", jLabelEmail, 3, crgbe);
                    isThreadEmail = !isThreadEmail;
                }
            }
        } else {
            isOkEmail = false;
            if (isThreadEmail) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelEmail);
                isThreadEmail = !isThreadEmail;
            }
        }
        setEnabledJBttnNuevo();
    }

    public void textoEnAlturaCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldAltura.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarNumeroDecimal(jTextFieldAltura.getText()) && jTextFieldAltura.getText().length() < 5) {
                if (paciente == null) {
                    isAlturaModified = true;
                    isOkAltura = true;
                } else {
                    String fieldAltura = jTextFieldAltura.getText().replace(',', '.');
                    isAlturaModified = (!("" + paciente.getAltura()).equals(fieldAltura));
                    estadoBttnGuardar();
                }
                if (jTextFieldAltura.getText().equals("")) {
                    isOkAltura = false;
                }
                if (isThreadAltura) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelAltura);
                    isThreadAltura = !isThreadAltura;
                }
            } else {
                isOkAltura = false;
                if (!isThreadAltura) {
                    manejadorAnimacionLBL.crearAnimacion("calorias", jLabelAltura, 3, crgbe);
                    isThreadAltura = !isThreadAltura;
                }
            }
        } else {
            isOkAltura = false;
            if (isThreadAltura) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelAltura);
                isThreadAltura = !isThreadAltura;
            }
        }
        setEnabledJBttnNuevo();
    }

    private void cambioDeFecha(javax.swing.event.DocumentEvent e) {
        if (!dateTextField.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarFecha(jDateChooserFNac)) {
                isOkFecha = true;
                if (paciente != null) {
                    Optional<java.util.Date> nuevaFecha = Optional.ofNullable((java.util.Date) jDateChooserFNac.getDate());
                    Optional<java.util.Date> fechaOriginal = Optional.ofNullable((paciente.getFechaNac() != null) ? java.sql.Date.valueOf(paciente.getFechaNac()) : null);
                    if (nuevaFecha.isPresent() && fechaOriginal.isPresent()) {
                        isFechaNacModified = !(nuevaFecha.get().equals(fechaOriginal.get()));
                        estadoBttnGuardar();
                    }
                }
                if (jDateChooserFNac.getDate().equals("")) {
                    isOkFecha = false;
                }
                if (isThreadFecha) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelFechaNac);
                    isThreadFecha = !isThreadFecha;
                }
            } else {
                isOkFecha = false;
                if (!isThreadFecha) {
                    manejadorAnimacionLBL.crearAnimacion("fechaNac", jLabelFechaNac, 3, crgbe);
                    isThreadFecha = !isThreadFecha;
                }
            }
        } else {
            isOkFecha = false;
            if (isThreadFecha) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelFechaNac);
                isThreadFecha = !isThreadFecha;
            }
        }
        setEnabledJBttnNuevo();
    }

    public void labelVolverMouseClicked(MouseEvent evt) {
        CardLayout cardLayout = pacientePanelMain.getCardLayout();
        desdePanel = pacientePanel.getDesdePanel();
        switch (desdePanel) {
            case 0:
                cardLayout.show(pacientePanelMain, "pacienteMenu");
                break;
            case 1:
                cardLayout.show(pacientePanelMain, "pacienteAdv");
        }
    }

}
