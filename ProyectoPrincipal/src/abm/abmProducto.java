package abm;

import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloProducto;


public class abmProducto extends config.conexion
{
    sesion oSesion;

    public abmProducto(sesion pSesion) 
    {
        oSesion = pSesion;
    }
    
    public DefaultTableModel cargarTabla(String condicion) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"CODIGO","PRODUCTO","PRECIO","STOCK"});

        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        try {
            sql = "SELECT * FROM producto WHERE Stock > 0 AND Estado = 1 " + condicion;
            preparaConsulta = conex.prepareStatement(sql);
            resultado = preparaConsulta.executeQuery();

            while (resultado.next() == true) {
                modeloTabla.addRow(new Object[]{
                    resultado.getInt("Id_producto"),
                    resultado.getString("Nombre_producto"),
                    resultado.getFloat("Precio"),
                    resultado.getInt("Stock"),});   
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return modeloTabla;
    }

     public modeloProducto productoExiste(modeloProducto pProducto)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        ResultSet resultado = null;
        try 
        {
            sql = "SELECT * FROM producto WHERE Id_producto = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, pProducto.getId_producto());
            resultado = consulta.executeQuery();
            if(resultado.next() == true)
            {
                pProducto.setCosto(resultado.getFloat("Costo"));
                pProducto.setEstado(resultado.getInt("Estado"));
                pProducto.setFk_proveedor(resultado.getInt("Fk_proveedor"));
                pProducto.setId_producto(resultado.getInt("Id_producto"));
                pProducto.setNombre_producto(resultado.getString("Nombre_producto"));
                pProducto.setPrecio(resultado.getFloat("Precio"));
                pProducto.setStock(resultado.getInt("Stock"));
                return pProducto;
            }
            else
            {
                return pProducto;
            }
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return pProducto;
        }
    }
     
     
    public boolean cargarProducto(modeloProducto pProducto)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        ResultSet resultado = null;        
        try 
        {
            if (productoExiste(pProducto) != null)
            {
                return false;
            }
            else
            {
                sql = "INSERT INTO producto(Id_producto,Precio,Nombre_producto,Stock,Costo,Fk_proveedor,Estado)" + 
                        "VALUES (?,?,?,?,?,?,?)";
                consulta = conex.prepareStatement(sql);
                consulta.setInt(1,pProducto.getId_producto());
                consulta.setFloat(2, pProducto.getPrecio());
                consulta.setString(3, pProducto.getNombre_producto());
                consulta.setInt(4,pProducto.getStock());
                consulta.setFloat(5, pProducto.getCosto());
                consulta.setInt(6, pProducto.getFk_proveedor());
                consulta.setInt(7, pProducto.getEstado());
                consulta.execute();
                return true;
            }    
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    public boolean modificarProducto(modeloProducto pProducto)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql; 
        try 
        {
            sql = "UPDATE producto SET Precio = ?,Nombre_producto = ? ,Stock = ?,Costo = ?,Fk_proveedor = ?,Estado = ? " + 
                "WHERE Id_producto = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setFloat(1, pProducto.getPrecio());
            consulta.setString(2, pProducto.getNombre_producto());
            consulta.setInt(3, pProducto.getStock());
            consulta.setFloat(4, pProducto.getCosto());
            consulta.setInt(5, pProducto.getFk_proveedor());
            consulta.setInt(6, pProducto.getEstado());
            consulta.setInt(7, pProducto.getId_producto());
            consulta.executeUpdate();
            return true; 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarProducto(modeloProducto pProducto)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql; 
        try 
        {
            sql = "UPDATE producto SET Estado = 0 WHERE Id_producto = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, pProducto.getId_producto());
            consulta.executeUpdate();
            return true; 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }    
    }
    
    public boolean aumentarStock(int cantidad,int id_producto)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql; 
         try 
        {
            sql = "UPDATE producto SET Stock = Stock + ? WHERE Id_producto = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, cantidad);
            consulta.setInt(2, id_producto);
            consulta.executeUpdate();
            return true; 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } 
    }
    public boolean disminuirStock(int cantidad,int id_producto)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql; 
         try 
        {
            sql = "UPDATE producto SET Stock = Stock - ? WHERE Id_producto = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, cantidad);
            consulta.setInt(2, id_producto);
            consulta.executeUpdate();
            return true; 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } 
    }
    
    public int stockDisponible(int id_producto)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql; 
        ResultSet resultado = null;
        int cantidadStock;
         try 
        {
            sql = "SELECT Stock FROM producto WHERE Id_producto = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, id_producto);
            resultado = consulta.executeQuery();
            cantidadStock = resultado.getInt("Stock");
            return cantidadStock;
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        } 
    }
}
