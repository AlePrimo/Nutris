/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutris;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Conexion {

    private static Connection connection;

    private Conexion() {
    }

    public static Connection getConexion(String driverDB) throws SQLException {
        if (driverDB.equals("mysql")) {
            mysqlConexion();
        } else if (driverDB.equals("mariadb")) {
            getConnection();
        }
        return connection;
    }

    private static void mysqlConexion() throws SQLException {
        Properties properties = new Properties();
        connection = null;

        try {
            properties.load(new FileInputStream("src/config/database.properties"));
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getConnection() {
        String URL = "jdbc:mariadb://localhost:3306/";
        String DB = "nutris";
        String USUARIO = "root";
        String PASSWORD = "";
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL + DB + "?useLegacyDateTimeCode=false&server&TimeZone=UTC" + "&user=" + USUARIO + "&password=" + PASSWORD);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al conectarse a base de datos " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar los drivers " + ex.getMessage());
            }
        }
    }
}
