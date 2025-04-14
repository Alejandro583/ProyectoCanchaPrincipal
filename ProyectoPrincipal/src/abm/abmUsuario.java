package abm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloUsuario;
import javax.swing.table.DefaultTableModel;
import config.conexion;
import config.sesion;

public class abmUsuario extends conexion {

    private sesion oSesion;

    // Constructor sin sesión
    public abmUsuario() {
        oSesion = new sesion();
    }

    // Constructor con sesión
    public abmUsuario(sesion sesionActiva) {
        this.oSesion = sesionActiva;
    }

    public boolean agregarUsuario(modeloUsuario u) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "INSERT INTO Usuario (Nombre_usuario, Usuario, Contrasenha, Cargo) VALUES (?, ?, ?, ?)";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, u.getNombreUsuario());
            consulta.setString(2, u.getUsuario());
            consulta.setString(3, u.getContrasenha());
            consulta.setString(4, u.getCargo());
            consulta.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar usuario: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public modeloUsuario buscarUsuarioPorId(int idUsuario) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        String sql = "SELECT * FROM Usuario WHERE Id_usuario = ?";
        modeloUsuario u = null;

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, idUsuario);
            resultado = consulta.executeQuery();

            if (resultado.next()) {
                u = new modeloUsuario();
                u.setIdUsuario(resultado.getInt("Id_usuario"));
                u.setNombreUsuario(resultado.getString("Nombre_usuario"));
                u.setUsuario(resultado.getString("Usuario"));
                u.setContrasenha(resultado.getString("Contrasenha"));
                u.setCargo(resultado.getString("Cargo"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar usuario: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
        }

        return u;
    }

    public boolean modificarUsuario(modeloUsuario u) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "UPDATE Usuario SET Nombre_usuario = ?, Usuario = ?, Contrasenha = ?, Cargo = ? WHERE Id_usuario = ?";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, u.getNombreUsuario());
            consulta.setString(2, u.getUsuario());
            consulta.setString(3, u.getContrasenha());
            consulta.setString(4, u.getCargo());
            consulta.setInt(5, u.getIdUsuario());
            consulta.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar usuario: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean eliminarUsuario(int idUsuario) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "DELETE FROM Usuario WHERE Id_usuario = ?";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, idUsuario);
            consulta.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // 🆕 Método para cargar los usuarios en una grilla
    public DefaultTableModel cargarTabla(String condicion) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"ID", "NOMBRE COMPLETO", "USUARIO", "CONTRASEÑA", "CARGO"});

        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        ResultSet resultado = null;
        String sql = "SELECT * FROM Usuario " + condicion;

        try {
            preparaConsulta = conex.prepareStatement(sql);
            resultado = preparaConsulta.executeQuery();

            while (resultado.next()) {
                modeloTabla.addRow(new Object[]{
                    resultado.getInt("Id_usuario"),
                    resultado.getString("Nombre_usuario"),
                    resultado.getString("Usuario"),
                    resultado.getString("Contrasenha"),
                    resultado.getString("Cargo")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
        }

        return modeloTabla;
    }
}
