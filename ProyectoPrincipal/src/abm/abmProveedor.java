/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloProveedor;

public class abmProveedor extends config.conexion {

    public boolean agregarProveedor(modeloProveedor p) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "INSERT INTO Proveedor (Ruc, Nombre, Telefono, Direccion, Estado) VALUES (?, ?, ?, ?, ?)";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, p.getRuc());
            consulta.setString(2, p.getNombre());
            consulta.setString(3, p.getTelefono());
            consulta.setString(4, p.getDireccion());
            consulta.setInt(5, p.getEstado());
            consulta.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar proveedor: " + e.getMessage());
            return false;
        }
    }

    public modeloProveedor buscarProveedorPorId(int idProveedor) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        modeloProveedor p = null;
        String sql = "SELECT * FROM Proveedor WHERE Id_proveedor = ?";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, idProveedor);
            resultado = consulta.executeQuery();

            if (resultado.next()) {
                p = new modeloProveedor();
                p.setIdProveedor(resultado.getInt("Id_proveedor"));
                p.setRuc(resultado.getString("Ruc"));
                p.setNombre(resultado.getString("Nombre"));
                p.setTelefono(resultado.getString("Telefono"));
                p.setDireccion(resultado.getString("Direccion"));
                p.setEstado(resultado.getInt("Estado"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar proveedor: " + e.getMessage());
        }

        return p;
    }

    public boolean modificarProveedor(modeloProveedor p) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "UPDATE Proveedor SET Ruc = ?, Nombre = ?, Telefono = ?, Direccion = ?, Estado = ? WHERE Id_proveedor = ?";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, p.getRuc());
            consulta.setString(2, p.getNombre());
            consulta.setString(3, p.getTelefono());
            consulta.setString(4, p.getDireccion());
            consulta.setInt(5, p.getEstado());
            consulta.setInt(6, p.getIdProveedor());
            consulta.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar proveedor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProveedor(int idProveedor) {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql = "UPDATE Proveedor SET Estado = 0 WHERE Id_proveedor = ?";

        try {
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, idProveedor);
            consulta.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }
}

