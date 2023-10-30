package AccesoADatos;

import Entidades.Paciente;
import Entidades.PacienteExtendido;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class PacienteData {
    
    private Paciente paciente;
    private Connection con;
    private boolean isDni;
    private boolean isCond1;
    private boolean isApellido;
    private boolean isCond2;
    private boolean isNombre;
    private boolean isCond3;
    private boolean isFechaNac;

    public PacienteData(Connection connection) {
       con= connection;
    }
   
    public boolean ingresarPaciente(Paciente paciente) {
        String sql = "INSERT INTO nutris.paciente  (nombre, apellido, dni, altura, domicilio, telefonoFijo, celular, mail, estado, fechaNac)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDni());
            ps.setString(4, "" + paciente.getAltura());
            ps.setString(5, paciente.getDomicilio());
            ps.setString(6, paciente.getTelefonoFijo());
            ps.setString(7, paciente.getCelular());
            ps.setString(8, paciente.getMail());
            ps.setBoolean(9, paciente.isEstado());
            ps.setDate(10, Date.valueOf(paciente.getFechaNac()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                paciente.setIdPaciente(rs.getInt(1));
                showMessage("Se ingreso un paciente con id :" + paciente.getIdPaciente());
            }
            return true;
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de pacientes");
            return false;
        }
    }
    
    public Paciente buscarPacientePorDni(String dni) {
        String sql = "SELECT * FROM nutris.paciente WHERE dni=? ";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return extraerPacienteDelResulset(rs);
                } else {
                    showMessage("No existe un paciente con ese dni ");
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla paciente");
        }
        return null;
    }
    
    public Paciente buscarPacientePorId(int id) {
        String sql = "SELECT * FROM nutris.paciente WHERE idPaciente=? ";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return extraerPacienteDelResulset(rs);
                } else {
                    showMessage("No existe un paciente con ese id ");
                }
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla paciente");
        }
        return null;
    }
    
     public boolean bajaPaciente(int id) {
        String sql = "Update nutris.paciente set estado=0 where idPaciente=?";
        try (PreparedStatement ps = con.prepareStatement(sql);){
            ps.setInt(1, id);
            int carga = ps.executeUpdate();
            if (carga == 1) {
                showMessage("Se ha dado de baja al paciente con id :" + id);
                return true;
            }else{
                showMessage("Paciente inexistente.");
                return false;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la tabla paciente");
            return false;
        }
    } 
    
    public boolean modificarPaciente(Paciente paciente) {
        String sql = "UPDATE nutris.paciente SET nombre=?, apellido=?, dni=?, altura=?, domicilio=?, "
                + "telefonoFijo=?, celular=?, mail=?, estado=?, fechaNac=? WHERE idPaciente=?";
        try (PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDni());
            ps.setString(4, ""+paciente.getAltura());
            ps.setString(5, paciente.getDomicilio());
            ps.setString(6, paciente.getTelefonoFijo());
            ps.setString(7, paciente.getCelular());
            ps.setString(8, paciente.getMail());
            ps.setBoolean(9, paciente.isEstado());
            ps.setDate(10, Date.valueOf(paciente.getFechaNac()));
            ps.setInt(11, paciente.getIdPaciente());
            if (ps.executeUpdate() == 1) {
                showMessage("Se ha modificado el paciente con id :" + paciente.getIdPaciente());
                return true;
            } else {
                showMessage("Paciente inexistente.");
                return false;
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de Pacientes");
            return false;
        }
    }
    
    public List<Paciente> listarPacientes() {
        String sql = "SELECT * FROM nutris.paciente ";
        ArrayList<Paciente> pacientes = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                pacientes.add(extraerPacienteDelResulset(rs));
            }
        } catch (SQLException ex) {
            showMessage("Error al conectarse a la base de datos de pacientes");
        }
        return pacientes;
    }
     
     public List<Paciente> listarPacientesAvanzado(Paciente paciente, List<String>sentencia, boolean boole) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = String.join("", sentencia); //"SELECT * FROM sistemaulp.paciente"; // WHERE estado = 1 ";
        
        if (!sentencia.get(2).equals("")) isDni = true;
        else isDni = false;
        if (!sentencia.get(3).equals("")) isCond1 = true;
        else isCond1 = false;
        if (!sentencia.get(4).equals("")) isApellido = true;
        else isApellido = false;
        if(!sentencia.get(5).equals("")) isCond2 = true;
        else isCond2 = false;
        if(!sentencia.get(6).equals("")) isNombre = true;
        else isNombre = false;
        if(!sentencia.get(7).equals("")) isCond3 = true;
        else isCond3 = false;
        if(!sentencia.get(8).equals("")) isFechaNac = true;
        else isFechaNac = false;
        int indice = 1;
        try (PreparedStatement ps = con.prepareStatement(sql);){//;
            if (!boole) {
                Paciente p = (Paciente) paciente;
                if(isDni) ps.setString(indice, p.getDni()+"%");
                else indice-=1; 
                if(isCond1) ps.setString(indice+=1, p.getApellido()+"%");
                else if(isApellido) ps.setString(indice+=1, p.getApellido()+"%");
                if(isCond2) ps.setString(indice+=1, p.getNombre()+"%");
                else if(isNombre) ps.setString(indice+=1, p.getNombre()+"%");
                if(isCond3) ps.setDate(indice+=1, Date.valueOf(p.getFechaNac()));
                else if(isFechaNac) ps.setDate(indice+=1, Date.valueOf(p.getFechaNac()));
            } else {
                    PacienteExtendido p = (PacienteExtendido) paciente;
                    
                    if(isDni) ps.setString(indice, p.getDni()+"%");
                    else indice-=1; 
                    if(isCond1) ps.setString(indice+=1, p.getApellido()+"%");
                    else if(isApellido) ps.setString(indice+=1, p.getApellido()+"%");
                    if(isCond2) ps.setString(indice+=1, p.getNombre()+"%");
                    else if(isNombre) ps.setString(indice+=1, p.getNombre()+"%");
                    if(isCond3) {
                        ps.setDate(indice+=1, Date.valueOf(p.getFechaNac()));
                        ps.setDate(indice+=1, Date.valueOf(p.getFechaNac2()));
                    }else if(isFechaNac){
                        ps.setDate(indice+=1, Date.valueOf(p.getFechaNac()));
                        ps.setDate(indice+=1, Date.valueOf(p.getFechaNac2()));
                    }
            }
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    pacientes.add(extraerPacienteDelResulset(rs));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteData.class.getName()).log(Level.SEVERE, null, ex);
            showMessage("Error al Buscar listado de Pacientes: " + ex.getMessage());
        }
        return pacientes;
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

    private Paciente extraerPacienteDelResulset(ResultSet rs) throws SQLException {
        paciente = new Paciente();
        paciente.setIdPaciente(rs.getInt("idPaciente"));
        paciente.setNombre(rs.getString("nombre"));
        paciente.setApellido(rs.getString("apellido"));
        paciente.setDni((rs.getString("dni")));
        paciente.setAltura(Double.parseDouble(rs.getString("altura")));
        paciente.setDomicilio(rs.getString("domicilio"));
        paciente.setTelefonoFijo(rs.getString("telefonoFijo"));
        paciente.setCelular(rs.getString("celular"));
        paciente.setMail(rs.getString("mail"));
        paciente.setEstado(rs.getBoolean("estado"));
        paciente.setFechaNac(rs.getDate("fechaNac").toLocalDate());

        return paciente;
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
