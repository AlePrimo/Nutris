/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import static java.lang.Math.pow;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public class IMC {
    
    public static Map<String,Object> calcIMC(double alt){
        
        Map<String, Object> cIMC = new HashMap<>();
        double pesoMinimo = pow(2, alt) * 18.5;
        double pesoMaximo = pow(2, alt) * 24.9;
        String rangoIMC = String.format( "%.2f ~ %.2f", pesoMinimo,pesoMaximo);

        cIMC.put("pesoMinimo", pesoMinimo);
        cIMC.put("pesoMaximo", pesoMaximo);
        cIMC.put("rangoIMC", rangoIMC);

        return cIMC;
    }
}
