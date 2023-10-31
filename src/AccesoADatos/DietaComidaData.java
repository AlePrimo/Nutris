package AccesoADatos;

import Entidades.Comida;
import Entidades.Dieta;
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
    private Connection con = null;
    private ComidaData cd;
    private DietaData dd;
    private Dieta dieta;
    private PacienteData pd;
    private Comida comida;

    public DietaComidaData(Connection connection) {
        con = connection;
        cd = new ComidaData(connection);
        dd = new DietaData(connection);
        pd = new PacienteData(connection);
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
    
    public List<DietaComida> obtenerDietaComida(){
        List<DietaComida> inscripciones = new ArrayList<>();
        String sql = "SELECT idDietaComida, idComida, idDieta, horario FROM nutris.dietacomida";
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                inscripciones.add(extraerDietaComidaDelResulset(rs));
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de dietaComidas");
        }
        return inscripciones;
    }
   
    public List<DietaComida> listarComidasPorDieta(int idDieta) {
        ArrayList<DietaComida> listaPorDieta = new ArrayList<>();
        String sql = "SELECT idDietaComida, idComida, idDieta, horario FROM nutris.dietacomida WHERE idDieta=?";
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
    
    public List<Comida> obtenerComidasEnDieta(int idDieta) {
        List<Comida> comidas = new ArrayList<>();
        String sql = "SELECT c.idComida, c.nombre, c.detalle, c.calorias, c.estado FROM nutris.dietacomida AS dc, nutris.comida AS c WHERE dc.idComida = c.idComida AND dc.idDieta = ?";
//        String sql1 = "SELECT c.idComida, c.nombre, c.detalle, c.calorias, c.estado FROM nutris.dietacomida AS dc INNER JOIN nutris.comida AS c ON dc.idComida = c.idComida WHERE dc.idDieta = ?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idDieta);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    comidas.add(extraerComidaDelResulset(rs));
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al obtener Inscripciones: " + ex.getMessage());
        }
        return comidas;
    }
    
    public List<Comida> obtenerNoComidasEnDieta(int idDieta) {
        List<Comida> comidas = new ArrayList<>();
        String sql = "SELECT c.idComida, c.nombre, c.detalle, c.calorias, c.estado FROM nutris.comida AS c WHERE c.idComida NOT IN (SELECT dc.idComida FROM nutris.dietacomida AS dc WHERE dc.idDieta = ?)";
//        String sql1 = "SELECT c.idComida, c.nombre, c.detalle, c.calorias, c.estado FROM nutris.comida AS c LEFT JOIN nutris.dietacomida AS dc ON c.idComida = dc.idComida AND dc.idDieta = ? WHERE dc.idComida IS NULL";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idDieta);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    comidas.add(extraerComidaDelResulset(rs));
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al obtener Inscripciones: " + ex.getMessage());
        }
        return comidas;
    }
    
    public List<Dieta> obtenerDietasDeComida(int idComida) {//id de materia

        List<Dieta> dietas = new ArrayList<>();
        String sql = "SELECT d.idDieta, d.nombre, d.idPaciente, d.fechaInicio, d.pesoInicial, d.pesoActual, d.fechaFinal, d.estado FROM nutris.dietacomida AS dc, nutris.dieta AS d WHERE dc.idDieta = d.idDieta AND dc.idComida = ?";
//        String sql1 = "SELECT d.idDieta, d.nombre, d.idPaciente, d.fechaInicio, d.pesoInicial, d.pesoActual, d.fechaFinal, d.estado FROM nutris.dietacomida INNER JOIN nutris. ON inscripcion.idMateria = materia.idMateria INNER JOIN dieta ON inscripcion.idDieta = ?";

        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idComida);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    dietas.add(extraerDietaDelResulset(rs));
                }
            }

        } catch (SQLException ex) {
            showMessage("Error al obtener Inscripciones: " + ex.getMessage());
        }
        return alumnos;
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
   
   private Dieta extraerDietaDelResultSet(ResultSet rs) throws SQLException {
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
