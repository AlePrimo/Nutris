package AccesoADatos;

import Entidades.Comida;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class ComidaData {
    private  Connection con;
    private Comida comida;

    public ComidaData(Connection connection) {
        con = connection;
        comida=new Comida();
    }
    
    public boolean guardarComida(Comida comida) {
        String sql = "INSERT INTO nutris.comida (nombre, detalle, calorias, estado) VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
            ps.setString(1, comida.getNombre());
            ps.setString(2, comida.getDetalle());
            ps.setDouble(3, comida.getCalorias());
            ps.setBoolean(4, comida.isEstado());
            ps.executeUpdate();
            ResultSet res = ps.getGeneratedKeys();
            if (res.next()) {
                comida.setIdComida(res.getInt(1));
                showMessage("Comida agregada con exito");
                return true;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla Comida");
            return false;
        }
        return false;
    }
    
    public boolean borrarComida(int idComida) {
        String sql = "UPDATE comida set estado=0 WHERE idComida=?";
        try (PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, idComida);
            if (ps.executeUpdate() == 1) {
                showMessage("Borrado exitoso");
                return true;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos Comidas");
            return false;
        }
        return false;
    }
    
    public List<Comida> obtenerComidas() {
        ArrayList<Comida> comidasList = new ArrayList<>();
        String sql = "SELECT * FROM nutris.comida";
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                comidasList.add(extraerComidaDelResulset(rs));
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos Comidas");
        }
        return comidasList;
    }
    
    public Comida buscarComida(int id) {
        String sql = "SELECT * FROM nutris.comida WHERE idComida=? ";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return extraerComidaDelResulset(rs);
                } else {
                    showMessage("No existe una comida con ese id ,ingrese el dato nuevamente!");
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos Comidas");
        }
        return null;
    }
    
    public boolean modificarComida(Comida comida) {
        String sql = "UPDATE nutris.comida SET nombre=?, detalle=?, calorias=?, estado=? WHERE idComida=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, comida.getNombre());
            ps.setString(2, comida.getDetalle());
            ps.setDouble(3, comida.getCalorias());
            ps.setInt(4, comida.getIdComida());
            ps.setBoolean(5, comida.isEstado());
            if (ps.executeUpdate() == 1) {
                showMessage("Comida modificada con exito");
                return true;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla Comida");
            return false;
        }
        return false;
    }
    
    public List<Comida> buscarComidasPorCalorias(double calorias) {
        ArrayList<Comida> comiList = new ArrayList<>();
        String sql = "SELECT * FROM nutris.comida WHERE calorias=? ";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setDouble(1, calorias);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    comiList.add(extraerComidaDelResulset(rs));
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos Comidas");
        }
        return comiList;
    }
   
    private void showMessage(String msg) {
        Font titleFont = new Font("JetBrains Mono NL Thin", Font.BOLD, 18);
        Font msgFont = new Font("JetBrains Mono NL Thin", Font.PLAIN, 16);
        Font buttonFont = new Font("JetBrains Mono NL Thin", Font.BOLD, 16);
        UIManager.put("OptionPane.titleFont", titleFont);
        UIManager.put("OptionPane.font", new Font("JetBrains Mono NL Thin", Font.BOLD, 8));
        UIManager.put("OptionPane.messageFont", msgFont);
        UIManager.put("Button.Font", buttonFont);
        JOptionPane.showMessageDialog(null, msg, "TESTING",JOptionPane.INFORMATION_MESSAGE );
    }
    
    
    private Comida extraerComidaDelResulset(ResultSet rs) throws SQLException {
                comida = new Comida();
                comida.setIdComida(rs.getInt("idComida"));
                comida.setNombre(rs.getString("nombre"));
                comida.setDetalle(rs.getString("detalle"));
                comida.setCalorias(rs.getDouble("calorias"));
                comida.setEstado(rs.getBoolean("estado"));
                return comida;
    
    }
    
    
}

