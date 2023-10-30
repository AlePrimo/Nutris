/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Component;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Administrador
 */
public class ManejadorAnimacionLBL {
    
    private AnimacionLBL animacion;

    public void crearAnimacion(String nombre, Component component, int milisegundos, ColorRGBError colorError) {
        if (animacion == null) {
            animacion = new AnimacionLBL(nombre, milisegundos, colorError);
            animacion.start();
            animacion.agregarComponente(component, new AtomicBoolean(true));
        } else {
            if (!animacion.isAlive()) {
                animacion = new AnimacionLBL(nombre, milisegundos, colorError);
                animacion.start();
            }
            animacion.agregarComponente(component, new AtomicBoolean(true));
        }
    }

    public void detenerAnimacion(Component component) {
        animacion.modificarEstadoComponente(component, false);
        if (animacion != null) {
            animacion.detener();
            if (!animacion.isBoole() && animacion.isAlive()) {
                System.out.println("NULL");
                animacion = null;
            }
        }
    }
    
}
