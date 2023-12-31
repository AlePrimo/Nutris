/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorComida;
import Controladores.ControladorComidaDietaPanel;
import Entidades.Comida;
import Entidades.Dieta;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Administrador
 */
public class ComidaDietaPanel11 extends javax.swing.JInternalFrame {
    private DefaultListModel<Comida> listModelComida = new DefaultListModel<>();
    private DefaultListModel<Dieta> listModelDieta = new DefaultListModel<>();
    private final ControladorComidaDietaPanel controladorComidaDietaPanel;
    private ComidaPanelMain comidaPanelMain;
    private ControladorComida controladorComida;
    private int desdePanel;

    /**
     * Creates new form FormMaterias
     */
    public ComidaDietaPanel11(ComidaPanelMain comidaPanelMain) {
        initComponents();
        this.comidaPanelMain = comidaPanelMain;
        controladorComidaDietaPanel = new ControladorComidaDietaPanel(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMateriaPpal = new javax.swing.JPanel();
        jPanelMateriaTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelVolver = new javax.swing.JLabel();
        jPanelMateriaForm = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListDieta = new javax.swing.JList<>(listModelDieta);
        jScrollPane2 = new javax.swing.JScrollPane();
        jListComida = new javax.swing.JList<>(listModelComida);
        jPanelMateriaBotones = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();

        setBorder(null);
        setMaximizable(true);
        setResizable(true);
        setOpaque(false);

        jPanelMateriaPpal.setPreferredSize(new java.awt.Dimension(640, 480));
        jPanelMateriaPpal.setLayout(new java.awt.BorderLayout());

        jPanelMateriaTitulo.setBackground(new java.awt.Color(39, 54, 64));

        jLabelTitulo.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 36)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Materia");

        jLabelVolver.setFont(new java.awt.Font("JetBrains Mono NL Thin", 1, 12)); // NOI18N
        jLabelVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BackArrow48px.png"))); // NOI18N
        jLabelVolver.setText("Volver");
        jLabelVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelVolverMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelMateriaTituloLayout = new javax.swing.GroupLayout(jPanelMateriaTitulo);
        jPanelMateriaTitulo.setLayout(jPanelMateriaTituloLayout);
        jPanelMateriaTituloLayout.setHorizontalGroup(
            jPanelMateriaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMateriaTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelVolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(637, Short.MAX_VALUE))
        );
        jPanelMateriaTituloLayout.setVerticalGroup(
            jPanelMateriaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMateriaTituloLayout.createSequentialGroup()
                .addGroup(jPanelMateriaTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanelMateriaPpal.add(jPanelMateriaTitulo, java.awt.BorderLayout.NORTH);

        jListDieta.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(jListDieta);

        jListComida.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 16)); // NOI18N
        jScrollPane2.setViewportView(jListComida);

        javax.swing.GroupLayout jPanelMateriaFormLayout = new javax.swing.GroupLayout(jPanelMateriaForm);
        jPanelMateriaForm.setLayout(jPanelMateriaFormLayout);
        jPanelMateriaFormLayout.setHorizontalGroup(
            jPanelMateriaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMateriaFormLayout.createSequentialGroup()
                .addContainerGap(379, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(334, 334, 334))
            .addGroup(jPanelMateriaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMateriaFormLayout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(653, Short.MAX_VALUE)))
        );
        jPanelMateriaFormLayout.setVerticalGroup(
            jPanelMateriaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMateriaFormLayout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelMateriaFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMateriaFormLayout.createSequentialGroup()
                    .addContainerGap(75, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanelMateriaPpal.add(jPanelMateriaForm, java.awt.BorderLayout.CENTER);

        jButtonNuevo.setFont(new java.awt.Font("JetBrains Mono NL Thin", 0, 14)); // NOI18N
        jButtonNuevo.setText("Nuevo");
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
        jButtonEliminar.setText("Eliminar");
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
        jButtonSalir.setText("Salir");
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

        javax.swing.GroupLayout jPanelMateriaBotonesLayout = new javax.swing.GroupLayout(jPanelMateriaBotones);
        jPanelMateriaBotones.setLayout(jPanelMateriaBotonesLayout);
        jPanelMateriaBotonesLayout.setHorizontalGroup(
            jPanelMateriaBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMateriaBotonesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanelMateriaBotonesLayout.setVerticalGroup(
            jPanelMateriaBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMateriaBotonesLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanelMateriaBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanelMateriaPpal.add(jPanelMateriaBotones, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanelMateriaPpal, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMateriaPpal, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        controladorComidaDietaPanel.salirMateriaActionPerformed(evt);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        controladorComidaDietaPanel.nuevoMateriaActionPerformed(evt);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        controladorComidaDietaPanel.eliminarMateriaActionPerformed(evt);
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        controladorComidaDietaPanel.guardarMateriaActionPerformed(evt);
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonNuevoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNuevoMouseEntered
        controladorComidaDietaPanel.buttonNuevoMouseEntered(evt);
    }//GEN-LAST:event_jButtonNuevoMouseEntered

    private void jButtonNuevoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNuevoMouseExited
        controladorComidaDietaPanel.buttonNuevoMouseExited(evt);
    }//GEN-LAST:event_jButtonNuevoMouseExited

    private void jButtonEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEliminarMouseEntered
        controladorComidaDietaPanel.buttonEliminarMouseEntered(evt);
    }//GEN-LAST:event_jButtonEliminarMouseEntered

    private void jButtonEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEliminarMouseExited
        controladorComidaDietaPanel.buttonEliminarMouseExited(evt);
    }//GEN-LAST:event_jButtonEliminarMouseExited

    private void jButtonGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarMouseEntered
        controladorComidaDietaPanel.buttonGuardarMouseEntered(evt);
    }//GEN-LAST:event_jButtonGuardarMouseEntered

    private void jButtonGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarMouseExited
        controladorComidaDietaPanel.buttonGuardarMouseExited(evt);
    }//GEN-LAST:event_jButtonGuardarMouseExited

    private void jButtonSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSalirMouseEntered
        controladorComidaDietaPanel.buttonSalirMouseEntered(evt);
    }//GEN-LAST:event_jButtonSalirMouseEntered

    private void jButtonSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSalirMouseExited
        controladorComidaDietaPanel.buttonSalirMouseExited(evt);
    }//GEN-LAST:event_jButtonSalirMouseExited

    private void jButtonNuevoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonNuevoFocusGained
        controladorComidaDietaPanel.buttonNuevoFocusGained(evt);
    }//GEN-LAST:event_jButtonNuevoFocusGained

    private void jButtonNuevoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonNuevoFocusLost
        controladorComidaDietaPanel.buttonNuevoFocusLost(evt);
    }//GEN-LAST:event_jButtonNuevoFocusLost

    private void jButtonEliminarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonEliminarFocusGained
        controladorComidaDietaPanel.buttonEliminarFocusGained(evt);
    }//GEN-LAST:event_jButtonEliminarFocusGained

    private void jButtonEliminarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonEliminarFocusLost
        controladorComidaDietaPanel.buttonEliminarFocusLost(evt);
    }//GEN-LAST:event_jButtonEliminarFocusLost

    private void jButtonGuardarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonGuardarFocusGained
        controladorComidaDietaPanel.buttonGuardarFocusGained(evt);
    }//GEN-LAST:event_jButtonGuardarFocusGained

    private void jButtonGuardarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonGuardarFocusLost
        controladorComidaDietaPanel.buttonGuardarFocusLost(evt);
    }//GEN-LAST:event_jButtonGuardarFocusLost

    private void jButtonSalirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonSalirFocusGained
        controladorComidaDietaPanel.buttonSalirFocusGained(evt);
    }//GEN-LAST:event_jButtonSalirFocusGained

    private void jButtonSalirFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButtonSalirFocusLost
        controladorComidaDietaPanel.buttonSalirFocusLost(evt);
    }//GEN-LAST:event_jButtonSalirFocusLost

    private void jLabelVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelVolverMouseClicked
        controladorComidaDietaPanel.labelVolverMouseClicked(evt);
    }//GEN-LAST:event_jLabelVolverMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelVolver;
    private javax.swing.JList<Comida> jListComida;
    private javax.swing.JList<Dieta> jListDieta;
    private javax.swing.JPanel jPanelMateriaBotones;
    private javax.swing.JPanel jPanelMateriaForm;
    private javax.swing.JPanel jPanelMateriaPpal;
    private javax.swing.JPanel jPanelMateriaTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    public DefaultListModel<Comida> getListModelComida() {
        return listModelComida;
    }

    public DefaultListModel<Dieta> getListModelDieta() {
        return listModelDieta;
    }
    
    public ComidaPanelMain getComidaPanelMain() {
        return comidaPanelMain;
    }

    public int getDesdePanel() {
        return desdePanel;
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

    public JList<Dieta> getjListDieta() {
        return jListDieta;
    }
    
    public JList<Comida> getjListComida() {
        return jListComida;
    }

    
    
    
    
}
