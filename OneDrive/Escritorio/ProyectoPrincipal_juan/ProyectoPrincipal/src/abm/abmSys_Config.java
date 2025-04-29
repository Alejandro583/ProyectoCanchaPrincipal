package abm;

import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloSys_Config;

public class abmSys_Config extends config.conexion {
    sesion oSesion;

    public abmSys_Config(sesion pSesion) {
        oSesion = pSesion;
    }

    // Obtener configuración (se asume que solo hay 1 registro)
    public modeloSys_Config obtenerConfiguracion() {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        modeloSys_Config config = null;

        String sql = "SELECT * FROM sys_confi LIMIT 1";
        try {
            consulta = conex.prepareStatement(sql);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                config = new modeloSys_Config(
                    resultado.getInt("id"),
                    resultado.getInt("dec_costo"),
                    resultado.getInt("dec_precio"),
                    resultado.getInt("dec_cantidad"),
                    resultado.getString("config_moneda"),
                    resultado.getString("config_regional"),
                    resultado.getString("factura"),
                    resultado.getInt("factura_seq")
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener configuración: " + e.getMessage(), oSesion.getTituloMensaje(), 1);
        }

        return config;
    }

    // Agregar configuración
    public boolean agregarConfiguracion(modeloSys_Config config) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "INSERT INTO sys_confi (dec_costo, dec_precio, dec_cantidad, config_moneda, config_regional, factura, factura_seq) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, config.getDec_costo());
            consulta.setInt(2, config.getDec_precio());
            consulta.setInt(3, config.getDec_cantidad());
            consulta.setString(4, config.getConfig_moneda());
            consulta.setString(5, config.getConfig_regional());
            consulta.setString(6, config.getFactura());
            consulta.setInt(7, config.getFactura_seq());
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar configuración: " + e.getMessage(), oSesion.getTituloMensaje(), 1);
            return false;
        }
    }

    // Modificar configuración
    public boolean modificarConfiguracion(modeloSys_Config config) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "UPDATE sys_confi SET dec_costo=?, dec_precio=?, dec_cantidad=?, config_moneda=?, config_regional=?, factura=?, factura_seq=? WHERE id=?";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, config.getDec_costo());
            consulta.setInt(2, config.getDec_precio());
            consulta.setInt(3, config.getDec_cantidad());
            consulta.setString(4, config.getConfig_moneda());
            consulta.setString(5, config.getConfig_regional());
            consulta.setString(6, config.getFactura());
            consulta.setInt(7, config.getFactura_seq());
            consulta.setInt(8, config.getId());
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar configuración: " + e.getMessage(), oSesion.getTituloMensaje(), 1);
            return false;
        }
    }

    // Eliminar configuración
    public boolean eliminarConfiguracion(int id) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "DELETE FROM sys_confi WHERE id=?";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar configuración: " + e.getMessage(), oSesion.getTituloMensaje(), 1);
            return false;
        }
    }
}
