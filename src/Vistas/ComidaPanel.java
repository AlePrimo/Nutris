/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorComida;
import Entidades.Comida;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Administrador
 */
public class ComidaPanel extends java.awt.Panel {
    private ComidaPanelMain comidaPanelMain;
    private ControladorComida controladorComida;
    private int desdePanel;
    /**
     * Creates new form ComidaPanel
     */
    public ComidaPanel(ComidaPanelMain comidaPanelMain) {
        initComponents();
        this.comidaPanelMain = comidaPanelMain;
        controladorComida = new ControladorComida(this);
        desdePanel = 0;
    }
    
    public void setComidaForm(Comida c) {
        controladorComida.setComidaForm(c);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelComida = new javax.swing.JPanel();
        jPanelComidaTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelVolver = new javax.swing.JLabel();
        jPanelComidaForm = new javax.swing.JPanel();
        jLabelCodigoComida = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelCalorias = new javax.swing.JLabel();
        jTextFieldCalorias = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldCodigoComida = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 50), new java.awt.Dimension(50, 50), new java.awt.Dimension(50, 50));
        jLabelDetalle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextDetalle = new javax.swing.JTextArea();
        jLabelEstado = new javax.swing.JLabel();
        jCheckBoxEstado = new javax.swing.JCheckBox();
        jButtonLimpiarForm = new javax.swing.JButton();
        jPanelComidaBotones = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();

        setBackground(java.awt.Color.darkGray);

        jPanelComida.setPreferredSize(new java.awt.Dimension(640, 480));
        jPanelComida.setLayout(new java.awt.BorderLayout());

        jPanelComidaTitulo.setBackground(new java.awt.Color(39, 54, 64));

        jLabelTitulo.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 30)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Comida");

        jLabelVolver.setFont(new java.awt.Font("JetBrains Mono NL Thin", 1, 12)); // NOI18N
        jLabelVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BackArrow48px.png"))); // NOI18N
        jLabelVolver.setText("Volver");
        jLabelVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelVolverMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelComidaTituloLayout = new javax.swing.GroupLayout(jPanelComidaTitulo);
        jPanelComidaTitulo.setLayout(jPanelComidaTituloLayout);
        jPanelComidaTituloLayout.setHorizontalGroup(
            jPanelComidaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComidaTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelVolver)
                .addGap(18, 18, 18)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(665, Short.MAX_VALUE))
        );
        jPanelComidaTituloLayout.setVerticalGroup(
            jPanelComidaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComidaTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelComidaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelComida.add(jPanelComidaTitulo, java.awt.BorderLayout.NORTH);

        jPanelComidaForm.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanelComidaFormPropertyChange(evt);
            }
        });

        jLabelCodigoComida.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 18)); // NOI18N
        jLabelCodigoComida.setText("Codigo:");
        jLabelCodigoComida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelNombre.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 18)); // NOI18N
        jLabelNombre.setText("Nombre:");
        jLabelNombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelCalorias.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 18)); // NOI18N
        jLabelCalorias.setText("Calorias:");
        jLabelCalorias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextFieldCalorias.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 18)); // NOI18N
        jTextFieldCalorias.setPreferredSize(new java.awt.Dimension(200, 30));
        jTextFieldCalorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCaloriasActionPerformed(evt);
            }
        });

        jTextFieldNombre.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 18)); // NOI18N
        jTextFieldNombre.setPreferredSize(new java.awt.Dimension(200, 30));
        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jTextFieldCodigoComida.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 18)); // NOI18N
        jTextFieldCodigoComida.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldCodigoComida.setMinimumSize(new java.awt.Dimension(14, 30));
        jTextFieldCodigoComida.setPreferredSize(new java.awt.Dimension(200, 30));

        jButtonBuscar.setBackground(new java.awt.Color(30, 30, 30));
        jButtonBuscar.setToolTipText("Buscar Por DNI");
        jButtonBuscar.setBorder(null);
        jButtonBuscar.setContentAreaFilled(false);
        jButtonBuscar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonBuscar.setMaximumSize(new java.awt.Dimension(5000, 5000));
        jButtonBuscar.setPreferredSize(new java.awt.Dimension(40, 40));
        jButtonBuscar.setRequestFocusEnabled(false);
        jButtonBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButtonBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButtonBuscarFocusLost(evt);
            }
        });
        jButtonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonBuscarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonBuscarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonBuscarMousePressed(evt);
            }
        });
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        jButtonBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButtonBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButtonBuscarKeyTyped(evt);
            }
        });

        jLabelDetalle.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 18)); // NOI18N
        jLabelDetalle.setText("Detalle:");
        jLabelDetalle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextDetalle.setColumns(20);
        jTextDetalle.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 18)); // NOI18N
        jTextDetalle.setRows(5);
        jScrollPane1.setViewportView(jTextDetalle);

        jLabelEstado.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 18)); // NOI18N
        jLabelEstado.setText("Estado:");
        jLabelEstado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jCheckBoxEstado.setSelected(true);
        jCheckBoxEstado.setText("Activa");
        jCheckBoxEstado.setToolTipText("");

        jButtonLimpiarForm.setBackground(new java.awt.Color(30, 30, 30));
        jButtonLimpiarForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Broom48px.png"))); // NOI18N
        jButtonLimpiarForm.setBorder(null);
        jButtonLimpiarForm.setContentAreaFilled(false);
        jButtonLimpiarForm.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonLimpiarForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarFormActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelComidaFormLayout = new javax.swing.GroupLayout(jPanelComidaForm);
        jPanelComidaForm.setLayout(jPanelComidaFormLayout);
        jPanelComidaFormLayout.setHorizontalGroup(
            jPanelComidaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanelComidaFormLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelComidaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelComidaFormLayout.createSequentialGroup()
                        .addComponent(jLabelCodigoComida, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCodigoComida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLimpiarForm, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelComidaFormLayout.createSequentialGroup()
                        .addGroup(jPanelComidaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelComidaFormLayout.createSequentialGroup()
                                .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addGroup(jPanelComidaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelComidaFormLayout.createSequentialGroup()
                                .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelComidaFormLayout.createSequentialGroup()
                                .addComponent(jLabelCalorias, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCalorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(50, 50, 50))
        );
        jPanelComidaFormLayout.setVerticalGroup(
            jPanelComidaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComidaFormLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanelComidaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelComidaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelCodigoComida, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldCodigoComida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filler1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLimpiarForm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanelComidaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCalorias, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCalorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanelComidaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanelComida.add(jPanelComidaForm, java.awt.BorderLayout.CENTER);

        jButtonNuevo.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 14)); // NOI18N
        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Greek Salad48px.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setToolTipText("Nueva Comida");
        jButtonNuevo.setMaximumSize(new java.awt.Dimension(200, 40));
        jButtonNuevo.setMinimumSize(new java.awt.Dimension(75, 30));
        jButtonNuevo.setPreferredSize(new java.awt.Dimension(75, 35));
        jButtonNuevo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButtonNuevoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButtonNuevoFocusLost(evt);
            }
        });
        jButtonNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonNuevoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonNuevoMouseExited(evt);
            }
        });
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });

        jButtonEliminar.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 14)); // NOI18N
        jButtonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Remove.png"))); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setToolTipText("Eliminar Comida");
        jButtonEliminar.setMaximumSize(new java.awt.Dimension(200, 40));
        jButtonEliminar.setMinimumSize(new java.awt.Dimension(102, 30));
        jButtonEliminar.setPreferredSize(new java.awt.Dimension(150, 35));
        jButtonEliminar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButtonEliminarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButtonEliminarFocusLost(evt);
            }
        });
        jButtonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonEliminarMouseExited(evt);
            }
        });
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonGuardar.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 14)); // NOI18N
        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setMaximumSize(new java.awt.Dimension(200, 40));
        jButtonGuardar.setMinimumSize(new java.awt.Dimension(93, 30));
        jButtonGuardar.setPreferredSize(new java.awt.Dimension(93, 35));
        jButtonGuardar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButtonGuardarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButtonGuardarFocusLost(evt);
            }
        });
        jButtonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonGuardarMouseExited(evt);
            }
        });
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonSalir.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 14)); // NOI18N
        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/CloseWindow.png"))); // NOI18N
        jButtonSalir.setText("Salir");
        jButtonSalir.setToolTipText("Salir del Programa");
        jButtonSalir.setMaximumSize(new java.awt.Dimension(200, 40));
        jButtonSalir.setMinimumSize(new java.awt.Dimension(75, 30));
        jButtonSalir.setPreferredSize(new java.awt.Dimension(75, 35));
        jButtonSalir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButtonSalirFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButtonSalirFocusLost(evt);
            }
        });
        jButtonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonSalirMouseExited(evt);
            }
        });
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelComidaBotonesLayout = new javax.swing.GroupLayout(jPanelComidaBotones);
        jPanelComidaBotones.setLayout(jPanelComidaBotonesLayout);
        jPanelComidaBotonesLayout.setHorizontalGroup(
            jPanelComidaBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComidaBotonesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanelComidaBotonesLayout.setVerticalGroup(
            jPanelComidaBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComidaBotonesLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanelComidaBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanelComida.add(jPanelComidaBotones, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanelComida, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelComida, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCaloriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCaloriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCaloriasActionPerformed

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jButtonBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonBuscarFocusGained
        controladorComida.buttonBuscarFocusGained(evt);
    }//GEN-LAST:event_jButtonBuscarFocusGained

    private void jButtonBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonBuscarFocusLost
        controladorComida.buttonBuscarFocusLost(evt);
    }//GEN-LAST:event_jButtonBuscarFocusLost

    private void jButtonBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonBuscarMouseClicked
        controladorComida.buttonBuscarMouseClicked(evt);
    }//GEN-LAST:event_jButtonBuscarMouseClicked

    private void jButtonBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonBuscarMouseEntered
        controladorComida.buttonBuscarMouseEntered(evt);
    }//GEN-LAST:event_jButtonBuscarMouseEntered

    private void jButtonBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonBuscarMouseExited
        controladorComida.buttonBuscarMouseExited(evt);
    }//GEN-LAST:event_jButtonBuscarMouseExited

    private void jButtonBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonBuscarMousePressed
        controladorComida.buttonBuscarMousePressed(evt);
    }//GEN-LAST:event_jButtonBuscarMousePressed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        controladorComida.buscarComidaActionPerformed(evt);
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonBuscarKeyReleased
        controladorComida.buttonBuscarKeyReleased(evt);
    }//GEN-LAST:event_jButtonBuscarKeyReleased

    private void jButtonBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonBuscarKeyTyped
        controladorComida.buttonBuscarKeyTyped(evt);
    }//GEN-LAST:event_jButtonBuscarKeyTyped

    private void jPanelComidaFormPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelComidaFormPropertyChange
        System.out.println("BAZINGA");
    }//GEN-LAST:event_jPanelComidaFormPropertyChange

    private void jButtonNuevoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonNuevoFocusGained
        controladorComida.buttonNuevoFocusGained(evt);
    }//GEN-LAST:event_jButtonNuevoFocusGained

    private void jButtonNuevoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonNuevoFocusLost
        controladorComida.buttonNuevoFocusLost(evt);
    }//GEN-LAST:event_jButtonNuevoFocusLost

    private void jButtonNuevoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNuevoMouseEntered
        controladorComida.buttonNuevoMouseEntered(evt);
    }//GEN-LAST:event_jButtonNuevoMouseEntered

    private void jButtonNuevoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNuevoMouseExited
        controladorComida.buttonNuevoMouseExited(evt);
    }//GEN-LAST:event_jButtonNuevoMouseExited

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        controladorComida.nuevoComidaActionPerformed(evt);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonEliminarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonEliminarFocusGained
        controladorComida.buttonEliminarFocusGained(evt);
    }//GEN-LAST:event_jButtonEliminarFocusGained

    private void jButtonEliminarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonEliminarFocusLost
        controladorComida.buttonEliminarFocusLost(evt);
    }//GEN-LAST:event_jButtonEliminarFocusLost

    private void jButtonEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEliminarMouseEntered
        controladorComida.buttonEliminarMouseEntered(evt);
    }//GEN-LAST:event_jButtonEliminarMouseEntered

    private void jButtonEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEliminarMouseExited
        controladorComida.buttonEliminarMouseExited(evt);
    }//GEN-LAST:event_jButtonEliminarMouseExited

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        controladorComida.eliminarComidaActionPerformed(evt);
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonGuardarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonGuardarFocusGained
        controladorComida.buttonGuardarFocusGained(evt);
    }//GEN-LAST:event_jButtonGuardarFocusGained

    private void jButtonGuardarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonGuardarFocusLost
        controladorComida.buttonGuardarFocusLost(evt);
    }//GEN-LAST:event_jButtonGuardarFocusLost

    private void jButtonGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarMouseEntered
        controladorComida.buttonGuardarMouseEntered(evt);
    }//GEN-LAST:event_jButtonGuardarMouseEntered

    private void jButtonGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarMouseExited
        controladorComida.buttonGuardarMouseExited(evt);
    }//GEN-LAST:event_jButtonGuardarMouseExited

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        controladorComida.guardarComidaActionPerformed(evt);
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonSalirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonSalirFocusGained
        controladorComida.buttonSalirFocusGained(evt);
    }//GEN-LAST:event_jButtonSalirFocusGained

    private void jButtonSalirFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonSalirFocusLost
        controladorComida.buttonSalirFocusLost(evt);
    }//GEN-LAST:event_jButtonSalirFocusLost

    private void jButtonSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSalirMouseEntered
        controladorComida.buttonSalirMouseEntered(evt);
    }//GEN-LAST:event_jButtonSalirMouseEntered

    private void jButtonSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSalirMouseExited
        controladorComida.buttonSalirMouseExited(evt);
    }//GEN-LAST:event_jButtonSalirMouseExited

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        controladorComida.salirComidaActionPerformed(evt);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jLabelVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelVolverMouseClicked
        controladorComida.labelVolverMouseClicked(evt);
    }//GEN-LAST:event_jLabelVolverMouseClicked

    private void jButtonLimpiarFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarFormActionPerformed
        controladorComida.ButtonLimpiarFormActionPerformed(evt);
    }//GEN-LAST:event_jButtonLimpiarFormActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonLimpiarForm;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JCheckBox jCheckBoxEstado;
    private javax.swing.JLabel jLabelCalorias;
    private javax.swing.JLabel jLabelCodigoComida;
    private javax.swing.JLabel jLabelDetalle;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelVolver;
    private javax.swing.JPanel jPanelComida;
    private javax.swing.JPanel jPanelComidaBotones;
    private javax.swing.JPanel jPanelComidaForm;
    private javax.swing.JPanel jPanelComidaTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextDetalle;
    private javax.swing.JTextField jTextFieldCalorias;
    private javax.swing.JTextField jTextFieldCodigoComida;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
    
    public JButton getjButtonLimpiarForm() {
        return jButtonLimpiarForm;
    }
    
    public JButton getjButtonBuscar() {
        return jButtonBuscar;
    }

    public JButton getjButtonEliminar() {
        return jButtonEliminar;
    }

    public JButton getjButtonGuardar() {
        return jButtonGuardar;
    }

    public JButton getjButtonNuevo() {
        return jButtonNuevo;
    }

    public JButton getjButtonSalir() {
        return jButtonSalir;
    }
    
    public JLabel getjLabelVolver() {
        return jLabelVolver;
    }

    public JLabel getjLabelCodigoComida() {
        return jLabelCodigoComida;
    }
    
    public JLabel getjLabelNombre() {
        return jLabelNombre;
    }
    
    public JLabel getjLabelCalorias() {
        return jLabelCalorias;
    }

    public JLabel getjLabelDetalle() {
        return jLabelDetalle;
    }

    public JLabel getjLabelEstado() {
        return jLabelEstado;
    }
    
    public JTextArea getjTextDetalle() {
        return jTextDetalle;
    }

    public JTextField getjTextFieldCalorias() {
        return jTextFieldCalorias;
    }

    public JTextField getjTextFieldCodigoComida() {
        return jTextFieldCodigoComida;
    }

    public JTextField getjTextFieldNombre() {
        return jTextFieldNombre;
    }

    public JCheckBox getjCheckBoxEstado() {
        return jCheckBoxEstado;
    }

    public ComidaPanelMain getComidaPanelMain() {
        return comidaPanelMain;
    }

    public int getDesdePanel() {
        return desdePanel;
    }

    public void setDesdePanel(int desdePanel) {
        this.desdePanel = desdePanel;
    }
    
    

}
