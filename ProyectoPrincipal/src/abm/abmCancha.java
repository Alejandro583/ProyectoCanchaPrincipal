package abm;

import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modeloCancha;
import modelo.modeloProducto;
import modelo.modeloReserva;


public class abmCancha extends config.conexion
{

    sesion oSesion;
    public abmCancha(sesion pSesion) 
    {
        oSesion = pSesion;
    }
    
    public DefaultTableModel cargarTabla(String condicion) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"CODIGO","DISPONIBLE","PRECIO","MANTENIMINTO"});

        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        try {
            sql = "SELECT * FROM cancha " + condicion;
            preparaConsulta = conex.prepareStatement(sql);
            resultado = preparaConsulta.executeQuery();

            while (resultado.next() == true) {
                modeloTabla.addRow(new Object[]{
                    resultado.getInt("Id_cancha"),
                    resultado.getString("Disponible"),
                    resultado.getFloat("Precio"),
                    resultado.getString("Mantenimiento"),});   
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
        }
        return modeloTabla;
    }
     

public DefaultComboBoxModel cargarComboBox(String condicion) {
    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel<>();

    PreparedStatement preparaConsulta = null;
    Connection conex = getAbrirConexion();
    String sql = "";
    ResultSet resultado = null;

    try {
        sql = "SELECT * FROM cancha " + condicion;
        preparaConsulta = conex.prepareStatement(sql);
        resultado = preparaConsulta.executeQuery();

        while (resultado.next()) {
            String nombreCancha = resultado.getString("Nombre"); // asumiendo que el campo se llama "Nombre"
            modeloCombo.addElement(nombreCancha);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
    }

    return modeloCombo;
}

    public boolean agregarCancha(modeloCancha pCancha)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        ResultSet resultado = null;        
        try 
        {
           
            sql = "INSERT INTO cancha(Disponible,Precio,Mantenimiento)" + 
                    "VALUES (?,?,?)";
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, pCancha.getDisponible());
            consulta.setFloat(2, pCancha.getPrecio());
            consulta.setString(3, pCancha.getMantenimiento());
            consulta.execute();
            return true;
                
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    
    public boolean modificarCancha(modeloCancha pCancha)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql; 
        try 
        {
            sql = "UPDATE cancha SET Disponible = ?, Precio = ?,Mantenimiento = ? " + 
                "WHERE Id_cancha = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, pCancha.getDisponible());
            consulta.setFloat(2, pCancha.getPrecio());
            consulta.setString(3, pCancha.getMantenimiento());
            consulta.setInt(4, pCancha.getId_cancha());
            consulta.executeUpdate();
            return true; 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
 
     public boolean eliminarCliente(modeloCancha pCancha)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql; 
        try 
        {
            sql = "UPDATE cancha SET Disponible = ? WHERE Id_cancha = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, pCancha.getDisponible());
            consulta.setInt(2, pCancha.getId_cancha());
            consulta.executeUpdate();
            return true; 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }   
    }
     
    public modeloCancha canchaExiste(modeloCancha pCancha)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql;
        ResultSet resultado = null;
        modeloCancha cancha =  new modeloCancha();
        cancha = null;
       try {
            sql = "SELECT * FROM cancha WHERE Id_cancha = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, pCancha.getId_cancha());
            resultado = consulta.executeQuery();
            cancha.setDisponible(resultado.getString("Disponible"));
            cancha.setId_cancha(resultado.getInt("Id_cancha"));
            cancha.setMantenimiento(resultado.getString("Mantenimiento"));
            cancha.setPrecio(resultado.getFloat("Precio"));
            return cancha;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
        }
        return cancha;
    }
    
    public int obtenerIdCanchaPorNombre(String nombreCancha) {
    int idCancha = -1; // Valor por defecto si no encuentra

    String sql = "SELECT Id_cancha FROM cancha WHERE Nombre = ?";

    try (Connection conex = getAbrirConexion(); 
         PreparedStatement ps = conex.prepareStatement(sql)) {

        ps.setString(1, nombreCancha);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            idCancha = rs.getInt("Id_cancha");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar el ID de la cancha: " + e.getMessage());
    }

    return idCancha;
}

       
} 

