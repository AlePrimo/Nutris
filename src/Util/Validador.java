/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.toedter.calendar.JDateChooser;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class Validador {
    
    public static boolean validarNumeroEntero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarNumeroDecimal(String texto) {
        try {
            Double.parseDouble(texto.replace(",", "."));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarTextoYEspacio(String texto) {
        return texto.matches("^[A-Za-záéíóúÁÉÍÓÚñÑ'\\s]*$");
    }
    
    public static boolean validarFecha(JDateChooser dateChooser) {
        Date fechaSeleccionada = dateChooser.getDate();
        if (fechaSeleccionada != null) {
            LocalDate localDate = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            LocalDate fechaTopeInferior = LocalDate.of(1900, 1, 1);
            LocalDate fechaTopeSuperior = LocalDate.now().plusDays(1);
            if (localDate.isAfter(fechaTopeInferior)&&localDate.isBefore(fechaTopeSuperior)) {
                return true; 
            }
        }
        return false; 
    }
    
    public static boolean validarEMail(String email) {
        return email.matches("^(?i)\\w+([.-_]?\\w+)?@\\w+([.-_]?\\w+)?(\\.\\w{2,6}){1,2}$");
    }
    
    public static boolean validarTextoYEspacioV2(String texto) {
        return texto.matches("(?i)^[A-Za-z0-9ÁáÉéÍíÓóÚúÜü.,\\s]*$");
    }
    
    public static boolean validarNumeroTel(String texto) {
        return texto.matches("^((\\+?\\d{1,4})[-\\s]?\\d{1,4}?[-\\s]?)?\\d{1,7}$");
    }
    
    public static boolean validarNumeroCel(String texto) {
        return texto.matches("^(\\+?\\d{1,4})[-\\s]?15[-\\s]?\\d{1,4}[-\\s]?\\d{1,4}$");//("^(\\+?\\d{1,4}[-\\s]?\\d{1,4}?[-\\s]?)?15(\\d{1,4}?[-\\s]?)?\\d{1,7}$");
    }
    
}
