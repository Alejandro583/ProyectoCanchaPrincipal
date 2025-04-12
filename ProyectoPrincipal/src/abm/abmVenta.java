
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloVenta;

public class abmVenta extends config.conexion {

    public boolean agregarVenta(modeloVenta pVenta) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;

        try {
            sql = "INSERT INTO Venta(Saldo, Fecha, Total_costo, Subtotal, Iva0, Iva5, Iva10, Estado, Factura_nro, Tipo_venta, Total_neto, Ttl_pago, Ttl_descuento, Ttl_saldo, Fk_cliente, Fk_caja, Fk_usuario) " +
                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            consulta = conex.prepareStatement(sql);
            consulta.setDouble(1, pVenta.getSaldo());
            consulta.setDate(2, pVenta.getFecha());
            consulta.setDouble(3, pVenta.getTotalCosto());
            consulta.setDouble(4, pVenta.getSubtotal());
            consulta.setDouble(5, pVenta.getIva0());
            consulta.setDouble(6, pVenta.getIva5());
            consulta.setDouble(7, pVenta.getIva10());
            consulta.setDouble(8, pVenta.getEstado());
            consulta.setDouble(9, pVenta.getFacturaNro());
            consulta.setString(10, pVenta.getTipoVenta());
            consulta.setDouble(11, pVenta.getTotalNeto());
            consulta.setDouble(12, pVenta.getTtlPago());
            consulta.setDouble(13, pVenta.getTtlDescuento());
            consulta.setDouble(14, pVenta.getTtlSaldo());
            consulta.setInt(15, pVenta.getFkCliente());
            consulta.setInt(16, pVenta.getFkCaja());
            consulta.setInt(17, pVenta.getFkUsuario());
            consulta.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public modeloVenta buscarVentaPorId(int idVenta) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        String sql;
        modeloVenta venta = null;

        try {
            sql = "SELECT * FROM Venta WHERE Id_venta = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, idVenta);
            resultado = consulta.executeQuery();

            if (resultado.next()) {
                venta = new modeloVenta();
                venta.setIdVenta(resultado.getInt("Id_venta"));
                venta.setSaldo(resultado.getDouble("Saldo"));
                venta.setFecha(resultado.getDate("Fecha"));
                venta.setTotalCosto(resultado.getDouble("Total_costo"));
                venta.setSubtotal(resultado.getDouble("Subtotal"));
                venta.setIva0(resultado.getDouble("Iva0"));
                venta.setIva5(resultado.getDouble("Iva5"));
                venta.setIva10(resultado.getDouble("Iva10"));
                venta.setEstado(resultado.getDouble("Estado"));
                venta.setFacturaNro(resultado.getDouble("Factura_nro"));
                venta.setTipoVenta(resultado.getString("Tipo_venta"));
                venta.setTotalNeto(resultado.getDouble("Total_neto"));
                venta.setTtlPago(resultado.getDouble("Ttl_pago"));
                venta.setTtlDescuento(resultado.getDouble("Ttl_descuento"));
                venta.setTtlSaldo(resultado.getDouble("Ttl_saldo"));
                venta.setFkCliente(resultado.getInt("Fk_cliente"));
                venta.setFkCaja(resultado.getInt("Fk_caja"));
                venta.setFkUsuario(resultado.getInt("Fk_usuario"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return venta;
    }

    public boolean modificarVenta(modeloVenta pVenta) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;

        try {
            sql = "UPDATE Venta SET Saldo = ?, Fecha = ?, Total_costo = ?, Subtotal = ?, Iva0 = ?, Iva5 = ?, Iva10 = ?, Estado = ?, Factura_nro = ?, Tipo_venta = ?, Total_neto = ?, Ttl_pago = ?, Ttl_descuento = ?, Ttl_saldo = ?, Fk_cliente = ?, Fk_caja = ?, Fk_usuario = ? " +
                  "WHERE Id_venta = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setDouble(1, pVenta.getSaldo());
            consulta.setDate(2, pVenta.getFecha());
            consulta.setDouble(3, pVenta.getTotalCosto());
            consulta.setDouble(4, pVenta.getSubtotal());
            consulta.setDouble(5, pVenta.getIva0());
            consulta.setDouble(6, pVenta.getIva5());
            consulta.setDouble(7, pVenta.getIva10());
            consulta.setDouble(8, pVenta.getEstado());
            consulta.setDouble(9, pVenta.getFacturaNro());
            consulta.setString(10, pVenta.getTipoVenta());
            consulta.setDouble(11, pVenta.getTotalNeto());
            consulta.setDouble(12, pVenta.getTtlPago());
            consulta.setDouble(13, pVenta.getTtlDescuento());
            consulta.setDouble(14, pVenta.getTtlSaldo());
            consulta.setInt(15, pVenta.getFkCliente());
            consulta.setInt(16, pVenta.getFkCaja());
            consulta.setInt(17, pVenta.getFkUsuario());
            consulta.setInt(18, pVenta.getIdVenta());
            consulta.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean eliminarVenta(int idVenta) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;

        try {
            sql = "UPDATE Venta SET Estado = 0 WHERE Id_venta = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, idVenta);
            consulta.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}
