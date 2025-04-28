
package abm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloProveedor;
import config.conexion;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloProveedor;

public class abmProveedor extends conexion {

    public abmProveedor() {
    }

    public boolean agregarProveedor(modeloProveedor proveedor) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "INSERT INTO Proveedor(Ruc, Nombre, Telefono, Direccion, Estado) VALUES (?, ?, ?, ?, ?)";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, proveedor.getRuc());
            consulta.setString(2, proveedor.getNombre());
            consulta.setString(3, proveedor.getTelefono());
            consulta.setString(4, proveedor.getDireccion());
            consulta.setInt(5, proveedor.getEstado());
            consulta.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar proveedor: " + e.getMessage());
            return false;
        }
    }
    
    public modeloProveedor proveedorExiste(String criterio, String valor) {
    Connection conex = getAbrirConexion();
    PreparedStatement consulta = null;
    ResultSet resultado = null;
    modeloProveedor proveedor = null;
    
    String sql = "SELECT * FROM Proveedor WHERE " + criterio + " = ? AND Estado = 1";

    try {
        consulta = conex.prepareStatement(sql);
        consulta.setString(1, valor);
        resultado = consulta.executeQuery();
        
        if (resultado.next()) {
            proveedor = new modeloProveedor();
            proveedor.setIdProveedor(resultado.getInt("Id_proveedor"));
            proveedor.setRuc(resultado.getString("Ruc"));
            proveedor.setNombre(resultado.getString("Nombre"));
            proveedor.setTelefono(resultado.getString("Telefono"));
            proveedor.setDireccion(resultado.getString("Direccion"));
            proveedor.setEstado(resultado.getInt("Estado"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return proveedor;
}


    public boolean modificarProveedor(modeloProveedor proveedor) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "UPDATE Proveedor SET Ruc = ?, Nombre = ?, Telefono = ?, Direccion = ?, Estado = ? WHERE Id_proveedor = ?";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, proveedor.getRuc());
            consulta.setString(2, proveedor.getNombre());
            consulta.setString(3, proveedor.getTelefono());
            consulta.setString(4, proveedor.getDireccion());
            consulta.setInt(5, proveedor.getEstado());
            consulta.setInt(6, proveedor.getIdProveedor());
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar proveedor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProveedor(modeloProveedor proveedor) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "UPDATE Proveedor SET Estado = 0 WHERE Id_proveedor = ?";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, proveedor.getIdProveedor());
            consulta.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }

    public DefaultTableModel cargarTabla(String condicion) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"ID", "RUC", "NOMBRE", "TELÉFONO", "DIRECCIÓN", "ESTADO"});

        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        ResultSet resultado = null;
        String sql = "SELECT * FROM Proveedor WHERE Estado = 1 " + condicion;

        try {
            preparaConsulta = conex.prepareStatement(sql);
            resultado = preparaConsulta.executeQuery();

            while (resultado.next()) {
                modeloTabla.addRow(new Object[]{
                    resultado.getInt("Id_proveedor"),
                    resultado.getString("Ruc"),
                    resultado.getString("Nombre"),
                    resultado.getString("Telefono"),
                    resultado.getString("Direccion"),
                    resultado.getInt("Estado")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error al cargar proveedores", JOptionPane.ERROR_MESSAGE);
        }

        return modeloTabla;
    }
    public DefaultComboBoxModel cargarProveedor() {
    DefaultComboBoxModel modelo = new DefaultComboBoxModel<>();

    String sql = "SELECT Id_proveedor, Nombre FROM proveedor WHERE Estado = 1"; // Solo proveedores activos

    try (Connection conex = getAbrirConexion();
         PreparedStatement stmt = conex.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int idProveedor = rs.getInt("Id_proveedor");
            String nombreProveedor = rs.getString("Nombre");

            // Formato: id - nombre
            String valor = idProveedor + " - " + nombreProveedor;

            modelo.addElement(valor);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    
    return modelo;
}
public modeloProveedor proveedorExiste(modeloProveedor oProveedor) {
    Connection conex = getAbrirConexion();
    PreparedStatement consulta = null;
    ResultSet resultado = null;
    modeloProveedor proveedor = oProveedor;
    
    String sql = "SELECT * FROM Proveedor WHERE Id_proveedor = ? AND Estado = 1";

    try {
        consulta = conex.prepareStatement(sql);
        consulta.setInt(1, oProveedor.getIdProveedor());
        resultado = consulta.executeQuery();
        
        if (resultado.next()) {
            proveedor = new modeloProveedor();
            proveedor.setIdProveedor(resultado.getInt("Id_proveedor"));
            proveedor.setRuc(resultado.getString("Ruc"));
            proveedor.setNombre(resultado.getString("Nombre"));
            proveedor.setTelefono(resultado.getString("Telefono"));
            proveedor.setDireccion(resultado.getString("Direccion"));
            proveedor.setEstado(resultado.getInt("Estado"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    return proveedor;
}

    
}

