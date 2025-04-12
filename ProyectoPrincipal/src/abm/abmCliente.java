package abm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.modeloCliente;


public class abmCliente extends config.conexion
{  
    public abmCliente()
    {
        
    }
    
    public boolean agregarCliente(modeloCliente pCliente)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        ResultSet resultado = null;        
        try 
        {
            if (clienteExiste(pCliente.getCi()) != null)
            {
                return false;
            }
            else
            {
                sql = "INSERT INTO cliente(Ci,Telefono,Nombre,Estado)" + 
                        "VALUES (?,?,?,?)";
                consulta = conex.prepareStatement(sql);
                consulta.setString(1, pCliente.getCi().trim());
                consulta.setString(2, pCliente.getTelefono());
                consulta.setString(3, pCliente.getNombre());
                consulta.setInt(4, pCliente.getEstado());
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
    
    public modeloCliente clienteExiste(String cedula)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        ResultSet resultado = null;
        modeloCliente cliente =  new modeloCliente();
        cliente = null;
        try 
        {
            sql = "SELECT * FROM cliente WHERE Ci = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, cedula);
            resultado = consulta.executeQuery();
            if(resultado.next() == true)
            {
                cliente.setCi(resultado.getString("Ci"));
                cliente.setEstado(resultado.getInt("Estado"));
                cliente.setNombre(resultado.getString("Nombre"));
                cliente.setTelefono(resultado.getString("Telefono"));
                cliente.setId_cliente(resultado.getInt("Id_cliente"));
                return cliente;
            }
            else
            {
                return cliente;
            }
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return cliente;
        }
    }
    //MODIFICAR PARA QUE RECIBA UN OBJETO DE TIPO CLIENTE
    public boolean modificarCliente(modeloCliente pCliente)
    {
            Connection conex = getAbrirConexion();
            PreparedStatement consulta = null;
            String sql; 
            try 
            {
               sql = "UPDATE cliente SET Nombre = ?, Estado = ?,Telefono = ?,Ci = ? " + 
                    "WHERE Ci = ?";
               consulta = conex.prepareStatement(sql);
               consulta.setString(1, pCliente.getNombre());
               consulta.setInt(2, pCliente.getEstado());
               consulta.setString(3, pCliente.getTelefono());
               consulta.setString(4, pCliente.getCi());
               consulta.setString(5, pCliente.getCi());
               consulta.executeUpdate();
               return true; 
            } 
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
                return false;
            }
       }
            
    
    
    //MODIFICAR PARA QUE RECIBA UN OBJETO DE TIPO CLIENTE 
     public boolean eliminarCliente(modeloCliente pCliente)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql; 
        try 
        {
            sql = "UPDATE cliente SET Estado = ? WHERE Ci = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, pCliente.getEstado());
            consulta.setString(2, pCliente.getCi());
            consulta.executeUpdate();
            return true; 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
       
    }
    
}
