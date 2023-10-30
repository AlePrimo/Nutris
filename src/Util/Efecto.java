/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Administrador
 */
public class Efecto {
    
    public Icon setIcon(String relPath, JButton bttn) {
        ImageIcon imageIcon;
        imageIcon = new ImageIcon(getClass().getResource(relPath));
//       ImageIcon icon = new ImageIcon(imageIcon.getImage().getScaledInstance((bttn.getPreferredSize().width-5), (bttn.getPreferredSize().height-5), java.awt.Image.SCALE_DEFAULT));
        ImageIcon icon = new ImageIcon(imageIcon.getImage().getScaledInstance((bttn.getWidth() + 5), (bttn.getHeight() + 5), java.awt.Image.SCALE_SMOOTH));
        return icon;
    }
    
    public void animacionBuscar(JButton jBttn, int size){
        if (jBttn.isEnabled()) {
            if (jBttn.getWidth() != size && jBttn.getHeight() != size) {
                jBttn.setSize(new Dimension(size, size));
                jBttn.setIcon(setIcon("../img/lupa1_1.png", jBttn));
            }
        }
    }
    
    public JButton efectoBttn(JButton jBttn, boolean boole, int sig, int state) {
        if (jBttn.isEnabled() && !boole) {
            Color color;
            switch (state) {
                case 1:
                    color = new java.awt.Color(56, 122, 255);   //tipo celeste
                    break;
                case 2:
                    color = new java.awt.Color(178, 87, 80);    //tipo rojo
                    break;
                case 3:
                    color = new java.awt.Color(94, 94, 94);     //tipo gris
                    break;
                default:
                    color = new java.awt.Color(94, 94, 94);
            }
            jBttn.setBackground(color);   //(new java.awt.Color(64, 135, 178));
            jBttn.setFont((!boole && sig > 0) ? new Font("JetBrains Mono NL Thin", Font.BOLD, 18) : new Font("JetBrains Mono NL Thin", Font.PLAIN, 14));
            jBttn.revalidate();
        }
        return jBttn;
    }
    
}
