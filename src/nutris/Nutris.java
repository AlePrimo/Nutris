/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutris;

import Vistas.WinAppNutris;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.jetbrains_mono.FlatJetBrainsMonoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.UIManager;

/**
 *
 * @author Administrador
 */
public class Nutris {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatLaf.setPreferredMonospacedFontFamily(FlatJetBrainsMonoFont.FAMILY);
        FlatMacDarkLaf.setup();
        UIManager.put("Button.arc", 999);
        UIManager.put("Component.arc", 999);
        UIManager.put("TextComponent.arc", 999);
       
        java.awt.EventQueue.invokeLater(() -> {
            new WinAppNutris().setVisible(true);
        });
    }
    
}
