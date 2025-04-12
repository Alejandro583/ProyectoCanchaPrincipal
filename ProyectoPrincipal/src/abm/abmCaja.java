package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloCaja;

public class abmCaja extends conexion {
    sesion oSesion;

    public abmCaja(sesion pSesion) {
        oSesion = pSesion;
    }

    public boolean generarCaja(modeloCaja pModelo) {
        PreparedStatement ps = null;
        Connection con = getAbrirConexion();
        ResultSet rs = null;
        String sql = "";

        try {
            // Verificar si ya hay una caja abierta por el usuario
            sql = "SELECT * FROM caja WHERE fk_usuario = ? AND estado = 1";
            ps = con.prepareStatement(sql);
            ps.setInt(1, oSesion.getIdUsuario());
            rs = ps.executeQuery();

            if (!rs.next()) {
                // Insertar nueva caja
                sql = "INSERT INTO caja(total, efectivo, tarjeta, fecha, estado, fk_usuario) VALUES (?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setDouble(1, pModelo.getTotal());
                ps.setDouble(2, pModelo.getEfectivo());
                ps.setDouble(3, pModelo.getTarjeta());
                ps.setString(4, pModelo.getFecha()); // asegurate que sea formato "yyyy-MM-dd"
                ps.setBoolean(5, pModelo.isEstado());
                ps.setInt(6, pModelo.getFk_usuario());
                ps.execute();

                // Volver a consultar para llenar el modelo
                sql = "SELECT * FROM caja WHERE fk_usuario = ? AND estado = 1";
                ps = con.prepareStatement(sql);
                ps.setInt(1, oSesion.getIdUsuario());
                rs = ps.executeQuery();
                rs.next();
            }

            if (rs.getRow() >= 1) {
                // Llenar modelo
                pModelo.setId_caja(rs.getInt("id_caja"));
                pModelo.setTotal(rs.getDouble("total"));
                pModelo.setEfectivo(rs.getDouble("efectivo"));
                pModelo.setTarjeta(rs.getDouble("tarjeta"));
                pModelo.setFecha(rs.getString("fecha"));
                pModelo.setEstado(rs.getBoolean("estado"));
                pModelo.setFk_usuario(rs.getInt("fk_usuario"));

                con.close();
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            setCerrarConexion(con);
        }
    }
}

