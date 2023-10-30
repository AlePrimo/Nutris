/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Administrador
 */
public class ColorRGBError {
    private AtomicInteger r = new AtomicInteger(220);
    private AtomicInteger g = new AtomicInteger(220);
    private AtomicInteger b = new AtomicInteger(220);

    public int getR() {
        return r.get();
    }

    public int getG() {
        return g.get();
    }

    public int getB() {
        return b.get();
    }

    public void setR(int newValue) {
        r.set(newValue);
    }

    public void setG(int newValue) {
        g.set(newValue);
    }

    public void setB(int newValue) {
        b.set(newValue);
    }
    
}
