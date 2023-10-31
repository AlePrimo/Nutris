package AccesoADatos;

import Entidades.Dieta;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class DietaData {

    private Dieta dieta = new Dieta();
    private PacienteData pd;
    private Connection con = null;

    public DietaData(Connection connection) {
        con = connection;
        pd = new PacienteData(connection);
    }

    public boolean ingresarDieta(Dieta dieta) {
        String sql = "INSERT INTO nutris.dieta  (nombre, idpaciente, fechaInicio, fechaFinal, pesoInicial, pesoActual, estado)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, dieta.getNombre());
            ps.setInt(2, dieta.getPaciente().getIdPaciente());
            ps.setDate(3, Date.valueOf(dieta.getFechaInicio()));
            ps.setDate(4, Date.valueOf(dieta.getFechaInicio()));
            ps.setString(5, "" + dieta.getPesoInicial());
            ps.setString(6, "" + dieta.getPesoActual());
            ps.setBoolean(7, dieta.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                dieta.setIdDieta(rs.getInt(1));
                showMessage("Se ingreso una dieta con id :" + dieta.getIdDieta());
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de dietas");
            return false;
        }
    }

    public List<Dieta> buscarDietasPorPaciente(int idPaciente) {
        String sql = "SELECT * FROM nutris.dieta WHERE idpaciente=? ";
        ArrayList<Dieta> dietasList = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idPaciente);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    dietasList.add(extraerDietaDelResultset(rs));
                } 
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla dieta");
        }
        return dietasList;
    }

    public Dieta buscarDietaPorId(int id) {
        String sql = "SELECT * FROM nutris.dieta WHERE idDieta=? ";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return extraerDietaDelResultset(rs);
                } else {
                    showMessage("No existe un dieta con ese ID ");
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla dieta");
        }
        return null;
    }

    public boolean eliminarDieta(int id) {
        String sql = "UPDATE nutris.dieta SET estado=0 WHERE idDieta=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                showMessage("Dieta suspendida");
                return true;
            }
        } catch (SQLException ex) {
            showMessage("Error al acceder a la tabla dieta");
            return false;
        }
        return false;
    }

    public boolean modificarDieta(Dieta dieta) {
        String sql = "UPDATE nutris.dieta SET nombre=?,idpaciente=?,fechaInicio=?, fechaFinal=?, pesoInicial=?, pesoActual=?, estado=? "
                + " WHERE idDieta=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dieta.getNombre());
            ps.setInt(2, dieta.getPaciente().getIdPaciente());
            ps.setDate(3, Date.valueOf(dieta.getFechaInicio()));
            ps.setDate(4, Date.valueOf(dieta.getFechaInicio()));
            ps.setString(5, "" + dieta.getPesoInicial());
            ps.setString(6, "" + dieta.getPesoActual());
            ps.setBoolean(7, dieta.isEstado());
            ps.setInt(8, dieta.getIdDieta());
            if (ps.executeUpdate() == 1) {
                showMessage("Se ha modificado el dieta con id :" + dieta.getIdDieta());
                return true;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de Dietas");
            return false;
        }
        return false;
    }

    public List<Dieta> listarDietas() {
        String sql = "SELECT * FROM nutris.dieta ORDER BY idDieta";
        ArrayList<Dieta> dietasList = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                dietasList.add(extraerDietaDelResultset(rs));
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de dietas");
        }
        return dietasList;
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

    private Dieta extraerDietaDelResultset(ResultSet rs) throws SQLException {
        dieta = new Dieta();
        dieta.setIdDieta(rs.getInt("idDieta"));
        dieta.setNombre(rs.getString("nombre"));
        dieta.setPaciente(pd.buscarPacientePorId(rs.getInt("idPaciente")));
        dieta.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
        dieta.setFechaFinal(rs.getDate("fechaFinal").toLocalDate());
        dieta.setPesoInicial(rs.getDouble("pesoInicial"));
        dieta.setPesoActual(rs.getDouble("pesoActual"));
        dieta.setEstado(rs.getBoolean("estado"));
        return dieta;
    }

}
