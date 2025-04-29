package abm;

import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.horariosCancha;
import modelo.modeloProducto;


public class abmProducto extends config.conexion
{
    sesion oSesion;

    modeloProducto oModeloProducto = new modeloProducto();
    public abmProducto(sesion pSesion) 
    {
        oSesion = pSesion;
    }

    public abmProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
   public DefaultComboBoxModel cargarProducto() {
    DefaultComboBoxModel modelo = new DefaultComboBoxModel<>();

    String sql = "SELECT Id_producto, Nombre_producto FROM producto WHERE Estado = 1"; // opcionalmente filtramos por Estado=1 si quieres solo activos

    try (Connection conex = getAbrirConexion();
         PreparedStatement stmt = conex.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int idProducto = rs.getInt("Id_producto");
            String nombreProducto = rs.getString("Nombre_producto");

            // Puedes cargar el id y nombre juntos si quieres mostrar más información
            String valor = idProducto + " - " + nombreProducto;

            modelo.addElement(valor);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
    }
    
    return modelo;
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
            if (productoExiste(pProducto) == null)
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
    public List<modeloProducto> listarProductosPorNombre(String criterio) throws Exception {
        List<modeloProducto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, precio_venta FROM producto "
                   + "WHERE estado = 1 AND nombre LIKE ?";
        try (Connection cn = this.getAbrirConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, "%" + criterio + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modeloProducto p = new modeloProducto();
                    p.setIdProducto(rs.getInt("id_producto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecioVenta(rs.getDouble("precio_venta"));
                    lista.add(p);
                }
            }
        }
        return lista;
    }
    public modeloProducto buscarProductoPorId(int idProducto) throws Exception {
    modeloProducto p = null;
    String sql = "SELECT * FROM producto WHERE Id_producto = ?";
    try (Connection cn = getAbrirConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idProducto);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                p = new modeloProducto();
                p.setId_producto(rs.getInt("Id_producto"));
                p.setNombre_producto(rs.getString("Nombre_producto"));
                p.setPrecio(rs.getFloat("Precio"));
            }
        }
    }
    return p;
}

    
    
}
