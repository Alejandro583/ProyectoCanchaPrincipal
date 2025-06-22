package abm;

import config.sesion;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloProducto;
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

   public List<modeloVenta> buscarVentasPorCliente(int id_cliente) {
    Connection conex = getAbrirConexion();
    PreparedStatement consulta = null;
    ResultSet resultado = null;
    String sql;
    List<modeloVenta> listaVentas = new ArrayList<>();

    try {
        sql = "SELECT * FROM Venta WHERE Fk_cliente = ? AND Estado = 1";
        consulta = conex.prepareStatement(sql);
        consulta.setInt(1, id_cliente);
        resultado = consulta.executeQuery();

        while (resultado.next()) {
            modeloVenta venta = new modeloVenta();
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

            listaVentas.add(venta); // agrega a la lista
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

    return listaVentas;
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
            consulta.setInt(18, idVenta());
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
    
    
    public int obtenerUltimoIdVenta() {
    int idVenta = -1;  // Valor por defecto si no hay registros
    String sql = "SELECT Id_venta FROM venta ORDER BY Id_venta DESC LIMIT 1";
    Connection conn = getAbrirConexion();
    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            idVenta = rs.getInt("Id_venta");
        }

    } catch (SQLException e) {
        e.printStackTrace();  // Puedes reemplazar por manejo con logger si usas uno
    }

    return idVenta;
}
public int idVenta()
    {
        int idVenta = 0;
        String sql = "SELECT Id_venta FROM venta WHERE Estado = 0";
        Connection conn = getAbrirConexion();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
            idVenta = rs.getInt("Id_venta");
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Puedes reemplazar por manejo con logger si usas uno
        }

        return idVenta;
    }
    
public boolean guardarVenta(modeloVenta pVenta, DefaultTableModel pVentaItems, sesion psesion) {
    PreparedStatement ps = null;
    Connection con = getAbrirConexion();
    String sql;

    try {
        con.setAutoCommit(false);

        // Actualizar la venta principal
        sql = "UPDATE Venta SET Saldo = ?, Fecha = ?, Total_costo = ?, Subtotal = ?, Iva0 = ?, Iva5 = ?, Iva10 = ?, " +
              "Estado = ?, Factura_nro = ?, Tipo_venta = ?, Total_neto = ?, Ttl_pago = ?, Ttl_descuento = ?, " +
              "Ttl_saldo = ?, Fk_cliente = ?, Fk_caja = ?, Fk_usuario = ? WHERE Id_venta = ?";
        ps = con.prepareStatement(sql);

// Index del parámetro → Valor
ps.setDouble(1, pVenta.getSaldo());
ps.setDate(2, pVenta.getFecha());
ps.setDouble(3, pVenta.getTotalCosto());
ps.setDouble(4, pVenta.getSubtotal());
ps.setDouble(5, pVenta.getIva0());
ps.setDouble(6, pVenta.getIva5());
ps.setDouble(7, pVenta.getIva10());
ps.setInt(8, 1);
ps.setString(9, String.valueOf(pVenta.getFacturaNro()));
ps.setString(10, pVenta.getTipoVenta());
ps.setDouble(11, pVenta.getTotalNeto());

// Lógica para Contado o Crédito
if (pVenta.getTipoVenta().equalsIgnoreCase("Contado")) {
    ps.setDouble(12, pVenta.getTotalNeto()); // Ttl_pago
    ps.setDouble(13, pVenta.getTtlDescuento());
    ps.setDouble(14, 0); // Ttl_saldo
} else {
    ps.setDouble(12, 0); // Ttl_pago
    ps.setDouble(13, pVenta.getTtlDescuento());
    ps.setDouble(14, pVenta.getTotalNeto()); // Ttl_saldo
}

ps.setInt(15, pVenta.getFkCliente());
ps.setInt(16, pVenta.getFkCaja());
ps.setInt(17, pVenta.getFkUsuario());

ps.setInt(18, obtenerUltimoIdVenta()+1); // Este es el valor del WHERE

ps.executeUpdate();

        // Insertar los items de la venta
        String sqlItems = "INSERT INTO Venta_detalle (Precio, Venta_producto, Costo, Cantidad, Fk_venta) VALUES (?, ?, ?, ?, ?)";
        for (int i = 0; i < pVentaItems.getRowCount(); i++) {
            abmProducto oAbmP = new abmProducto(psesion);
            modeloProducto oModeloP = new modeloProducto();
            oModeloP.setIdProducto(Integer.parseInt(pVentaItems.getValueAt(i, 0).toString()));
            oModeloP = oAbmP.productoExiste(oModeloP); // Trae precio y costo

            ps = con.prepareStatement(sqlItems);
            ps.setFloat(1, Float.parseFloat(pVentaItems.getValueAt(i, 4).toString())); // Precio unitario
            ps.setFloat(2, oModeloP.getPrecio());  // Precio de venta
            ps.setFloat(3, oModeloP.getCosto());   // Costo del producto
            ps.setInt(4, Integer.parseInt(pVentaItems.getValueAt(i, 2).toString())); // Cantidad
            ps.setInt(5, pVenta.getIdVenta());     // FK Venta actual
            ps.executeUpdate();
        }

        // Acumular cantidades por producto (evita update repetidos)
        Map<Integer, Integer> productos = new HashMap<>();
        for (int i = 0; i < pVentaItems.getRowCount(); i++) {
            int idProducto = Integer.parseInt(pVentaItems.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(pVentaItems.getValueAt(i, 2).toString());
            productos.put(idProducto, productos.getOrDefault(idProducto, 0) + cantidad);
        }

        // Descontar stock de cada producto
        String sqlStock = "UPDATE Producto SET stock = stock - ? WHERE id_producto = ?";
        for (Map.Entry<Integer, Integer> entry : productos.entrySet()) {
            ps = con.prepareStatement(sqlStock);
            ps.setInt(1, entry.getValue());
            ps.setInt(2, entry.getKey());
            ps.executeUpdate();
        }

        con.commit();
        return true;

    } catch (SQLException e) {
        try {
            if (con != null) con.rollback();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al hacer rollback: " + ex.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Error al guardar la venta: " + e.getMessage());
        return false;
    } finally {
        this.setCerrarConexion(con);
    }
}

}
