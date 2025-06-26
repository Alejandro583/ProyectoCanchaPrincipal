package abm;

import config.conexion;
import config.sesion;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelo.modeloCompra;

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

}

