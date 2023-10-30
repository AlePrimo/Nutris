
package AccesoADatos;

import Entidades.Historial;
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

public class HistorialData {
    
     private  Connection con;
     private Historial historial;
     private DietaData dietaData;
     private PacienteData pacienteData;

    public HistorialData(Connection connection) {
        con=connection;
        historial=new Historial();
        dietaData=new DietaData(connection);
        pacienteData=new PacienteData(connection);
    }
     
     
    public boolean guardarHistorial(Historial historial) {
        String sql = "Insert into nutris.historial (idPaciente,idDieta, pesoActual, fechaRegistro) values (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, historial.getPaciente().getIdPaciente());
            ps.setInt(2, historial.getDieta().getIdDieta());
            ps.setDouble(3, historial.getPesoActual());
            ps.setDate(4, Date.valueOf(historial.getFechaRegistro()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                historial.setIdHistorial(rs.getInt(1));
                showMessage("Se genero un historial con id :" + historial.getIdHistorial());
                return true;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla Historial");
            return false;
        }
        return false;
    }
    
    
    public boolean modificarHistorial(Historial historial) {
        String sql = "Update nutris.historial SET idPaciente=?, idDieta=?, pesoaActual=?, fechaRegistro=? where idHistorial=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, historial.getPaciente().getIdPaciente());
            ps.setInt(2, historial.getDieta().getIdDieta());
            ps.setDouble(3, historial.getPesoActual());
            ps.setDate(4, Date.valueOf(historial.getFechaRegistro()));
            ps.setInt(5, historial.getIdHistorial());
            if (ps.executeUpdate() == 1) {
                showMessage("Se modifico el  historial con id :" + historial.getIdHistorial());
                return true;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla Historial");
            return false;
        }
        return false;
    }
    
    
    public Historial buscarHistorialPorId(int idHistorial) {
        String sql = "SELECT * FROM nutris.historial WHERE idHistorial=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idHistorial);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return extraerHistorialDelResultSet(rs);
                } else {
                    showMessage("No existe un historial con ese id");
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla Historial");
        }
        return null;
    }
    
    public List<Historial> historialTotal() {
        ArrayList<Historial> total = new ArrayList<>();
        String sql = "SELECT * FROM nutris.historial ";
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                total.add(extraerHistorialDelResultSet(rs));
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla Historial");
        }
        return total;
    }
    
    public List<Historial> historialPorPaciente(int idPaciente) {
        ArrayList<Historial> listaPaciente = new ArrayList<>();
        String sql = "SELECT * FROM nutris.historial WHERE idPaciente=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idPaciente);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    listaPaciente.add(extraerHistorialDelResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla Historial");
        }
        return listaPaciente;
    }
    
    public List<Historial> historialPorPacienteXDieta(int idPaciente, int idDieta) {
        ArrayList<Historial> listaPacienteXDieta = new ArrayList<>();
        String sql = "SELECT * FROM nutris.historial WHERE idPaciente=? AND idDieta=?";

        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idPaciente);
            ps.setInt(2, idDieta);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    listaPacienteXDieta.add(extraerHistorialDelResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla Historial");
        }
        return listaPacienteXDieta;
    }
    
    public List<Historial> historialPorDieta(int idDieta) {
        ArrayList<Historial> listaDieta = new ArrayList<>();
        String sql = "SELECT * FROM nutris.historial WHERE idDieta=?";        
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idDieta);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    listaDieta.add(extraerHistorialDelResultSet(rs));
                }
            }            
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla Historial");
        }        
        return listaDieta;
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
    
    private Historial extraerHistorialDelResultSet(ResultSet rs) throws SQLException {
        historial = new Historial();
        historial.setIdHistorial(rs.getInt("idHistorial"));
        historial.setDieta(dietaData.buscarDietaPorId(rs.getInt("idDieta")));
        historial.setPaciente(pacienteData.buscarPacientePorId(rs.getInt("idPaciente")));
        historial.setPesoActual(rs.getDouble("pesoActual"));
        historial.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
        return historial;
    } 
    
    
    
    
    
    
    }
    

