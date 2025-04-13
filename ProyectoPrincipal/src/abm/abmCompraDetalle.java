package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloCompraDetalle;

public class abmCompraDetalle extends conexion {
    sesion oSesion;

    public abmCompraDetalle(sesion pSesion) {
        oSesion = pSesion;
    }

    public DefaultTableModel cargarTabla(String condicion) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"CODIGO", "PRODUCTO", "CANTIDAD", "COSTO", "IVA", "COSTO MEDIO", "FK COMPRA", "FK PRODUCTO", "ESTADO"});

        Connection conex = getAbrirConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM compra_detalle " + condicion;

        try {
            ps = conex.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    rs.getInt("Id_compra_detalle"),
                    rs.getString("Compra_producto"),
                    rs.getInt("Cantidad"),
                    rs.getDouble("Costo"),
                    rs.getInt("Iva"),
                    rs.getDouble("Costo_medio"),
                    rs.getInt("Fk_compra"),
                    rs.getInt("Fk_producto"),
                    rs.getInt("Estado")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tabla: " + e, oSesion.getTituloMensaje(), 1);
        }

        return modeloTabla;
    }
    public modeloCompraDetalle compraDetalleExiste(int id_compra_detalle) {
    Connection conex = getAbrirConexion();
    PreparedStatement ps = null;
    ResultSet rs = null;
    modeloCompraDetalle detalle = null;

    String sql = "SELECT * FROM compra_detalle WHERE Id_compra_detalle = ?";

    try {
        ps = conex.prepareStatement(sql);
        ps.setInt(1, id_compra_detalle);
        rs = ps.executeQuery();

        if (rs.next()) {
            detalle = new modeloCompraDetalle();
            detalle.setId_compra_detalle(rs.getInt("Id_compra_detalle"));
            detalle.setCosto(rs.getDouble("Costo"));
            detalle.setCantidad(rs.getInt("Cantidad"));
            detalle.setCompra_producto(rs.getString("Compra_producto"));
            detalle.setCosto_medio(rs.getDouble("Costo_medio"));
            detalle.setIva(rs.getInt("Iva"));
            detalle.setEstado(rs.getInt("Estado"));
            detalle.setFk_compra(rs.getInt("Fk_compra"));
            detalle.setFk_producto(rs.getInt("Fk_producto"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al verificar detalle: " + e.getMessage(), oSesion.getTituloMensaje(), 1);
    }

    return detalle;
}


    public boolean agregarDetalle(modeloCompraDetalle detalle) {
        Connection conex = getAbrirConexion();
        PreparedStatement ps = null;
        String sql = "INSERT INTO compra_detalle (Costo, Cantidad, Compra_producto, Costo_medio, Iva, Estado, Fk_compra, Fk_producto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = conex.prepareStatement(sql);
            ps.setDouble(1, detalle.getCosto());
            ps.setInt(2, detalle.getCantidad());
            ps.setString(3, detalle.getCompra_producto());
            ps.setDouble(4, detalle.getCosto_medio());
            ps.setInt(5, detalle.getIva());
            ps.setInt(6, detalle.getEstado());
            ps.setInt(7, detalle.getFk_compra());
            ps.setInt(8, detalle.getFk_producto());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean modificarDetalle(modeloCompraDetalle detalle) {
        Connection conex = getAbrirConexion();
        PreparedStatement ps = null;
        String sql = "UPDATE compra_detalle SET Costo = ?, Cantidad = ?, Compra_producto = ?, Costo_medio = ?, Iva = ?, Estado = ?, Fk_compra = ?, Fk_producto = ? WHERE Id_compra_detalle = ?";

        try {
            ps = conex.prepareStatement(sql);
            ps.setDouble(1, detalle.getCosto());
            ps.setInt(2, detalle.getCantidad());
            ps.setString(3, detalle.getCompra_producto());
            ps.setDouble(4, detalle.getCosto_medio());
            ps.setInt(5, detalle.getIva());
            ps.setInt(6, detalle.getEstado());
            ps.setInt(7, detalle.getFk_compra());
            ps.setInt(8, detalle.getFk_producto());
            ps.setInt(9, detalle.getId_compra_detalle());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean eliminarDetalle(int idDetalle) {
        Connection conex = getAbrirConexion();
        PreparedStatement ps = null;
        String sql = "UPDATE compra_detalle SET Estado = 0 WHERE Id_compra_detalle = ?";

        try {
            ps = conex.prepareStatement(sql);
            ps.setInt(1, idDetalle);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}

