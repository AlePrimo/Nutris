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
import Entidades.PacienteExtendido;
import Util.ColorRGBError;
import Util.Efecto;
import Util.ManejadorAnimacionLBL;
import Util.SetConnValues;
import Util.Validador;
import Vistas.PacientePanelAdv;
import Vistas.PacientePanelMain;
import com.toedter.calendar.JDateChooser;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import nutris.Conexion;

/**
 *
 * @author Administrador
 */
public class ControladorPacientesAdv {
    
    private String driverDB = SetConnValues.getTipoDB();

    private final PacientePanelMain pacientePanelMain;
    private final PacientePanelAdv pacientePanelAdv;
    private Paciente paciente;
    private List<Paciente> pacientes;

    private final JTextField jTextFieldDni;
    private final JTextField jTextFieldApellido;
    private final JTextField jTextFieldNombre;

    private final JCheckBox jCheckBoxrangoFechaNac;
    private final JCheckBox jCheckBoxCond;

    private final JDateChooser jDateChooserFNac;
    private final JDateChooser jDateChooserFNacDesde;
    private final JDateChooser jDateChooserFNacHasta;

    private final JPanel jPanelCardFechaNac;
    private final JPanel jPanelCond1;
    private final JPanel jPanelCond2;
    private final JPanel jPanelCond3;

    private final JRadioButton jRadioButtonCondO1;
    private final JRadioButton jRadioButtonCondO2;
    private final JRadioButton jRadioButtonCondO3;
    private final JRadioButton jRadioButtonCondY1;
    private final JRadioButton jRadioButtonCondY2;
    private final JRadioButton jRadioButtonCondY3;

    private JButton jButtonNuevo;
    private final JButton jButtonVolver;
    private final JButton jButtonLimpiarForm;
    private final JButton jButtonBuscar;
    private JButton jButtonSalir;

    private final JLabel jLabelDni;
    private final JLabel jLabelApellido;
    private final JLabel jLabelNombre;

    private final DefaultTableModel tableModel;

    private final ColorRGBError crgbe;
    private final ManejadorAnimacionLBL manejadorAnimacionLBL;

    private boolean isDniModified;
    private boolean isApellidoModified;
    private boolean isNombreModified;
    private boolean isFechaNacModified;
    private boolean guardarMouseEntered;
    private boolean salirMouseEntered;
    private boolean isThreadDni;
    private boolean isThreadApellido;
    private boolean isThreadNombre;
    private final JTable jTablePacientes;
    private final Efecto efecto;
    private int columnaIcono;
    private int columnaIconoEditar;
    private int columnaIconoDietas;

    public ControladorPacientesAdv(PacientePanelAdv pacientePanelAdv) {
        this.pacientePanelAdv = pacientePanelAdv;
        pacientePanelMain = pacientePanelAdv.getPacientePanelMain();
        paciente = null;
        pacientes = new ArrayList<>();

        jTextFieldDni = pacientePanelAdv.getjTextFieldDni();
        jTextFieldApellido = pacientePanelAdv.getjTextFieldApellido();
        jTextFieldNombre = pacientePanelAdv.getjTextFieldNombre();

        jCheckBoxrangoFechaNac = pacientePanelAdv.getjCheckBoxrangoFechaNac();
        jDateChooserFNac = pacientePanelAdv.getjDateChooserFNac();
        jDateChooserFNacDesde = pacientePanelAdv.getjDateChooserFNacDesde();
        jDateChooserFNacHasta = pacientePanelAdv.getjDateChooserFNacHasta();

        jButtonNuevo = pacientePanelAdv.getjButtonNuevo();
        jButtonVolver = pacientePanelAdv.getjButtonVolver();
        jButtonLimpiarForm = pacientePanelAdv.getjButtonLimpiarForm();
        jButtonBuscar = pacientePanelAdv.getjButtonBuscar();
        jButtonSalir = pacientePanelAdv.getjButtonSalir();

        jLabelDni = pacientePanelAdv.getjLabelDni();
        jLabelApellido = pacientePanelAdv.getjLabelApellido();
        jLabelNombre = pacientePanelAdv.getjLabelNombre();

        jCheckBoxCond = pacientePanelAdv.getjCheckBoxCond();

        jPanelCardFechaNac = pacientePanelAdv.getjPanelCardFechaNac();

        jPanelCond1 = pacientePanelAdv.getjPanelCond1();
        jPanelCond2 = pacientePanelAdv.getjPanelCond2();
        jPanelCond3 = pacientePanelAdv.getjPanelCond3();

        jRadioButtonCondO1 = pacientePanelAdv.getjRadioButtonCondO1();
        jRadioButtonCondO2 = pacientePanelAdv.getjRadioButtonCondO2();
        jRadioButtonCondO3 = pacientePanelAdv.getjRadioButtonCondO3();
        jRadioButtonCondY1 = pacientePanelAdv.getjRadioButtonCondY1();
        jRadioButtonCondY2 = pacientePanelAdv.getjRadioButtonCondY2();
        jRadioButtonCondY3 = pacientePanelAdv.getjRadioButtonCondY3();

        tableModel = pacientePanelAdv.getTableModel();
        jTablePacientes = pacientePanelAdv.getjTablePacientes();

        efecto = new Efecto();
        efecto.animacionBuscar(jButtonBuscar, 40);

        isThreadDni = false;
        isThreadApellido = false;
        isThreadNombre = false;

        crgbe = new ColorRGBError();
        manejadorAnimacionLBL = new ManejadorAnimacionLBL();

        cambiosEnCampos();
        limpiarForm(false);
    }

    private void limpiarForm(boolean b) {
        jTextFieldDni.setText("");
        jTextFieldApellido.setText("");
        jTextFieldNombre.setText("");
        if (b) {
            jDateChooserFNac.setDate(null);
            jDateChooserFNacDesde.setDate(null);
            jDateChooserFNacHasta.setDate(null);
        }
        tableModel.setRowCount(0);

    }

    private void cargarTablePacientes() {
        Icon iconoEditar = new ImageIcon(getClass().getResource("../img/MoreInfo20px.png"));
        Icon iconoDietas = new ImageIcon(getClass().getResource("../img/CaseStudy48px.png"));
        columnaIconoEditar = 6;
        columnaIconoDietas = 7;
        tableModel.setRowCount(0);
        pacientes.forEach(p -> {
            tableModel.addRow(new Object[]{
                p.getIdPaciente(),
                p.getDni(),
                p.getApellido(),
                p.getNombre(),
                p.getFechaNac(),
                (p.isEstado()) ? "Activo" : "Inactivo",
                iconoEditar,
                iconoDietas
            });
        });
        jTablePacientes.getColumnModel().getColumn(columnaIconoEditar).setCellRenderer(createIconCellRenderer(iconoEditar));
        jTablePacientes.getColumnModel().getColumn(columnaIconoDietas).setCellRenderer(createIconCellRenderer(iconoDietas));
    }
    
    private TableCellRenderer createIconCellRenderer(final Icon icon) {
    return new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == columnaIconoEditar || column == columnaIconoDietas) {
                label.setIcon(icon);
                label.setText("");
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
            }
            return label;
        }
    };
}

    private List<String> prepararSentencia() {
        List<String> sentenciaPreparada = new ArrayList<>();
        String sentencia = "select * from nutris.paciente";
        String sClausula = " where ";
        String sDni = "dni like ? ";//"dni like ? ";
        String sCond1;// = "or ";
        String sApellido = "apellido like ? ";
        String sCond2;// = " ";
        String sNombre = "nombre like ? ";
        String sCond3;// = " ";
        String sFechaNacSRango = "fechaNac=?";
        String sFechaNacCRango = "fechaNac between ? and ?";
        String sFechaNac = (jCheckBoxrangoFechaNac.isSelected()) ? sFechaNacCRango : sFechaNacSRango;
//        String sql;
        boolean isClausula = false;
        boolean isDni = false;
        boolean isCond1 = false;
        boolean isApellido = false;
        boolean isCond2 = false;
        boolean isNombre = false;
        boolean isCond3 = false;
        boolean isFechaNac = false;
        boolean isFechaDesde = false;
        boolean isFechaHasta = false;

        if (!jTextFieldDni.getText().equals("")) {
            isDni = true;
        } else {
            isDni = false;
        }
        if (!jTextFieldApellido.getText().equals("")) {
            isApellido = true;
        } else {
            isApellido = false;
        }
        if (!jTextFieldNombre.getText().equals("")) {
            isNombre = true;
        } else {
            isNombre = false;
        }
        if (jCheckBoxrangoFechaNac.isSelected()) {
            if (jDateChooserFNacDesde.getDate() != null) {
                isFechaDesde = true;
            } else {
                isFechaDesde = false;
            }
            if (jDateChooserFNacHasta.getDate() != null) {
                isFechaHasta = true;
            } else {
                isFechaHasta = false;
            }
            isFechaNac = isFechaDesde && isFechaHasta;
        } else {
            if (jDateChooserFNac.getDate() != null) {
                isFechaNac = true;
            } else {
                isFechaNac = false;
            }
        }

        if (jCheckBoxCond.isSelected()) {
            if (isDni && (isApellido || isNombre || isFechaNac)) {
                sCond1 = (jRadioButtonCondO1.isSelected() && !jRadioButtonCondY1.isSelected()) ? "or " : "and ";
            } else {
                sCond1 = "";
            }
            if ((isDni || isApellido) && (isNombre || isFechaNac)) {
                sCond2 = (jRadioButtonCondO2.isSelected() && !jRadioButtonCondY2.isSelected()) ? "or " : "and ";
            } else {
                sCond2 = "";
            }
            if ((isDni || isApellido || isNombre) && isFechaNac) {
                sCond3 = (jRadioButtonCondO3.isSelected() && !jRadioButtonCondY3.isSelected()) ? "or " : "and ";
            } else {
                sCond3 = "";
            }
        } else {

            if (isDni && (isApellido || isNombre || isFechaNac)) {
                sCond1 = "or ";
            } else {
                sCond1 = "";
            }
            if ((isDni || isApellido) && (isNombre || isFechaNac)) {
                sCond2 = "or ";
            } else {
                sCond2 = "";
            }
            if ((isDni || isApellido || isNombre) && isFechaNac) {
                sCond3 = "or ";
            } else {
                sCond3 = "";
            }

        }

        isClausula = isDni || isApellido || isNombre || isFechaNac;
        isCond1 = isApellido;
        isCond2 = isNombre;
        isCond3 = isFechaNac;

        sentenciaPreparada.add(sentencia);
        sentenciaPreparada.add((isClausula) ? sClausula : "");
        sentenciaPreparada.add((isDni) ? sDni : "");
        sentenciaPreparada.add((isCond1) ? sCond1 : "");
        sentenciaPreparada.add((isApellido) ? sApellido : "");
        sentenciaPreparada.add((isCond2) ? sCond2 : "");
        sentenciaPreparada.add((isNombre) ? sNombre : "");
        sentenciaPreparada.add((isCond3) ? sCond3 : "");
        sentenciaPreparada.add((isFechaNac) ? sFechaNac : "");
        //sql=sentencia+((isClausula)?sClausula:"")+((isDni)?sDni:"")+((isCond1)?sCond1:"")+((isApellido)?sApellido:"")+((isCond2)?sCond2:"")+((isNombre)?sNombre:"")+((isCond3)?sCond3:"")+((isFechaNac)?sFechaNac:"");
        return sentenciaPreparada;
    }

    private Paciente pacienteAvanzado() {
        if (jCheckBoxrangoFechaNac.isSelected()) {
            PacienteExtendido a = new PacienteExtendido();
            if (!jTextFieldDni.getText().equals("")) {
                a.setDni(jTextFieldDni.getText());
            }
            a.setApellido(jTextFieldApellido.getText());
            a.setNombre(jTextFieldNombre.getText());
            if (jDateChooserFNacDesde.getDate() != null) {
                java.util.Date date1 = jDateChooserFNacDesde.getDate();
                LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                a.setFechaNac(localDate1);
            }
            if (jDateChooserFNacHasta.getDate() != null) {
                java.util.Date date2 = jDateChooserFNacHasta.getDate();
                LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                a.setFechaNac2(localDate2);
            }
            return a;
        } else {
            Paciente a = new Paciente();
            if (!jTextFieldDni.getText().equals("")) {
                a.setDni(jTextFieldDni.getText());
            }
            a.setApellido(jTextFieldApellido.getText());
            a.setNombre(jTextFieldNombre.getText());
            if (jDateChooserFNac.getDate() != null) {
                java.util.Date date1 = jDateChooserFNac.getDate();
                LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                a.setFechaNac(localDate1);
            }
            return a;
        }
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
            }
        });
    }

    public void salirPacienteActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public void buscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {
        PacienteData pacienteData = null;
        try {
            pacienteData = new PacienteData(Conexion.getConexion(driverDB));//            Logger.getLogger(FormPacientes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPacientesAdv.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> sql = prepararSentencia();
        String test = String.join("", sql);
        System.out.println(test);
        if (sql.get(1).equals("")) {
            pacientes = pacienteData.listarPacientes();
        } else {
            pacientes = pacienteData.listarPacientesAvanzado(pacienteAvanzado(), sql, jCheckBoxrangoFechaNac.isSelected());
        }
        cargarTablePacientes();
    }

    public void buttonLimpiarFormActionPerformed(ActionEvent evt) {
        limpiarForm(true);
    }

    public void buttonVolverActionPerformed(ActionEvent evt) {
        CardLayout cardLayout = pacientePanelMain.getCardLayout();
        cardLayout.show(pacientePanelMain, "pacienteMenu");
    }

    public void nuevoPacienteActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout cardLayout;
        pacientePanelMain.getPacientePanel().setDesdePanel(1);
        cardLayout = pacientePanelMain.getCardLayout();
        cardLayout.show(pacientePanelMain, "pacienteBas");
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

    public void buttonNuevoMouseEntered(java.awt.event.MouseEvent evt) {
        guardarMouseEntered = true;
        jButtonNuevo = efecto.efectoBttn(jButtonNuevo, jButtonNuevo.isFocusOwner(), 1, 1);
    }

    public void buttonNuevoMouseExited(java.awt.event.MouseEvent evt) {
        guardarMouseEntered = false;
        jButtonNuevo = efecto.efectoBttn(jButtonNuevo, jButtonNuevo.isFocusOwner(), -1, 3);
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
        jButtonNuevo = efecto.efectoBttn(jButtonNuevo, guardarMouseEntered, 1, 1);
    }

    public void buttonNuevoFocusLost(java.awt.event.FocusEvent evt) {
        jButtonNuevo = efecto.efectoBttn(jButtonNuevo, guardarMouseEntered, -1, 3);
    }

    public void buttonSalirFocusGained(java.awt.event.FocusEvent evt) {
        jButtonSalir = efecto.efectoBttn(jButtonSalir, salirMouseEntered, 1, 2);

    }

    public void buttonSalirFocusLost(java.awt.event.FocusEvent evt) {
        jButtonSalir = efecto.efectoBttn(jButtonSalir, salirMouseEntered, -1, 3);
    }

    public void checkBoxrangoFechaNacItemStateChanged(java.awt.event.ItemEvent evt) {
        CardLayout cardLayout = (CardLayout) jPanelCardFechaNac.getLayout();
        System.out.println(evt.getStateChange());
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cardLayout.show(jPanelCardFechaNac, "cardCRango");
        } else {
            cardLayout.show(jPanelCardFechaNac, "cardSRango");
        }
    }

    public void checkBoxCondItemStateChanged(java.awt.event.ItemEvent evt) {
        CardLayout cardLayout1 = (CardLayout) jPanelCond1.getLayout();
        CardLayout cardLayout2 = (CardLayout) jPanelCond2.getLayout();
        CardLayout cardLayout3 = (CardLayout) jPanelCond3.getLayout();
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cardLayout1.show(jPanelCond1, "cardCondCOps1");
            cardLayout2.show(jPanelCond2, "cardCondCOps2");
            cardLayout3.show(jPanelCond3, "cardCondCOps3");
        } else {
            cardLayout1.show(jPanelCond1, "cardCondSOps1");
            cardLayout2.show(jPanelCond2, "cardCondSOps2");
            cardLayout3.show(jPanelCond3, "cardCondSOps3");
        }
    }

    public void tablePacientesKeyReleased(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            jButtonSalir.requestFocus();
        }
    }

    public void textoEnDniCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldDni.getText().isEmpty()) {
            if (Validador.validarNumeroEntero(jTextFieldDni.getText()) && jTextFieldDni.getText().length() < 10) {
                if (paciente == null) {
                    isDniModified = true;
                } else {
                    isDniModified = !paciente.getDni().equals(jTextFieldDni.getText()); //!= dni;
                }
                if (isThreadDni) {
                    manejadorAnimacionLBL.detenerAnimacion(jLabelDni);
                    isThreadDni = !isThreadDni;
                }
            } else {
                if (!isThreadDni) {
                    manejadorAnimacionLBL.crearAnimacion("lbldni", jLabelDni, 3, crgbe);
                    manejadorAnimacionLBL.crearAnimacion("txtfielddni", jTextFieldDni, 3, crgbe);
                    isThreadDni = !isThreadDni;
                }
            }
        } else {
            if (isThreadDni) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelDni);
                isThreadDni = !isThreadDni;
            }
        }
    }

    public void textoEnApellidoCambiado(javax.swing.event.DocumentEvent e) {
        if (Validador.validarTextoYEspacio(jTextFieldApellido.getText())) {
            if (paciente != null) {
                isApellidoModified = (!paciente.getApellido().equalsIgnoreCase(jTextFieldApellido.getText()));
            }
            jButtonBuscar.setEnabled(true);
            if (isThreadApellido) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelApellido);
                isThreadApellido = !isThreadApellido;
            }
        } else {
            jButtonBuscar.setEnabled(false);
            if (!isThreadApellido) {
                manejadorAnimacionLBL.crearAnimacion("apellido", jLabelApellido, 3, crgbe);
                isThreadApellido = !isThreadApellido;
            }
        }

    }

    public void textoEnNombreCambiado(javax.swing.event.DocumentEvent e) {
        if (Validador.validarTextoYEspacio(jTextFieldNombre.getText())) {
            if (paciente != null) {
                isNombreModified = (!paciente.getNombre().equalsIgnoreCase(jTextFieldNombre.getText()));
            }
            jButtonBuscar.setEnabled(true);
            if (isThreadNombre) {
                manejadorAnimacionLBL.detenerAnimacion(jLabelNombre);
                isThreadNombre = !isThreadNombre;
            }
        } else {
            jButtonBuscar.setEnabled(false);
            if (!isThreadNombre) {
                manejadorAnimacionLBL.crearAnimacion("nombre", jLabelNombre, 3, crgbe);
                isThreadNombre = !isThreadNombre;
            }
        }
    }

    public void tablePacientesMouseClicked(MouseEvent evt) {
        List<Dieta> dietas;
        PacienteData pacienteData = null;
        DietaData dietaData = null;
        CardLayout cardLayout;
        try {
            pacienteData = new PacienteData(Conexion.getConexion(driverDB));
            dietaData = new DietaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(PacientePanelAdv.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (jTablePacientes.columnAtPoint(evt.getPoint()) == 6) {
            paciente = pacienteData.buscarPacientePorDni("" + jTablePacientes.getValueAt(jTablePacientes.getSelectedRow(), 1));
            System.out.println(paciente);
            pacientePanelMain.getPacientePanel().setDesdePanel(1);
            pacientePanelMain.getPacientePanel().setPacienteForm(paciente);
            cardLayout = pacientePanelMain.getCardLayout();
            cardLayout.show(pacientePanelMain, "pacienteBas");
        }
        if (jTablePacientes.columnAtPoint(evt.getPoint()) == 7) {
            dietas = dietaData.buscarDietasPorPaciente((int) jTablePacientes.getValueAt(jTablePacientes.getSelectedRow(), 0));
            if (!dietas.isEmpty()) {
                dietas.forEach(System.out::println);
            }
        }
    }

}
