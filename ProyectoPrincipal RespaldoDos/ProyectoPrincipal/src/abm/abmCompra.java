package abm;

import config.conexion;
import config.sesion;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.modeloCaja;
import modelo.modeloCompra;
import modelo.modeloCompraDetalle;

public class abmCompra extends conexion {
    sesion oSesion;

    public abmCompra(sesion pSesion) {
        oSesion = pSesion;
    }

       public modeloCompra cargarCompra(int idCompra) {
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
                compra.setFecha(resultado.getString("Fecha"));
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
        if (cargarCompra(compra.getId_compra()) != null) {
            return false;
        }

        String sql = "INSERT INTO compra(Id_compra, Factura_nro, Tipo_compra, Fecha, Subtotal, Iva0, Iva5, Iva10, Total_neto, Saldo, Estado, Fk_usuario, Fk_proveedor) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conex = getAbrirConexion();
             PreparedStatement consulta = conex.prepareStatement(sql)) {

            consulta.setInt(1, compra.getId_compra());
            consulta.setString(2, compra.getFactura_nro());
            consulta.setString(3, compra.getTipo_compra());
            consulta.setString(4, compra.getFecha());
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
            consulta.setString(4, compra.getFecha());
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
    
    
   public DefaultComboBoxModel<String> cargarFechas() {
    DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate hoy = LocalDate.now();

    for (int i = 0; i <= 7; i++) {
        LocalDate fecha = hoy.plusDays(i);
        modelo.addElement(fecha.format(formato)); // compatible con MySQL
    }

    return modelo;
}

    
    public int obtenerUltimoIdCompra() {
    int ultimoId = -1; // Usamos -1 como valor por defecto si no se encuentra nada
    String sql = "SELECT MAX(Id_compra) AS ultimo_id FROM compra";

    try (Connection conex = getAbrirConexion();
         PreparedStatement stmt = conex.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            ultimoId = rs.getInt("ultimo_id");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener el último ID de compra: " + e.getMessage(),
                oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
    }

    return ultimoId;
}
    
    public void guardarCompraYDetalles(JTable modeloTabla, modeloCompra compra) {
    Connection conn = null;
    PreparedStatement psCompra = null;
    PreparedStatement psDetalle = null;
    PreparedStatement ps = null;
    ResultSet generatedKeys = null;

    try {
        conn = getAbrirConexion();
        conn.setAutoCommit(false);

        // Insertar en la tabla compra
        String sqlCompra = "INSERT INTO compra (Factura_nro, Tipo_compra, Fecha, Subtotal, Iva0, Iva5, Iva10, Total_neto, Saldo, Estado, Fk_usuario, Fk_proveedor) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        psCompra = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS);
        psCompra.setString(1, compra.getFactura_nro());
        psCompra.setString(2, compra.getTipo_compra());
        psCompra.setDate(3, Date.valueOf(compra.getFecha()));
        psCompra.setDouble(4, compra.getSubtotal());
        psCompra.setDouble(5, compra.getIva0());
        psCompra.setDouble(6, compra.getIva5());
        psCompra.setDouble(7, compra.getIva10());
        psCompra.setDouble(8, compra.getTotal_neto());
        psCompra.setDouble(9, compra.getSaldo());
        psCompra.setInt(10, compra.getEstado());
        psCompra.setInt(11, compra.getFk_usuario());
        psCompra.setInt(12, compra.getFk_proveedor());
        abmCaja oabCaja = new abmCaja(oSesion);
        modeloCaja omodeloCaja = new modeloCaja();
        omodeloCaja = oabCaja.CargarCaja(oSesion.getIdUsuario());
        oabCaja.disminuirEfectivo(compra.getTotal_neto(), omodeloCaja.getId_caja());
        int rows = psCompra.executeUpdate();
        if (rows == 0) {
            throw new SQLException("Error al insertar la compra, no se generó ID.");
        }

        // Obtener ID generado
        generatedKeys = psCompra.getGeneratedKeys();
        int idCompra = -1;
        if (generatedKeys.next()) {
            idCompra = generatedKeys.getInt(1);
        }

        // Preparar inserción de detalles
        String sqlDetalle = "INSERT INTO compra_detalle(Costo, Cantidad, Compra_producto, Costo_medio, Iva, Estado, Fk_compra, Fk_producto) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        psDetalle = conn.prepareStatement(sqlDetalle);

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            int idProducto = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString()); // Fk_producto
            String compraProducto = modeloTabla.getValueAt(i, 1).toString();            // Compra_producto
            float costo = Float.parseFloat(modeloTabla.getValueAt(i, 2).toString());    // Costo
            //float costoMedio = Float.parseFloat(modeloTabla.getValueAt(i, 3).toString());// Costo_medio
            //int iva = Integer.parseInt(modeloTabla.getValueAt(i, 4).toString());        // Iva
            int cantidad = Integer.parseInt(modeloTabla.getValueAt(i, 5).toString());   // Cantidad
            int estado = 1; // Estado activo por defecto

            psDetalle.setFloat(1, costo);
            psDetalle.setInt(2, cantidad);
            psDetalle.setString(3, compraProducto);
            psDetalle.setFloat(4, 0);
            psDetalle.setInt(5, 10);
            psDetalle.setInt(6, estado);
            psDetalle.setInt(7, idCompra);
            psDetalle.setInt(8, idProducto);

            psDetalle.executeUpdate();
        }
        
        
        
        
         Map<Integer, Integer> productos = new HashMap<>();
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            int idProducto = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString());  // ID producto
            int cantidad = Integer.parseInt(modeloTabla.getValueAt(i, 5).toString());   // Cantidad

            productos.put(idProducto, productos.getOrDefault(idProducto, 0) + cantidad);
        }

        // Actualizar stock sumando
        String sqlStock = "UPDATE Producto SET stock = stock + ? WHERE id_producto = ?";
        for (Map.Entry<Integer, Integer> entry : productos.entrySet()) {
            ps = conn.prepareStatement(sqlStock);
            ps.setInt(1, entry.getValue());
            ps.setInt(2, entry.getKey());
            ps.executeUpdate();
        }
        conn.commit();
        JOptionPane.showMessageDialog(null, "Compra y detalles guardados correctamente");

    } catch (Exception e) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al guardar la compra: " + e.getMessage());
    } finally {
        try {
            if (generatedKeys != null) generatedKeys.close();
            if (psCompra != null) psCompra.close();
            if (psDetalle != null) psDetalle.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


}

