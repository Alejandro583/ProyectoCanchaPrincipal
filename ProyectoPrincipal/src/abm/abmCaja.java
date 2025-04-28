package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloCaja;

public class abmCaja extends conexion {
    sesion oSesion;

    public abmCaja(sesion pSesion) {
        oSesion = pSesion;
    }
    
       public DefaultTableModel cargarTabla(String condicion) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"CÓDIGO","TOTAL","EFECTIVO","TARJETA", "FECHA", "ESTADO"});

        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        try {
            sql = "SELECT * FROM caja " + condicion;
            preparaConsulta = conex.prepareStatement(sql);
            resultado = preparaConsulta.executeQuery();

            while (resultado.next() == true) {
                modeloTabla.addRow(new Object[]{
                    resultado.getInt("Id_caja"),
                    resultado.getDouble("Total"),
                    resultado.getDouble("Efectivo"),
                    resultado.getDouble("Tarjeta"),
                    resultado.getDate("Fecha"),
                    resultado.getInt("Estado"),});   
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
        }
        return modeloTabla;
    }

     public modeloCaja CargarCaja(int idCaja) {
        Connection conex = getAbrirConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM caja WHERE id_caja = ?";
        modeloCaja caja = null;

        try {
            ps = conex.prepareStatement(sql);
            ps.setInt(1, idCaja);
            rs = ps.executeQuery();

            if (rs.next()) {
                caja = new modeloCaja();
                caja.setId_caja(rs.getInt("Id_caja"));
                caja.setTotal(rs.getDouble("Total"));
                caja.setEfectivo(rs.getDouble("Efectivo"));
                caja.setTarjeta(rs.getDouble("Tarjeta"));
                caja.setFecha(rs.getDate("Fecha"));
                caja.setEstado(rs.getBoolean("Estado"));
                caja.setFk_usuario(rs.getInt("Fk_usuario"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return caja;
    }

    public boolean agregarCaja(modeloCaja caja) {
        if (CargarCaja(caja.getId_caja()) != null) {
            return false;
        }

        Connection conex = getAbrirConexion();
        PreparedStatement ps = null;
        String sql = "INSERT INTO caja (Total, Efectivo, Tarjeta, Fecha, Estado, Fk_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            ps = conex.prepareStatement(sql);
            ps.setDouble(1, caja.getTotal());
            ps.setDouble(2, caja.getEfectivo());
            ps.setDouble(3, caja.getTarjeta());
            ps.setDate(4, caja.getFecha());
            ps.setBoolean(5, caja.isEstado());
            ps.setInt(6, caja.getFk_usuario());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean modificarCaja(modeloCaja caja) {
        Connection conex = getAbrirConexion();
        PreparedStatement ps = null;
        String sql = "UPDATE caja SET total = ?, Efectivo = ?, Tarjeta = ?, Fecha = ?, Estado = ?, Fk_usuario = ? WHERE Id_caja = ?";

        try {
            ps = conex.prepareStatement(sql);
            ps.setDouble(1, caja.getTotal());
            ps.setDouble(2, caja.getEfectivo());
            ps.setDouble(3, caja.getTarjeta());
            ps.setDate(4, caja.getFecha());
            ps.setBoolean(5, caja.isEstado());
            ps.setInt(6, caja.getFk_usuario());
            ps.setInt(7, caja.getId_caja());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean eliminarCaja(int idCaja) {
        Connection conex = getAbrirConexion();
        PreparedStatement ps = null;
        String sql = "UPDATE caja SET Estado = 0 WHERE Id_caja = ?";

        try {
            ps = conex.prepareStatement(sql);
            ps.setInt(1, idCaja);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public boolean aumentarEfectivo(double monto, int id_caja) {
    Connection conex = getAbrirConexion();
    PreparedStatement consulta = null;
    String sql;
    try {
        sql = "UPDATE caja SET Efectivo = Efectivo + ?, Total = Total + ? WHERE Id_caja = ?";
        consulta = conex.prepareStatement(sql);
        consulta.setDouble(1, monto);
        consulta.setDouble(2, monto);
        consulta.setInt(3, id_caja);
        consulta.executeUpdate();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        return false;
    }
}
    

public boolean aumentarTarjeta(double monto, int id_caja) {
    Connection conex = getAbrirConexion();
    PreparedStatement consulta = null;
    String sql;
    try {
        sql = "UPDATE caja SET Tarjeta = Tarjeta + ?, Total = Total + ? WHERE Id_caja = ?";
        consulta = conex.prepareStatement(sql);
        consulta.setDouble(1, monto);
        consulta.setDouble(2, monto);
        consulta.setInt(3, id_caja);
        consulta.executeUpdate();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        return false;
    }
}

public boolean reversionTotal(double monto, int id_caja) {
    Connection conex = getAbrirConexion();
    PreparedStatement consulta = null;
    String sql;
    try {
        sql = "UPDATE caja SET Efectivo = Efectivo - ?, Total = Total - ? WHERE Id_caja = ?";
        consulta = conex.prepareStatement(sql);
        consulta.setDouble(1, monto);
        consulta.setDouble(2, monto);
        consulta.setInt(3, id_caja);
        consulta.executeUpdate();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        return false;
    }
}

    public modeloCaja CargarCaja(modeloCaja oModeloCaja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean eliminarCaja(modeloCaja oModeloCaja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

