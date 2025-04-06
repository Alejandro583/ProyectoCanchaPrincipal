package abm;

import config.sesion;
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
            if (clienteExiste(pCliente.getCi()) == true)
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
    
    public boolean clienteExiste(String cedula)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        ResultSet resultado = null;
        
        try 
        {
            sql = "SELECT Nombre FROM cliente WHERE Ci = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, cedula);
            resultado = consulta.executeQuery();
            if(resultado.next() == true)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}
