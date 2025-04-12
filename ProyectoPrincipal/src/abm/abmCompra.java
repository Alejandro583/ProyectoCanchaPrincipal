package abm;

import config.conexion;
import config.sesion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloCompra;

public class abmCompra extends conexion {
    sesion oSesion;

    public abmCompra(sesion pSesion) {
        oSesion = pSesion;
    }

    public DefaultTableModel cargarTabla(String condicion) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{
            "CÓDIGO", "FACTURA NRO", "TIPO COMPRA", "FECHA", "SUBTOTAL",
            "IVA 0%", "IVA 5%", "IVA 10%", "TOTAL NETO", "SALDO", "ESTADO"
        });

        try (Connection conex = getAbrirConexion();
             PreparedStatement consulta = conex.prepareStatement("SELECT * FROM compra " + condicion);
             ResultSet resultado = consulta.executeQuery()) {

            while (resultado.next()) {
                modeloTabla.addRow(new Object[]{
                    resultado.getInt("Id_compra"),
                    resultado.getString("Factura_nro"),
                    resultado.getString("Tipo_compra"),
                    resultado.getDate("Fecha"),
                    resultado.getDouble("Subtotal"),
                    resultado.getDouble("Iva0"),
                    resultado.getDouble("Iva5"),
                    resultado.getDouble("Iva10"),
                    resultado.getDouble("Total_neto"),
                    resultado.getDouble("Saldo"),
                    resultado.getInt("Estado")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
        }

        return modeloTabla;
    }

    public modeloCompra compraExiste(int idCompra) {
        modeloCompra compra = null;

        try (Connection conex = getAbrirConexion();
             PreparedStatement consulta = conex.prepareStatement("SELECT * FROM compra WHERE Id_compra = ?")) {

            consulta.setInt(1, idCompra);
            ResultSet resultado = consulta.executeQuery();

            if (resultado.next()) {
                compra = new modeloCompra();
                compra.setId_compra(resultado.getInt("Id_compra"));
                compra.setFactura_nro(resultado.getString("Factura_nro"));
                compra.setTipo_compra(resultado.getString("Tipo_compra"));
                compra.setFecha(resultado.getDate("Fecha"));
                compra.setSubtotal(resultado.getDouble("Subtotal"));
                compra.setIva0(resultado.getDouble("Iva0"));
                compra.setIva5(resultado.getDouble("Iva5"));
                compra.setIva10(resultado.getDouble("Iva10"));
                compra.setTotal_neto(resultado.getDouble("Total_neto"));
                compra.setSaldo(resultado.getDouble("Saldo"));
                compra.setEstado(resultado.getInt("Estado"));
                compra.setFk_usuario(resultado.getInt("Fk_usuario"));
                compra.setFk_proveedor(resultado.getInt("Fk_proveedor"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return compra;
    }

    public boolean agregarCompra(modeloCompra compra) {
        if (compraExiste(compra.getId_compra()) != null) {
            return false;
        }

        String sql = "INSERT INTO compra(Id_compra, Factura_nro, Tipo_compra, Fecha, Subtotal, Iva0, Iva5, Iva10, Total_neto, Saldo, Estado, Fk_usuario, Fk_proveedor) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conex = getAbrirConexion();
             PreparedStatement consulta = conex.prepareStatement(sql)) {

            consulta.setInt(1, compra.getId_compra());
            consulta.setString(2, compra.getFactura_nro());
            consulta.setString(3, compra.getTipo_compra());
            consulta.setDate(4, compra.getFecha());
            consulta.setDouble(5, compra.getSubtotal());
            consulta.setDouble(6, compra.getIva0());
            consulta.setDouble(7, compra.getIva5());
            consulta.setDouble(8, compra.getIva10());
            consulta.setDouble(9, compra.getTotal_neto());
            consulta.setDouble(10, compra.getSaldo());
            consulta.setInt(11, compra.getEstado());
            consulta.setInt(12, compra.getFk_usuario());
            consulta.setInt(13, compra.getFk_proveedor());
            consulta.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean modificarCompra(modeloCompra compra) {
        String sql = "UPDATE compra SET Id_compra=?, Factura_nro=?, Tipo_compra=?, Fecha=?, Subtotal=?, Iva0=?, Iva5=?, Iva10=?, Total_neto=?, Saldo=?, Estado=?, Fk_usuario=?, Fk_proveedor=? " +
                     "WHERE Id_compra = ?";

        try (Connection conex = getAbrirConexion();
             PreparedStatement consulta = conex.prepareStatement(sql)) {

            consulta.setInt(1, compra.getId_compra());
            consulta.setString(2, compra.getFactura_nro());
            consulta.setString(3, compra.getTipo_compra());
            consulta.setDate(4, compra.getFecha());
            consulta.setDouble(5, compra.getSubtotal());
            consulta.setDouble(6, compra.getIva0());
            consulta.setDouble(7, compra.getIva5());
            consulta.setDouble(8, compra.getIva10());
            consulta.setDouble(9, compra.getTotal_neto());
            consulta.setDouble(10, compra.getSaldo());
            consulta.setInt(11, compra.getEstado());
            consulta.setInt(12, compra.getFk_usuario());
            consulta.setInt(13, compra.getFk_proveedor());
            consulta.setInt(14, compra.getId_compra());
            consulta.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean eliminarCompra(int idCompra) {
        String sql = "UPDATE compra SET Estado = 0 WHERE Id_compra = ?";

        try (Connection conex = getAbrirConexion();
             PreparedStatement consulta = conex.prepareStatement(sql)) {

            consulta.setInt(1, idCompra);
            consulta.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}

