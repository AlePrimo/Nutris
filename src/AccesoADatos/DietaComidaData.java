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
    
    public boolean actualizarHorario(int idComida, int idDieta, String horario){
        String sql = "UPDATE nutris.dietacomida set horario=? WHERE idDieta=? AND idComida=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, horario);
            ps.setInt(2, idDieta);
            ps.setInt(3, idComida);
            if (ps.executeUpdate() == 1) return true;
            else return false;
        } catch (SQLException ex) {
            showMessage("Error al tratar de actualizar nota: " + ex.getMessage());
            return false;
        }
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
    
    public Horario obtenerHorarioDietaComida(int idComida, int idDieta){
        String sql = "Select horario from nutris.dietaComida where idComida = ? AND idDieta = ?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idComida);
            ps.setInt(2, idDieta);
            ps.executeQuery();
                if (rs.next()) {
                    dietaComi.add(extraerAlumnoDelResulset(rs));
                
            }

        } catch (SQLException ex) {
            showMessage("Error al obtener Inscripciones: " + ex.getMessage());
        }
        return alumnos;
    }

    public List<DietaComida> ListarTodoDietasComidas() {
        List<DietaComida> listaDietacomida = new ArrayList<>();
        String sql = "SELECT idDietaComida, idComida, idDieta, horario FROM nutris.dietacomida";
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                listaDietacomida.add(extraerDietaComidaDelResultSet(rs));
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de dietaComidas");
        }
        return listaDietacomida;
    }

    public List<DietaComida> listarDietaComidaPorDieta(int idDieta) {
        ArrayList<DietaComida> listaPorDieta = new ArrayList<>();
        String sql = "SELECT idDietaComida, idComida, idDieta, horario FROM nutris.dietacomida WHERE idDieta=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idDieta);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    listaPorDieta.add(extraerDietaComidaDelResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de dietaComidas");
        }
        return listaPorDieta;
    }
    
    public List<DietaComida> listarDietaComidaPorComida(int idComida) {
        ArrayList<DietaComida> listaPorDieta = new ArrayList<>();
        String sql = "SELECT idDietaComida, idComida, idDieta, horario FROM nutris.dietacomida WHERE idComida=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idComida);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    listaPorDieta.add(extraerDietaComidaDelResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de dietaComidas");
        }
        return listaPorDieta;
    }

    public List<Comida> ListarComidasEnDieta(int idDieta) {
        List<Comida> comidas = new ArrayList<>();
        String sql = "SELECT c.idComida, c.nombre, c.detalle, c.calorias, c.estado FROM nutris.dietacomida AS dc, nutris.comida AS c WHERE dc.idComida = c.idComida AND dc.idDieta = ? ORDER BY idComida";
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

    public List<Comida> ListarNoComidasEnDieta(int idDieta) {
        List<Comida> comidas = new ArrayList<>();
        String sql = "SELECT c.idComida, c.nombre, c.detalle, c.calorias, c.estado FROM nutris.comida AS c WHERE c.idComida NOT IN (SELECT dc.idComida FROM nutris.dietacomida AS dc WHERE dc.idDieta = ?) ORDER BY idComida";
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

    public List<Dieta> ListarDietasDeComida(int idComida) {//id de materia
        List<Dieta> dietas = new ArrayList<>();
        String sql = "SELECT d.idDieta, d.nombre, d.idPaciente, d.fechaInicio, d.pesoInicial, d.pesoActual, d.fechaFinal, d.estado FROM nutris.dietacomida AS dc, nutris.dieta AS d WHERE dc.idDieta = d.idDieta AND dc.idComida = ? ORDER BY idDieta";
//        String sql1 = "SELECT d.idDieta, d.nombre, d.idPaciente, d.fechaInicio, d.pesoInicial, d.pesoActual, d.fechaFinal, d.estado FROM nutris.dietacomida INNER JOIN nutris.dieta AS d ON dc.idDieta = d.idDieta WHERE dc.idComida = ?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idComida);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    dietas.add(extraerDietaDelResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al obtener Inscripciones: " + ex.getMessage());
        }
        return dietas;
    }

    public List<Dieta> ListarNoDietasDeComida(int idComida) {//id de materia
        List<Dieta> dietas = new ArrayList<>();
        String sql = "SELECT d.idDieta, d.nombre, d.idPaciente, d.fechaInicio, d.pesoInicial, d.pesoActual, d.fechaFinal, d.estado FROM nutris.dieta AS d WHERE d.idDieta NOT IN (SELECT dc.idDieta FROM nutris.dietacomida AS dc WHERE dc.idComida = ?) ORDER BY idDieta";
//        String sql1 = "SELECT d.idDieta, d.nombre, d.idPaciente, d.fechaInicio, d.pesoInicial, d.pesoActual, d.fechaFinal, d.estado FROM nutris.dieta AS d LEFT JOIN nutris.dietacomida AS dc ON d.idDieta = dc.idDieta AND dc.idComida = ? WHERE dc.idDieta IS NULL";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, idComida);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    dietas.add(extraerDietaDelResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al obtener Inscripciones: " + ex.getMessage());
        }
        return dietas;
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

    private DietaComida extraerDietaComidaDelResultSet(ResultSet rs) throws SQLException {
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
