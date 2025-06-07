package abm;

import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloCliente;


public class abmCliente extends config.conexion
{  
    sesion oSesion;
    public abmCliente(sesion pSesion)
    {
        oSesion = pSesion;
    }
    
    public DefaultTableModel cargarTabla(String condicion) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"CODIGO","CEDULA","NOMBRE","TELEFONO"});

        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        try {
            sql = "SELECT * FROM cliente WHERE Estado = 1 " + condicion ;
            preparaConsulta = conex.prepareStatement(sql);
            resultado = preparaConsulta.executeQuery();

            while (resultado.next() == true) {
                modeloTabla.addRow(new Object[]{
                    resultado.getInt("Id_cliente"),
                    resultado.getString("Ci"),
                    resultado.getString("Nombre"),
                    resultado.getString("Telefono"),});   
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
        }
        return modeloTabla;
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
        try 
        {
            sql = "SELECT * FROM cliente WHERE Ci = ? AND Estado = 1";
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
                return null;
            }
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public modeloCliente clienteExiste(int cedula)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        ResultSet resultado = null;
        modeloCliente cliente =  new modeloCliente();
        try 
        {
            sql = "SELECT * FROM cliente WHERE Id_cliente = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, cedula);
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
                return null;
            }
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
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
                "WHERE Id_cliente = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, pCliente.getNombre());
            consulta.setInt(2, pCliente.getEstado());
            consulta.setString(3, pCliente.getTelefono());
            consulta.setString(4, pCliente.getCi());
            consulta.setInt(5, pCliente.getId_cliente());
            consulta.executeUpdate();
            return true; 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
            
    
    public boolean eliminarCliente(modeloCliente pCliente)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql; 
        try 
        {
            sql = "UPDATE cliente SET Estado = ? WHERE Id_cliente = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, pCliente.getEstado());
            consulta.setInt(2, pCliente.getId_cliente());
            consulta.executeUpdate();
            return true; 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }   
    } 
    
    public int obtenerIdClientePorCedula(String cedula) {
    Connection conex = getAbrirConexion();
    PreparedStatement consulta = null;
    String sql;
    ResultSet resultado = null;
    int idCliente = -1;  // Retorna -1 si no se encuentra el cliente
    
    try {
        // Consulta SQL para obtener el ID del cliente por su cédula
        sql = "SELECT Id_cliente FROM cliente WHERE Ci = ?";
        consulta = conex.prepareStatement(sql);
        consulta.setString(1, cedula);
        resultado = consulta.executeQuery();
        
        if (resultado.next()) {
            // Si el cliente existe, obtener el ID
            idCliente = resultado.getInt("Id_cliente");
        }
        
    } catch (SQLException e) {
        // Manejo de error en caso de excepción
        JOptionPane.showMessageDialog(null, e.getMessage());
    } finally {
        // Cerrar recursos
        try {
            if (resultado != null) resultado.close();
            if (consulta != null) consulta.close();
            if (conex != null) conex.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    return idCliente;
}
    
    public DefaultComboBoxModel<String> obtenerClientesActivos(String condicion) {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        Connection conn = getAbrirConexion();
        String sql = "SELECT Nombre,Ci FROM cliente WHERE Estado = 1 " + condicion;

        try{
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                String clienteDes = "Nombre : "+rs.getString("Nombre") + " | C.I : " + rs.getString("Ci");
                
                
                modelo.addElement(clienteDes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelo;
    }

}
