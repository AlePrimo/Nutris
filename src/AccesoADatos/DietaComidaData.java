package AccesoADatos;

import Entidades.DietaComida;
import Entidades.Horario;
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

public class DietaComidaData {
   
   private DietaComida dietaComida;
   private Connection con=null;
   private ComidaData cd;
   private DietaData dd;
   
   public DietaComidaData(Connection connection) {
       con= connection;
       cd=new ComidaData(connection);
       dd=new DietaData(connection);
    }
    
    public boolean ingresarDietaComida(DietaComida dietaComida) {
        String sql = "INSERT INTO nutris.dietacomida (idComida,idDieta,horario) VALUES(?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, dietaComida.getComida().getIdComida());
            ps.setInt(2, dietaComida.getDieta().getIdDieta());
            ps.setString(3, dietaComida.getHorario().toString());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                dietaComida.setIdDietaComida(rs.getInt(1));
                showMessage("Se genero una dietaComida con id :" + dietaComida.getIdDietaComida());
                return true;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de dietaComidas");
            return false;
        }
        return false;
    }
   
    public boolean modificarDietaComida(DietaComida dietaComida) {
        String sql = "UPDATE nutris.dietacomida SET idComida=?, idDieta=?, horario=? WHERE idDietaComida=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, dietaComida.getComida().getIdComida());
            ps.setInt(2, dietaComida.getDieta().getIdDieta());
            ps.setString(3, dietaComida.getHorario().toString());
            ps.setInt(4, dietaComida.getIdDietaComida());
            if (ps.executeUpdate() == 1) {
                showMessage("Se ha modificado la dietaComida con id = " + dietaComida.getIdDietaComida());
                return true;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de dietaComidas");
            return false;
        }
        return false;
    }
   
    public boolean eliminarDietaComida(int id) {
        String sql = "DELETE nutris.dietaComida WHERE idDietaComida=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                showMessage("Se ha eliminado la dietaComida con id = " + dietaComida.getIdDietaComida());
                return true;
            } else {
                showMessage("No existe una dietaComida con ese id");
                return false;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de dietaComidas");
            return false;
        }
    }
   
    public List<DietaComida> listarComidasPorDieta(int idDieta) {
        ArrayList<DietaComida> listaPorDieta = new ArrayList<>();
        String sql = "SELECT * FROM nutris.dietacomida WHERE idDieta=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idDieta);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    listaPorDieta.add(extraerDietaComidaDelResulset(rs));
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de dietaComidas");
        }
        return listaPorDieta;
    }
    
    private void showMessage(String msg) {
        Font titleFont = new Font("JetBrains Mono NL Thin", Font.BOLD, 18);
        Font msgFont = new Font("JetBrains Mono NL Thin", Font.PLAIN, 16);
        Font buttonFont = new Font("JetBrains Mono NL Thin", Font.BOLD, 16);
        UIManager.put("OptionPane.titleFont", titleFont);
        UIManager.put("OptionPane.font", new Font("JetBrains Mono NL Thin", Font.BOLD, 8));
        UIManager.put("OptionPane.messageFont", msgFont);
        UIManager.put("Button.Font", buttonFont);
        JOptionPane.showMessageDialog(null, msg, "TESTING", JOptionPane.INFORMATION_MESSAGE);
    }
   
    private DietaComida extraerDietaComidaDelResulset(ResultSet rs) throws SQLException {
        dietaComida = new DietaComida();
        dietaComida.setIdDietaComida(rs.getInt("idDietaComida"));
        dietaComida.setComida(cd.buscarComida(rs.getInt("idComida")));
        dietaComida.setDieta(dd.buscarDietaPorId(rs.getInt("idDieta")));
        dietaComida.setHorario(Horario.valueOf(rs.getString("horario")));
        return dietaComida;
    }
   
   
   
   
   
    
}
