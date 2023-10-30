/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Administrador
 */
public class SetConnValues {
   private static final String tipoDB = "mysql";
//   private static final String tipoDB = "mariadb";
   
   private SetConnValues(){}

    public static String getTipoDB() {
        return tipoDB;
    }
    
}
