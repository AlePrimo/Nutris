/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JLabel;
/**
 *
 * @author Administrador
 */
public class AnimacionLBL extends Thread {
    private final String nombre;
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final int milisegundos;
    private boolean reverseColor = false;
    private final ColorRGBError colorError;
    private Map<Component,AtomicBoolean>componentes;
    private boolean boole;

    public AnimacionLBL(String nombre, int milisegundos, ColorRGBError colorError) {
        this.nombre = nombre;
        this.milisegundos = milisegundos;
        this.colorError = colorError;
        componentes = new HashMap<>();
    }

    @Override
    public void run() {
        while (running.get()) {
//            System.out.println("HOLA "+getNombre());
            
            actualizarColor();
            try {
                Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
                System.out.println("Error Thread Interrumpido: " + e);
                Thread.currentThread().interrupt();
            }
            if((colorError.getG()==0&&colorError.getB()==0)||(colorError.getG()==220&&colorError.getB()==220)) reverseColor = !reverseColor;
        }
    }

    private void actualizarColor() {
        if (reverseColor) {
            if (colorError.getG() > 0) {
                colorError.setG(colorError.getG() - 4);
            }
            if (colorError.getB() > 0) {
                colorError.setB(colorError.getB() - 4);
            }
        } else {
            if (colorError.getG() < 220) {
                colorError.setG(colorError.getG() + 4);
            }
            if (colorError.getB() < 220) {
                colorError.setB(colorError.getB() + 4);
            }
        }
        Color nuevoColor = new Color(colorError.getR(), colorError.getG(), colorError.getB());
        componentes.forEach((k, v) -> {
            if (v.get()) {
                if (k instanceof JLabel) {
                    JLabel lbl = (JLabel) k;
                    lbl.setForeground(nuevoColor);
                    lbl.setFont(new Font("JetBrains Mono NL Thin", Font.BOLD, 18));
                }
            } /*else if (k instanceof JLabel) {
                JLabel lbl = (JLabel) k;
                lbl.setForeground(new Color(220, 220, 220));
                lbl.setFont(new Font("JetBrains Mono NL Thin", Font.PLAIN, 18));
            }*/
        });
    }
    
    public void detener() {
        boole = false;
        componentes.forEach((k, v) -> {
            if (k instanceof JLabel) {
                JLabel lbl = (JLabel) k;
                lbl.setForeground(new Color(220, 220, 220));
                lbl.setFont(new Font("JetBrains Mono NL Thin", Font.PLAIN, 18));
            }
            boole|=v.get();
        });
        running.set(boole);
        if(!boole) componentes.clear();
        
    }
    
    public String getNombre() {
        return nombre;
    }

    public Map<Component, AtomicBoolean> getComponentes() {
        return componentes;
    }

    public void setComponentes(Map<Component, AtomicBoolean> componentes) {
        this.componentes = componentes;
    }
    
    public void agregarComponente(Component componente, AtomicBoolean activo) {
        componentes.put(componente, activo);
    }

    public void modificarEstadoComponente(Component componente, boolean nuevoEstado) {
        AtomicBoolean atomicBoolean = componentes.get(componente);
        if (atomicBoolean != null) {
            atomicBoolean.set(nuevoEstado);
        }
    }

    public boolean isBoole() {
        return boole;
    }
    
    
}
