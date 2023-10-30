/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Vistas.WinAppNutris;
import java.awt.Dimension;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JPanel;

/**
 *
 * @author Administrador
 */
public class AnimacionPNL extends Thread{
    private final JPanel PanelWest;
    private final JPanel PanelCenter;
//    private final JPanel PanelCard1;
    private final int milisegundos;
    private final int saltos; 
    private final int parar;
    private final AtomicBoolean sentido;
//    private boolean isCheck=false;

    public AnimacionPNL(WinAppNutris winAppNutris, int milisegundos, int saltos, int parar, AtomicBoolean sentido) {
        this.PanelWest = winAppNutris.getPanelWest();
        this.PanelCenter = null;/*winAppNutris.getPanelCenter();*/
//        this.PanelCard1 = winAppNutris.getjPanelCard1();
        this.milisegundos = milisegundos;
        this.saltos = saltos;
        this.parar = parar;
        this.sentido = sentido;
        start();
    }
    
    @Override
    public void run(){
//        System.out.println("sentido: "+sentido);
        if (sentido.get()) {
            try {
//                Thread.sleep(1000);
                int test=PanelWest.getWidth();
                for (int i = PanelWest.getX(); i >= parar; i -= saltos) {
                    Thread.sleep(milisegundos);
                    PanelWest.setLocation(i, PanelWest.getY());
                    PanelCenter.setLocation(test, PanelCenter.getY());
                    System.out.println(PanelCenter.getWidth());
//                    PanelCenter.setSize(new Dimension(PanelCenter.getWidth() + saltos, PanelCenter.getHeight()));
//                    PanelCard1.setSize(PanelCard1.getWidth() + saltos, PanelCard1.getHeight());
                    test-=saltos;
                }
            } catch (InterruptedException e) {
                System.out.println("Error Thread Interrumpido: " + e);
            }

        } else {
//            isCheck = true;
            try {
                for (int i = PanelWest.getX(); i <= parar; i += saltos) {

                    Thread.sleep(milisegundos);
                    PanelWest.setLocation(i, PanelWest.getY());
                    PanelCenter.setLocation(PanelCenter.getX() + saltos, PanelCenter.getY());
//                    PanelCenter.setSize(PanelCenter.getWidth() - saltos, PanelCenter.getHeight());
//                    PanelCard1.setSize(PanelCard1.getWidth() - saltos, PanelCard1.getHeight());
                }

//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error Thread Interrumpido: " + e);
            }
        }
    
    }
    
    
    
    
}
