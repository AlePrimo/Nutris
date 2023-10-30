/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import AccesoADatos.DietaData;
import AccesoADatos.PacienteData;
import Entidades.Dieta;
import Entidades.Paciente;
import Util.ColorRGBError;
import Util.Efecto;
import Util.IMC;
import Util.ManejadorAnimacionLBL;
import Util.Validador;
import Vistas.DietaPanel;
import Vistas.DietaPanelMain;
import com.toedter.calendar.JDateChooser;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import nutris.Conexion;

/**
 *
 * @author Administrador
 */
public class ControladorDieta {
    
    private String driverDB = "mariadb";

    private DietaPanelMain dietaPanelMain;
    private final DietaPanel dietaPanel;
    private Dieta dieta;
    private Paciente paciente;
    private List<Paciente> pacientes;

    private final JTextField jTextFieldCodDieta;
    private final JTextField jTextFieldDietaNombre;
    private final JTextField jTextFieldPesoInicial;
    private final JTextField jTextFieldPesoActual;
    private final JTextField dateTextFieldInicial;
    private final JTextField dateTextFieldFinal;

    private final JCheckBox jCheckBoxEstado;
    private final JDateChooser jDateChooserFechaInicio;
    private final JDateChooser jDateChooserFechaFinal;

    private final JButton jButtonLimpiarForm;
    private final JButton jButtonBuscar;
    private JButton jButtonEliminar;
    private JButton jButtonGuardar;
    private JButton jButtonNuevo;
    private JButton jButtonSalir;

    private JLabel jLabelVolver;
    private final JLabel jLabelCodDieta;
    private final JLabel jLabelDietaNombre;
    private final JLabel jLabelFechaInicio;
    private final JLabel jLabelFechaFinal;
    private final JLabel jLabelDniData;
    private final JLabel jLabelNombreData;
    private final JLabel jLabelApellidoData;
    private final JLabel jLabelPesoActual;
    private final JLabel jLabelPesoInicial;
    private final JLabel jLabelKg1;
    private final JLabel jLabelKg2;
    private final JLabel jLabelRPesoRequerido;
    private final JLabel jLabelRPesoRequeridoData;

    private final ColorRGBError crgbe;
    private final ManejadorAnimacionLBL manejadorAnimacionLBL;
    private final Efecto efecto;

    private boolean isFound;
    private boolean isCodDietaModified;
    private boolean isNombreModified;
    private boolean isFechaInicioModified;
    private boolean isFechaFinalModified;
    private boolean isPesoInicialModified;
    private boolean isPesoFinalModified;
    private boolean isEstadoModified;
    
    private boolean bufferState;
    private boolean nuevoMouseEntered;
    private boolean eliminarMouseEntered;
    private boolean guardarMouseEntered;
    private boolean salirMouseEntered;

    private final DefaultComboBoxModel boxModel;
    private final JComboBox<Paciente> jComboBoxPaciente;
    
//    private boolean isOk;
    private boolean isOkCodDieta;
    private boolean isOkDietaNombre;
    private boolean isOkFechaInicio;
    private boolean isOkFechaFinal;
    private boolean isOkPesoFinal;
    private boolean isOkPesoInicial;
    
    private boolean isThreadCodDieta;
    private boolean isThreadDietaNombre;
    private boolean isThreadFechaInicio;
    private boolean isThreadFechaFinal;
    private boolean isThreadPesoInicial;
    private boolean isThreadPesoFinal;

    private int desdePanel;
    private Map<String, Object> infoRangoPesoRequerido;

    public ControladorDieta(DietaPanel dietaPanel) {
        this.dietaPanel = dietaPanel;
        dietaPanelMain = dietaPanel.getDietaPanelMain();
        dieta = null;
        pacientes = new ArrayList<>();

        jTextFieldCodDieta = dietaPanel.getjTextFieldCodDieta();
        jTextFieldDietaNombre = dietaPanel.getjTextFieldDietaNombre();
        jTextFieldPesoInicial = dietaPanel.getjTextFieldPesoInicial();
        jTextFieldPesoActual = dietaPanel.getjTextFieldPesoActual();

        jButtonLimpiarForm = dietaPanel.getjButtonLimpiarForm();
        jButtonBuscar = dietaPanel.getjButtonBuscar();
        jButtonEliminar = dietaPanel.getjButtonEliminar();
        jButtonGuardar = dietaPanel.getjButtonGuardar();
        jButtonNuevo = dietaPanel.getjButtonNuevo();
        jButtonSalir = dietaPanel.getjButtonSalir();

        jLabelVolver = dietaPanel.getjLabelVolver();
        jLabelCodDieta = dietaPanel.getjLabelCodDieta();
        jLabelDietaNombre = dietaPanel.getjLabelDietaNombre();
        jLabelFechaInicio = dietaPanel.getjLabelFechaInicio();
        jLabelFechaFinal = dietaPanel.getjLabelFechaFinal();
        jLabelDniData = dietaPanel.getjLabelDniData();
        jLabelNombreData = dietaPanel.getjLabelNombreData();
        jLabelApellidoData = dietaPanel.getjLabelApellidoData();
        jLabelPesoActual = dietaPanel.getjLabelPesoActual();
        jLabelPesoInicial = dietaPanel.getjLabelPesoInicial();
        jLabelKg1 = dietaPanel.getjLabelKg1();
        jLabelKg2 = dietaPanel.getjLabelKg2();
        jLabelRPesoRequerido = dietaPanel.getjLabelRPesoRequerido();
        jLabelRPesoRequeridoData = dietaPanel.getjLabelRPesoRequeridoData();

        jCheckBoxEstado = dietaPanel.getjCheckBoxEstado();
        jDateChooserFechaInicio = dietaPanel.getjDateChooserFechaInicio();
        jDateChooserFechaFinal = dietaPanel.getjDateChooserFechaFinal();
        dateTextFieldInicial = (JTextField) jDateChooserFechaInicio.getDateEditor().getUiComponent();
        dateTextFieldFinal = (JTextField) jDateChooserFechaFinal.getDateEditor().getUiComponent();

        boxModel = dietaPanel.getBoxModel();
        jComboBoxPaciente = dietaPanel.getjComboBoxPaciente();

        efecto = new Efecto();
        efecto.animacionBuscar(jButtonBuscar, 40);

        crgbe = new ColorRGBError();
        manejadorAnimacionLBL = new ManejadorAnimacionLBL();
        
        nuevoMouseEntered = false;
        eliminarMouseEntered = false;
        guardarMouseEntered = false;
        salirMouseEntered = false;

        isThreadCodDieta = false;
        isThreadDietaNombre = false;
        isThreadFechaInicio = false;
        isThreadFechaFinal = false;
        isThreadPesoInicial = false;
        isThreadPesoFinal = false;

        isOkCodDieta = false;
        isOkDietaNombre = false;
        isOkFechaInicio = false;
        isOkPesoFinal = false;
        isOkPesoInicial = false;
        isOkPesoFinal = false;
//        isOk = isOkCodDieta && isOkApellido && isOkDietaNombre;
        cambiosEnCampos();
        cargarComboBoxPaciente();
        setEnabledJBttnNuevo();
        limpiarForm();

    }

    private void cargarComboBoxPaciente() {
        PacienteData pacienteData = null;
        try {
            pacienteData = new PacienteData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDieta.class.getName()).log(Level.SEVERE, null, ex);
        }
        pacientes = pacienteData.listarPacientes();
        pacientes.forEach(boxModel::addElement);
        jComboBoxPaciente.setModel(boxModel);
    }

    private void limpiarForm() {
        jTextFieldCodDieta.setText("");
        jTextFieldDietaNombre.setText("");
        jLabelDniData.setText("");
        jLabelApellidoData.setText("");
        jLabelNombreData.setText("");
        jTextFieldPesoInicial.setText("");
        jTextFieldPesoActual.setText("");
        dateTextFieldFinal.setText("");
        dateTextFieldInicial.setText("");
        jLabelRPesoRequeridoData.setText("");
        jButtonGuardar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        jButtonNuevo.setEnabled(false);
        jButtonBuscar.setEnabled(false);
        jButtonLimpiarForm.setEnabled(false);
        jTextFieldCodDieta.setEditable(true);
    }

    private void estadoBttnGuardar() {
        System.out.println("----------------------");
        System.out.println("found "+isFound);
        System.out.println("nombre "+isNombreModified);
        System.out.println("fechaIn "+isFechaInicioModified);
        System.out.println("fechaFi "+isFechaFinalModified);
        System.out.println("pesoIn "+isPesoInicialModified);
        System.out.println("pesoAc "+isPesoFinalModified);
        System.out.println("estado "+isEstadoModified);
        System.out.println("final "+(isFound && (isNombreModified || isEstadoModified || isFechaInicioModified || isFechaFinalModified || isPesoInicialModified || isPesoFinalModified)));
        System.out.println("----------------------");
        jButtonGuardar.setEnabled(isFound && (isNombreModified || isEstadoModified || isFechaInicioModified || isFechaFinalModified || isPesoInicialModified || isPesoFinalModified));
//        isFechaInicioModified = false;
//        isFechaFinalModified = false;
    }

    private void cambiosEnCampos() {
        jTextFieldCodDieta.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCodDietaCambiado(e);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCodDietaCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnCodDietaCambiado(e);
            }
        });

        jTextFieldDietaNombre.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDietaNombreCambiado(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDietaNombreCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnDietaNombreCambiado(e);
            }
        });

        dateTextFieldInicial.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                cambioDeFechaInicio(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                cambioDeFechaInicio(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                cambioDeFechaInicio(e);
            }
        });

        dateTextFieldFinal.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                cambioDeFechaFinal(e);

            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                cambioDeFechaFinal(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                cambioDeFechaFinal(e);
            }
        });

        jTextFieldPesoInicial.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnPesoInicialCambiado(e);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnPesoInicialCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnPesoInicialCambiado(e);
            }
        });

        jTextFieldPesoActual.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                textoEnPesoFinalCambiado(e);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                textoEnPesoFinalCambiado(e);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                textoEnPesoFinalCambiado(e);
            }
        });

    }

    public void salirDietaActionPerformed(java.awt.event.ActionEvent evt) {
//        dietaPanel.dispose();
        System.exit(0);
    }

    public void buttonBuscarActionPerformed(ActionEvent evt) {
        DietaData dietaData = null;
        try {
            dietaData = new DietaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
//            Logger.getLogger(PacientePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        dieta = dietaData.buscarDietaPorId(Integer.parseInt(jTextFieldCodDieta.getText()));

        if (dieta != null) {
            isFound = true;
            setDietaForm(dieta);
            jTextFieldCodDieta.setEditable(false);
            System.out.println("ver found "+isFound);
        } else {
            isFound = false;
        }
    }

    public void nuevoDietaActionPerformed(java.awt.event.ActionEvent evt) {
        nuevoOGuardar();
    }

    public void guardarDietaActionPerformed(java.awt.event.ActionEvent evt) {
        nuevoOGuardar();
    }
    
    public void eliminarDietaActionPerformed(java.awt.event.ActionEvent evt) {
        DietaData dietaData = null;
        try {
            dietaData = new DietaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(DietaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (dietaData.eliminarDieta(dieta.getIdDieta())) {
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
        jCheckBoxEstado.setText(jCheckBoxEstado.isSelected()?"Terminada":"En Curso");
        if (isFound) {
            if (bufferState != jCheckBoxEstado.isSelected()) {
                isEstadoModified = true; //(dieta.getDni()!=0&&prevState!=jCheckBoxEstado.isSelected())?true:false;
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

    public void textoEnCodDietaCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldCodDieta.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarNumeroEntero(jTextFieldCodDieta.getText())) {
                if (dieta == null) {
                    isCodDietaModified = true;
                    isOkCodDieta = true;
                } else {
                    isCodDietaModified = !("" + dieta.getIdDieta()).equals(jTextFieldCodDieta.getText());
                    isOkCodDieta = true;
//                estadoBttnGuardar();
                }
                if (isThreadCodDieta) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelCodDieta);
                    isThreadCodDieta = !isThreadCodDieta;
                }
            } else {
                isOkCodDieta = false;
                if (!isThreadCodDieta) {
                    manejadorAnimacionLBL.crearAnimacion("lblCodDieta", jLabelCodDieta, 3, crgbe);
                    manejadorAnimacionLBL.crearAnimacion("txtfieldCodDieta", jTextFieldCodDieta, 3, crgbe);

                    isThreadCodDieta = !isThreadCodDieta;
                }
            }
        } else {
            isOkCodDieta = false;
            if (isThreadCodDieta) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelCodDieta);
                isThreadCodDieta = !isThreadCodDieta;
            }
        }
        setEnabledJBttnNuevo();
        setEditableTextFieldCodDieta();
        jButtonBuscar.setEnabled(isOkCodDieta);
    }

    public void textoEnDietaNombreCambiado(javax.swing.event.DocumentEvent e) {
        if (Validador.validarTextoYEspacio(jTextFieldDietaNombre.getText())) {
            isOkDietaNombre = true;
            if (dieta != null) {
                isNombreModified = !dieta.getNombre().equalsIgnoreCase(jTextFieldDietaNombre.getText());
                estadoBttnGuardar();
            }
            if (jTextFieldDietaNombre.getText().isEmpty()) {
                isOkDietaNombre = false;
            } else {
                jButtonLimpiarForm.setEnabled(true);
            }
            if (isThreadDietaNombre) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelDietaNombre);
                isThreadDietaNombre = !isThreadDietaNombre;
            }
        } else {
            isOkDietaNombre = false;
            if (!isThreadDietaNombre) {
                manejadorAnimacionLBL.crearAnimacion("dietaNombre", jLabelDietaNombre, 3, crgbe);
                isThreadDietaNombre = !isThreadDietaNombre;
            }
        }
        setEnabledJBttnNuevo();
        setEditableTextFieldCodDieta();
    }

    private void cambioDeFechaInicio(javax.swing.event.DocumentEvent e) {
        if (!dateTextFieldInicial.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarFecha(jDateChooserFechaInicio)) {
                isOkFechaInicio = true;
                if (dieta != null) {
                    Optional<java.util.Date> nuevaFecha = Optional.ofNullable((java.util.Date) jDateChooserFechaInicio.getDate());
                    Optional<java.util.Date> fechaOriginal = Optional.ofNullable((dieta.getFechaInicio() != null) ? java.sql.Date.valueOf(dieta.getFechaInicio()) : null);
                    if (nuevaFecha.isPresent() && fechaOriginal.isPresent()) {
                        isFechaInicioModified = !(nuevaFecha.get().equals(fechaOriginal.get()));
                        estadoBttnGuardar();
                    }
                }
                if (dateTextFieldInicial.getText().isEmpty()) {  //(jDateChooserFechaInicio.getDate().equals("")) {
                    isOkFechaInicio = false;
                }
                if (isThreadFechaInicio) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelFechaInicio);
                    isThreadFechaInicio = !isThreadFechaInicio;
                }
            } else {
                isOkFechaInicio = false;
                if (!isThreadFechaInicio) {
                    manejadorAnimacionLBL.crearAnimacion("fechaInicio", jLabelFechaInicio, 3, crgbe);
                    isThreadFechaInicio = !isThreadFechaInicio;
                }
            }
        } else {
            isOkFechaInicio = false;
            if (isThreadFechaInicio) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelFechaInicio);
                isThreadFechaInicio = !isThreadFechaInicio;
            }
        }
        setEnabledJBttnNuevo();
        setEditableTextFieldCodDieta();
    }

    private void cambioDeFechaFinal(javax.swing.event.DocumentEvent e) {
        if (!dateTextFieldFinal.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarFecha(jDateChooserFechaFinal)) {
                isOkFechaFinal = true;
                if (dieta != null) {
                    Optional<java.util.Date> nuevaFecha = Optional.ofNullable((java.util.Date) jDateChooserFechaFinal.getDate());
                    Optional<java.util.Date> fechaOriginal = Optional.ofNullable((dieta.getFechaFinal() != null) ? java.sql.Date.valueOf(dieta.getFechaFinal()) : null);
                    if (nuevaFecha.isPresent() && fechaOriginal.isPresent()) {
                        isFechaFinalModified = !(nuevaFecha.get().equals(fechaOriginal.get()));
                        estadoBttnGuardar();
                    }
                }
                if (dateTextFieldFinal.getText().isEmpty()) {  //(jDateChooserFechaFinal.getDate().equals("")) {
                    isOkFechaFinal = false;
                }
                if (isThreadFechaFinal) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelFechaFinal);
                    isThreadFechaFinal = !isThreadFechaFinal;
                }
            } else {
                isOkFechaFinal = false;
                if (!isThreadFechaFinal) {
                    manejadorAnimacionLBL.crearAnimacion("fechaFinal", jLabelFechaFinal, 3, crgbe);
                    isThreadFechaFinal = !isThreadFechaFinal;
                }
            }
        } else {
            isOkFechaFinal = false;
            if (isThreadFechaFinal) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelFechaFinal);
                isThreadFechaFinal = !isThreadFechaFinal;
            }
        }
        setEnabledJBttnNuevo();
        setEditableTextFieldCodDieta();
    }

    public void textoEnPesoInicialCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldPesoInicial.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarNumeroDecimal(jTextFieldPesoInicial.getText()) && jTextFieldPesoInicial.getText().length() < 7) {
                if (dieta == null) {
                    isPesoInicialModified = true;
                    isOkPesoInicial = true;
                    System.out.println("MOMENTO PESOIN: "+isPesoInicialModified);
                } else {
                    String fieldPesoInicial = jTextFieldPesoInicial.getText().replace(',','.');
                    isPesoInicialModified = !("" + dieta.getPesoInicial()).equals(fieldPesoInicial);
                    isOkPesoInicial = true;
                    estadoBttnGuardar();
                }
                if (isThreadPesoInicial) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelPesoInicial);
                    manejadorAnimacionLBL.detenerAnimacion(jLabelKg1);
                    isThreadPesoInicial = !isThreadPesoInicial;
                }
            } else {
                isOkPesoInicial = false;
                if (!isThreadPesoInicial) {
                    manejadorAnimacionLBL.crearAnimacion("lblPesoInicial", jLabelPesoInicial, 3, crgbe);
                    manejadorAnimacionLBL.crearAnimacion("lblKg1", jLabelKg1, 3, crgbe);
                    isThreadPesoInicial = !isThreadPesoInicial;
                }
            }
        } else {
            isOkPesoInicial = false;
            if (isThreadPesoInicial) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelPesoInicial);
                manejadorAnimacionLBL.detenerAnimacion(jLabelKg1);
                isThreadPesoInicial = !isThreadPesoInicial;
            }
        }
        setEnabledJBttnNuevo();
        setEditableTextFieldCodDieta();
    }

    public void textoEnPesoFinalCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldPesoActual.getText().isEmpty()) {
            jButtonLimpiarForm.setEnabled(true);
            if (Validador.validarNumeroDecimal(jTextFieldPesoActual.getText()) && jTextFieldPesoActual.getText().length() < 7) {
                if (dieta == null) {
                    isPesoFinalModified = true;
                    System.out.println("MOMENTO PESOFI: "+isPesoFinalModified);
                    isOkPesoFinal = true;
                } else {
                    String fieldPesoFinal = jTextFieldPesoActual.getText().replace(',','.');
                    isPesoFinalModified = !("" + dieta.getPesoActual()).equals(fieldPesoFinal);
                    System.out.println("VALOR PESOfI: "+isPesoFinalModified);
                    isOkPesoFinal = true;
                    estadoBttnGuardar();
                }
                if (isThreadPesoFinal) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelPesoActual);
                    manejadorAnimacionLBL.detenerAnimacion(jLabelKg2);
                    isThreadPesoFinal = !isThreadPesoFinal;
                }
            } else {
                isOkPesoFinal = false;
                if (!isThreadPesoFinal) {
                    manejadorAnimacionLBL.crearAnimacion("lblPesoFinal", jLabelPesoActual, 3, crgbe);
                    manejadorAnimacionLBL.crearAnimacion("lblKg2", jLabelKg2, 3, crgbe);
                    isThreadPesoFinal = !isThreadPesoFinal;
                }
            }
        } else {
            isOkPesoFinal = false;
            if (isThreadPesoFinal) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelPesoActual);
                manejadorAnimacionLBL.detenerAnimacion(jLabelKg2);
                isThreadPesoFinal = !isThreadPesoFinal;
            }
        }
        setEnabledJBttnNuevo();
        setEditableTextFieldCodDieta();
    }

    public void ButtonLimpiarFormActionPerformed(ActionEvent evt) {
        limpiarForm();
    }

    public void labelVolverMouseClicked(MouseEvent evt) {
        CardLayout cardLayout = dietaPanelMain.getCardLayout();
        desdePanel = dietaPanel.getDesdePanel();
        switch (desdePanel) {
            case 0:
                cardLayout.show(dietaPanelMain, "dietaMenu");
                break;
            case 1:
                cardLayout.show(dietaPanelMain, "dietaPacienteAdv");
        }
    }

    private void setDietaForm(Dieta dieta) {
        this.dieta = dieta;
        if (jTextFieldCodDieta.getText().isEmpty()) {
            jTextFieldCodDieta.setText("" + dieta.getIdDieta());
        }
        jTextFieldDietaNombre.setText(dieta.getNombre());
        jLabelDniData.setText(dieta.getPaciente().getDni());
        jLabelApellidoData.setText(dieta.getPaciente().getApellido());
        jLabelNombreData.setText(dieta.getPaciente().getNombre());
        Date date = java.sql.Date.valueOf(dieta.getFechaInicio());
        jDateChooserFechaInicio.setDate(date);
        date = java.sql.Date.valueOf(dieta.getFechaFinal());
        jDateChooserFechaFinal.setDate(date);
        jTextFieldPesoInicial.setText("" + dieta.getPesoInicial());
        jTextFieldPesoActual.setText("" + dieta.getPesoActual());
        infoRangoPesoRequerido = IMC.calcIMC(dieta.getPaciente().getAltura());
        jLabelRPesoRequeridoData.setText((String) infoRangoPesoRequerido.get("rangoIMC"));
        jCheckBoxEstado.setSelected(dieta.isEstado());
        jButtonEliminar.setEnabled(dieta.isEstado());
        jButtonNuevo.setEnabled(false);
//        isFound = true;
        bufferState = dieta.isEstado();
    }

    private void setEnabledJBttnNuevo() {
        boolean setTo;
//        isOk = isOkCodDieta && isOkDietaNombre && isOkFechaInicio && isOkFechaFinal && isOkPesoInicial && isOkPesoFinal && !jCheckBoxEstado.isSelected();
//        System.out.println(isOkDietaNombre);
//        System.out.println(isOkFechaInicio);
//        System.out.println(isOkFechaFinal);
//        System.out.println(isOkPesoInicial);
//        System.out.println(isOkPesoFinal);
//        System.out.println(!jCheckBoxEstado.isSelected());
//        System.out.println("----------------------------------");
        setTo = jTextFieldCodDieta.getText().isEmpty() && isOkDietaNombre && isOkFechaInicio && isOkFechaFinal && isOkPesoInicial && isOkPesoFinal && !jCheckBoxEstado.isSelected();
        jButtonNuevo.setEnabled(setTo);
        jComboBoxPaciente.setVisible(setTo);
        jComboBoxPaciente.setEnabled(setTo);
    }

    public void comboBoxPacienteActionPerformed(ActionEvent evt) {
        paciente = (Paciente) jComboBoxPaciente.getSelectedItem();
        jLabelDniData.setText(paciente.getDni());
        jLabelApellidoData.setText(paciente.getApellido());
        jLabelNombreData.setText(paciente.getNombre());
        infoRangoPesoRequerido = IMC.calcIMC(paciente.getAltura());
        jLabelRPesoRequeridoData.setText((String) infoRangoPesoRequerido.get("rangoIMC"));
        setEnabledJBttnNuevo();
    }

    public void nuevoOGuardar() {
//        dieta.setIdDieta(Integer.parseInt(jTextFieldCodDieta.getText()));
        if (jButtonNuevo.isEnabled() && !jButtonGuardar.isEnabled()) {
            dieta = new Dieta();
            dieta.setPaciente((Paciente) jComboBoxPaciente.getSelectedItem());
        }
        dieta.setNombre(jTextFieldDietaNombre.getText());
        java.util.Date dateInicio = jDateChooserFechaInicio.getDate();
        LocalDate localDateInicio = dateInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dieta.setFechaInicio(localDateInicio);
        java.util.Date dateFinal = jDateChooserFechaFinal.getDate();
        LocalDate localDateFinal = dateFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dieta.setFechaInicio(localDateFinal);
        dieta.setPesoInicial(Double.parseDouble(jTextFieldPesoInicial.getText()));
        dieta.setPesoActual(Double.parseDouble(jTextFieldPesoActual.getText()));
        dieta.setEstado(jCheckBoxEstado.isSelected());
        DietaData dietaData = null;
        try {
            dietaData = new DietaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(DietaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (jButtonNuevo.isEnabled() && !jButtonGuardar.isEnabled()) {
            if (dietaData.ingresarDieta(dieta)) {
                limpiarForm();
            }
        }
        if (!jButtonNuevo.isEnabled() && jButtonGuardar.isEnabled()) {
            if (dietaData.modificarDieta(dieta)) {
                limpiarForm();
            }
        }
    }

    private void setEditableTextFieldCodDieta() {
        boolean setTo;
        setTo = isOkCodDieta || isOkDietaNombre || isOkFechaInicio || isOkFechaFinal || isOkPesoInicial || isOkPesoFinal;

        if (!setTo) {
            jTextFieldCodDieta.setEditable(true);
        }

    }

}
