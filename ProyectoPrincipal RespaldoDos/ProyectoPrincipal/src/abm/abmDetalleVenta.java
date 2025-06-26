package abm;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.modeloDetalleVenta;

public class abmDetalleVenta extends config.conexion {

    // Método para agregar un detalle de venta
    public boolean agregarDetalleVenta(modeloDetalleVenta pDetalleVenta) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        try {
            sql = "INSERT INTO Venta_detalle (Precio, Venta_producto, Costo, Cantidad, Fk_venta, Fk_reserva) VALUES (?, ?, ?, ?, ?, ?)";
            consulta = conex.prepareStatement(sql);
            consulta.setBigDecimal(1, pDetalleVenta.getPrecio());
            consulta.setBigDecimal(2, pDetalleVenta.getVenta_producto());
            consulta.setBigDecimal(3, pDetalleVenta.getCosto());
            consulta.setInt(4, pDetalleVenta.getCantidad());
            consulta.setInt(5, pDetalleVenta.getFk_venta());
            consulta.setInt(6, pDetalleVenta.getFk_reserva());
            consulta.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar detalle de venta: " + e.getMessage());
            return false;
        }
    }

    // Método para modificar un detalle de venta
    public boolean modificarDetalleVenta(modeloDetalleVenta pDetalleVenta) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        try {
            sql = "UPDATE Venta_detalle SET Precio = ?, Venta_producto = ?, Costo = ?, Cantidad = ?, Fk_venta = ?, Fk_reserva = ? WHERE Id_venta_detalle = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setBigDecimal(1, pDetalleVenta.getPrecio());
            consulta.setBigDecimal(2, pDetalleVenta.getVenta_producto());
            consulta.setBigDecimal(3, pDetalleVenta.getCosto());
            consulta.setInt(4, pDetalleVenta.getCantidad());
            consulta.setInt(5, pDetalleVenta.getFk_venta());
            consulta.setInt(6, pDetalleVenta.getFk_reserva());
            consulta.setInt(7, pDetalleVenta.getId_venta_detalle());
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar detalle de venta: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un detalle de venta
    public boolean eliminarDetalleVenta(int idDetalleVenta) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        try {
            sql = "DELETE FROM Venta_detalle WHERE Id_venta_detalle = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, idDetalleVenta);
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar detalle de venta: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener un detalle de venta por ID
    public modeloDetalleVenta obtenerDetalleVenta(int idDetalleVenta) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        ResultSet resultado = null;
        modeloDetalleVenta detalleVenta = new modeloDetalleVenta();
        try {
            sql = "SELECT * FROM Venta_detalle WHERE Id_venta_detalle = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, idDetalleVenta);
            resultado = consulta.executeQuery();
            if (resultado.next()) {
                detalleVenta.setId_venta_detalle(resultado.getInt("Id_venta_detalle"));
                detalleVenta.setPrecio(resultado.getBigDecimal("Precio"));
                detalleVenta.setVenta_producto(resultado.getBigDecimal("Venta_producto"));
                detalleVenta.setCosto(resultado.getBigDecimal("Costo"));
                detalleVenta.setCantidad(resultado.getInt("Cantidad"));
                detalleVenta.setFk_venta(resultado.getInt("Fk_venta"));
                detalleVenta.setFk_reserva(resultado.getInt("Fk_reserva"));
            }
            return detalleVenta;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener detalle de venta: " + e.getMessage());
            return detalleVenta;
        }
    }

    // Método para cargar la tabla de detalles de venta
    public DefaultTableModel cargarTabla(String condicion) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"ID", "PRECIO", "VENTA PRODUCTO", "COSTO", "CANTIDAD", "ID VENTA", "ID RESERVA"});

        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "SELECT * FROM Venta_detalle " + condicion;
        ResultSet resultado = null;
        try {
            preparaConsulta = conex.prepareStatement(sql);
            resultado = preparaConsulta.executeQuery();

            while (resultado.next()) {
                modeloTabla.addRow(new Object[]{
                    resultado.getInt("Id_venta_detalle"),
                    resultado.getBigDecimal("Precio"),
                    resultado.getBigDecimal("Venta_producto"),
                    resultado.getBigDecimal("Costo"),
                    resultado.getInt("Cantidad"),
                    resultado.getInt("Fk_venta"),
                    resultado.getInt("Fk_reserva")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return modeloTabla;
    }
}
