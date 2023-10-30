/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import AccesoADatos.DietaData;
import AccesoADatos.DietaData;
import Entidades.Dieta;
import Entidades.Dieta;
import Entidades.DietaExtendido;
import Util.ColorRGBError;
import Util.Efecto;
import Util.ManejadorAnimacionLBL;
import Util.Validador;
import Vistas.DetalleComidasPanel;
import Vistas.DetalleDietaPanel;
import Vistas.DetalleHistorialPanel;
import Vistas.DetallePacientePanel;
import Vistas.DietaPacientePanelAdv;
import Vistas.DietaPanelMain;
import com.formdev.flatlaf.extras.components.FlatTriStateCheckBox;
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
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import nutris.Conexion;

/**
 *
 * @author Administrador
 */
public class ControladorDietaDietasAdv {
    
    private String driverDB = "mysql";

    private final DietaPanelMain dietaPanelMain;
    private final DietaPacientePanelAdv dietaPacientePanelAdv;
    private Dieta dieta;
    private List<Dieta> dietas;
    
    private JTree jTreeDietas;
    private FlatTriStateCheckBox flatTriStateCheckBoxEstado;
    private FlatTriStateCheckBox flatTriStateCheckBoxEstadoDieta;
    private FlatTriStateCheckBox flatTriStateCheckBoxRangoPesoRequerido;

    private final JTextField jTextFieldDni;
    private final JTextField jTextFieldApellido;
    private final JTextField jTextFieldNombre;
    private final JTextField jTextFieldCodDieta;
    private final JTextField jTextFieldDietaNombre;

//    private final JCheckBox jCheckBoxrangoFechaNac;
//    private final JCheckBox jCheckBoxCond;

//    private final JDateChooser jDateChooserFNac;
//    private final JDateChooser jDateChooserFNacDesde;
//    private final JDateChooser jDateChooserFNacHasta;

    private final JPanel jPanelCardFechaNac;
    private final JPanel jPanelCond1;
    private final JPanel jPanelCond2;
    private final JPanel jPanelCond3;
    private final JPanel jPanelDetalles;

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
    private final JLabel jLabelCodDieta;
    private final JLabel jLabelDietaNombre;
    

//    private final DefaultTableModel tableModel;

    private final ColorRGBError crgbe;
    private final ManejadorAnimacionLBL manejadorAnimacionLBL;

    private boolean isDniModified;
    private boolean isApellidoModified;
    private boolean isNombreModified;
    private boolean isFechaNacModified;
//    private boolean guardarMouseEntered;
    private boolean salirMouseEntered;
    private boolean isThreadDni;
    private boolean isThreadApellido;
    private boolean isThreadNombre;
    private final JTable jTableDietas;
    private final Efecto efecto;
    private int columnaIcono;
//    private int columnaIconoEditar;
//    private int columnaIconoDietas;

    public ControladorDietaDietasAdv(DietaPacientePanelAdv dietaPacientePanelAdv) {
        this.dietaPacientePanelAdv = dietaPacientePanelAdv;
        dietaPanelMain = dietaPacientePanelAdv.getDietaPanelMain();
        dieta = null;
        dietas = new ArrayList<>();

        jTextFieldDni = dietaPacientePanelAdv.getjTextFieldDni();
        jTextFieldApellido = dietaPacientePanelAdv.getjTextFieldApellido();
        jTextFieldNombre = dietaPacientePanelAdv.getjTextFieldNombre();

//        jCheckBoxrangoFechaNac = dietaPacientePanelAdv.getjCheckBoxrangoFechaNac();
//        jDateChooserFNac = dietaPacientePanelAdv.getjDateChooserFNac();
//        jDateChooserFNacDesde = dietaPacientePanelAdv.getjDateChooserFNacDesde();
//        jDateChooserFNacHasta = dietaPacientePanelAdv.getjDateChooserFNacHasta();

        jButtonNuevo = dietaPacientePanelAdv.getjButtonNuevo();
//        jButtonVolver = dietaPacientePanelAdv.getjButtonVolver();
//        jButtonLimpiarForm = dietaPacientePanelAdv.getjButtonLimpiarForm();
        jButtonBuscar = dietaPacientePanelAdv.getjButtonBuscar();
        jButtonSalir = dietaPacientePanelAdv.getjButtonSalir();

        jLabelDni = dietaPacientePanelAdv.getjLabelDni();
        jLabelApellido = dietaPacientePanelAdv.getjLabelApellido();
        jLabelNombre = dietaPacientePanelAdv.getjLabelNombre();

//        jCheckBoxCond = dietaPacientePanelAdv.getjCheckBoxCond();

//        jPanelCardFechaNac = dietaPacientePanelAdv.getjPanelCardFechaNac();

//        jPanelCond1 = dietaPacientePanelAdv.getjPanelCond1();
//        jPanelCond2 = dietaPacientePanelAdv.getjPanelCond2();
//        jPanelCond3 = dietaPacientePanelAdv.getjPanelCond3();

//        jRadioButtonCondO1 = dietaPacientePanelAdv.getjRadioButtonCondO1();
//        jRadioButtonCondO2 = dietaPacientePanelAdv.getjRadioButtonCondO2();
//        jRadioButtonCondO3 = dietaPacientePanelAdv.getjRadioButtonCondO3();
//        jRadioButtonCondY1 = dietaPacientePanelAdv.getjRadioButtonCondY1();
//        jRadioButtonCondY2 = dietaPacientePanelAdv.getjRadioButtonCondY2();
//        jRadioButtonCondY3 = dietaPacientePanelAdv.getjRadioButtonCondY3();

//        tableModel = dietaPacientePanelAdv.getTableModel();
//        jTableDietas = dietaPacientePanelAdv.getjTableDietas();

        jTreeDietas = crearJTree(dietas, jPanelDetalles);

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
//        if (b) {
//            jDateChooserFNac.setDate(null);
//            jDateChooserFNacDesde.setDate(null);
//            jDateChooserFNacHasta.setDate(null);
//        }
//        tableModel.setRowCount(0);

    }

//    private void cargarTableDietas() {
//        Icon iconoEditar = new ImageIcon(getClass().getResource("../img/MoreInfo48px.png"));
//        Icon iconoDietas = new ImageIcon(getClass().getResource("../img/CaseStudy48px.png"));
//        columnaIconoEditar = 6;
//        columnaIconoDietas = 7;
//        tableModel.setRowCount(0);
//        dietas.forEach(p -> {
//            tableModel.addRow(new Object[]{
//                p.getIdDieta(),
//                p.getDni(),
//                p.getApellido(),
//                p.getNombre(),
//                p.getFechaNac(),
//                (p.isEstado()) ? "Activo" : "Inactivo",
//                iconoEditar,
//                iconoDietas
//            });
//        });
//        jTableDietas.getColumnModel().getColumn(columnaIconoEditar).setCellRenderer(createIconCellRenderer(iconoEditar));
//        jTableDietas.getColumnModel().getColumn(columnaIconoDietas).setCellRenderer(createIconCellRenderer(iconoDietas));
//    }
    
//    private TableCellRenderer createIconCellRenderer(final Icon icon) {
//    return new DefaultTableCellRenderer() {
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//            if (column == columnaIconoEditar || column == columnaIconoDietas) {
//                label.setIcon(icon);
//                label.setText("");
//                label.setHorizontalAlignment(JLabel.CENTER);
//                label.setVerticalAlignment(JLabel.CENTER);
//            }
//            return label;
//        }
//    };
//}

    private List<String> prepararSentencia() {
        List<String> sentenciaPreparada = new ArrayList<>();
        String sentencia = "select * from nutris.dieta";
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

    private Dieta dietaAvanzado() {
        if (jCheckBoxrangoFechaNac.isSelected()) {
            DietaExtendido a = new DietaExtendido();
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
            Dieta a = new Dieta();
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

    public void salirDietaActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    public void buscarDietaActionPerformed(java.awt.event.ActionEvent evt) {
        DietaData dietaData = null;
        try {
            dietaData = new DietaData(Conexion.getConexion(driverDB));//            Logger.getLogger(FormDietas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDietaDietasAdv.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> sql = prepararSentencia();
        String test = String.join("", sql);
        System.out.println(test);
        if (sql.get(1).equals("")) {
            dietas = dietaData.listarDietas();
        } else {
            dietas = dietaData.listarDietasAvanzado(dietaAvanzado(), sql, jCheckBoxrangoFechaNac.isSelected());
        }
        cargarTableDietas();
    }

    public void buttonLimpiarFormActionPerformed(ActionEvent evt) {
        limpiarForm(true);
    }

    public void buttonVolverActionPerformed(ActionEvent evt) {
        CardLayout cardLayout = dietaPanelMain.getCardLayout();
        cardLayout.show(dietaPanelMain, "dietaMenu");
    }

    public void nuevoDietaActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout cardLayout;
        dietaPanelMain.getDietaPanel().setDesdePanel(1);
        cardLayout = dietaPanelMain.getCardLayout();
        cardLayout.show(dietaPanelMain, "dietaBas");
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

    public void tableDietasKeyReleased(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            jButtonSalir.requestFocus();
        }
    }

    public void textoEnDniCambiado(javax.swing.event.DocumentEvent e) {
        if (!jTextFieldDni.getText().isEmpty()) {
            if (Validador.validarNumeroEntero(jTextFieldDni.getText()) && jTextFieldDni.getText().length() < 10) {
                if (dieta == null) {
                    isDniModified = true;
                } else {
                    isDniModified = !dieta.getDni().equals(jTextFieldDni.getText()); //!= dni;
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
            if (dieta != null) {
                isApellidoModified = (!dieta.getApellido().equalsIgnoreCase(jTextFieldApellido.getText()));
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
            if (dieta != null) {
                isNombreModified = (!dieta.getNombre().equalsIgnoreCase(jTextFieldNombre.getText()));
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

    public void tableDietasMouseClicked(MouseEvent evt) {
        List<Dieta> dietas;
        DietaData dietaData = null;
        DietaData dietaData = null;
        CardLayout cardLayout;
        try {
            dietaData = new DietaData(Conexion.getConexion(driverDB));
            dietaData = new DietaData(Conexion.getConexion(driverDB));
        } catch (SQLException ex) {
            Logger.getLogger(DietaPacientePanelAdv.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (jTableDietas.columnAtPoint(evt.getPoint()) == 6) {
            dieta = dietaData.buscarDietaPorDni("" + jTableDietas.getValueAt(jTableDietas.getSelectedRow(), 1));
            System.out.println(dieta);
            dietaPanelMain.getDietaPanel().setDesdePanel(1);
            dietaPanelMain.getDietaPanel().setDietaForm(dieta);
            cardLayout = dietaPanelMain.getCardLayout();
            cardLayout.show(dietaPanelMain, "dietaBas");
        }
        if (jTableDietas.columnAtPoint(evt.getPoint()) == 7) {
            dietas = dietaData.buscarDietasPorDieta((int) jTableDietas.getValueAt(jTableDietas.getSelectedRow(), 0));
            if (!dietas.isEmpty()) {
                dietas.forEach(System.out::println);
            }
        }
    }
    
    private void triStateCheckBox(FlatTriStateCheckBox flatTriStateCheckBox, String[] texto){
        boolean isIndeterminate = flatTriStateCheckBox.isIndeterminate();
        boolean isSelected = flatTriStateCheckBox.isSelected();
        int estado = (isIndeterminate&&isSelected)?1:(!isIndeterminate&&isSelected)?2:0;
        switch (estado){
            case 0: flatTriStateCheckBox.setText(texto[0]);
            break;
            case 1: flatTriStateCheckBox.setText(texto[1]);
            break;
            case 2: flatTriStateCheckBox.setText(texto[2]);
        }
    }
    
    private static JTree crearJTree(List<Dieta> listaDietas, JPanel detailsPanel) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Dietas");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        JTree tree = new JTree(treeModel);

        for (Dieta dieta : listaDietas) {
            DefaultMutableTreeNode dietaNode = new DefaultMutableTreeNode(dieta);
            DefaultMutableTreeNode detalleNode = new DefaultMutableTreeNode("Detalle");
            DefaultMutableTreeNode pacienteNode = new DefaultMutableTreeNode("Paciente");
            DefaultMutableTreeNode comidasNode = new DefaultMutableTreeNode("Comidas");
            DefaultMutableTreeNode historialNode = new DefaultMutableTreeNode("Historial");

            dietaNode.add(detalleNode);
            dietaNode.add(pacienteNode);
            dietaNode.add(comidasNode);
            dietaNode.add(historialNode);

            root.add(dietaNode);
        }
//        // Evento Detalle
//        tree.addTreeSelectionListener(e -> {
//            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//            if (selectedNode != null) {
//                Object selectedObject = selectedNode.getUserObject();
//                if (selectedObject instanceof Dieta) {
//                    DetalleDietaPanel detallePanel = new DetalleDietaPanel((Dieta) selectedObject);
//                    detailsPanel.add(detallePanel, "Detalle");
//                    CardLayout cardLayout = (CardLayout) detailsPanel.getLayout();
//                    cardLayout.show(detailsPanel, "Detalle");
//                }
//            }
//        });
//
//        // Evento Paciente
//        tree.addTreeSelectionListener(e -> {
//            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//            if (selectedNode != null) {
//                Object selectedObject = selectedNode.getUserObject();
//                if (selectedObject instanceof Dieta) {
//                    DetallePacientePanel pacientePanel = new DetallePacientePanel((Dieta) selectedObject);
//                    detailsPanel.add(pacientePanel, "Paciente");
//                    CardLayout cardLayout = (CardLayout) detailsPanel.getLayout();
//                    cardLayout.show(detailsPanel, "Paciente");
//                }
//            }
//        });

//        // Evento Comidas
//        tree.addTreeSelectionListener(e -> {
//            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//            if (selectedNode != null && selectedNode.toString().equals("Comidas")) {
//                ComidasPanel comidasPanel = new ComidasPanel(obtenerComidasDeDietaSeleccionada()); // Implementa esta función
//                detailsPanel.add(comidasPanel, "Comidas");
//                CardLayout cardLayout = (CardLayout) detailsPanel.getLayout();
//                cardLayout.show(detailsPanel, "Comidas");
//            }
//        });
//
//        // Evento Historial
//        tree.addTreeSelectionListener(e -> {
//            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//            if (selectedNode != null && selectedNode.toString().equals("Historial")) {
//                HistorialPanel historialPanel = new HistorialPanel(obtenerDatosDeHistorial()); // Implementa esta función
//                detailsPanel.add(historialPanel, "Historial");
//                CardLayout cardLayout = (CardLayout) detailsPanel.getLayout();
//                cardLayout.show(detailsPanel, "Historial");
//            }
//        });
//        int idDietaSeleccionada = -1;
//
//        // Evento Comidas y Historial 
//        tree.addTreeSelectionListener(e -> {
//            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//            if (selectedNode != null) {
//                String selectedNodeText = selectedNode.toString();
//                if (selectedNodeText.equals("Comidas") || selectedNodeText.equals("Historial")) {
//                    idDietaSeleccionada = obtenerIdDieta(selectedNode.getParent().toString(), listaDietas); // Implementa esta función
//                    if (idDietaSeleccionada != -1) {
//                        if (selectedNodeText.equals("Comidas")) {
//                            ComidasPanel comidasPanel = new ComidasPanel(obtenerComidasDeDietaSeleccionada(idDietaSeleccionada));
//                            detailsPanel.add(comidasPanel, "comidas");
//                        } else if (selectedNodeText.equals("Historial")) {
//                            HistorialPanel historialPanel = new HistorialPanel(obtenerDatosDeHistorial(idDietaSeleccionada));
//                            detailsPanel.add(historialPanel, "historial");
//                        }
//                        CardLayout cardLayout = (CardLayout) detailsPanel.getLayout();
//                        cardLayout.show(detailsPanel, selectedNodeText.toLowerCase());
//                    }
//                }
//            }
//        });
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            if (selectedNode != null) {
                String selectedNodeText = selectedNode.toString();

                if ("Detalle".equals(selectedNodeText)) {
                    DetalleDietaPanel detallePanel = new DetalleDietaPanel((Dieta) selectedNode.getUserObject());
                    detailsPanel.add(detallePanel, "detalle");
                } else if ("Paciente".equals(selectedNodeText)) {
                    DetallePacientePanel pacientePanel = new DetallePacientePanel(((Dieta) selectedNode.getUserObject()).getPaciente());
                    detailsPanel.add(pacientePanel, "paciente");
                } else if ("Comidas".equals(selectedNodeText)) {
                    int idDieta = ((Dieta) selectedNode.getUserObject()).getIdDieta();
                    DetalleComidasPanel comidasPanel = new DetalleComidasPanel(idDieta);
//                    detailsPanel.add(comidasPanel, "comidas");
                } else if ("Historial".equals(selectedNodeText)) {
                    int idDieta = ((Dieta) selectedNode.getUserObject()).getIdDieta();
                    DetalleHistorialPanel historialPanel = new DetalleHistorialPanel(idDieta);
//                    detailsPanel.add(historialPanel, "historial");
                }

                CardLayout cardLayout = (CardLayout) detailsPanel.getLayout();
                cardLayout.show(detailsPanel, selectedNodeText.toLowerCase());
            }
        });

        return tree;
    }

}
