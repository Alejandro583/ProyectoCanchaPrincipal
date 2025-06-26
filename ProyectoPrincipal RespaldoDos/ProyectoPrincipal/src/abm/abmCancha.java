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
    public abmCancha() 
    {
       
    }
    
    public DefaultTableModel cargarTabla(String condicion) {
    DefaultTableModel modeloTabla = new DefaultTableModel();
    modeloTabla.setColumnIdentifiers(new Object[]{"CODIGO", "DISPONIBLE", "PRECIO", "MANTENIMIENTO","NOMBRE"});

    String sql = "SELECT * FROM cancha WHERE Disponible LIKE 'S%'";

    try (Connection conex = getAbrirConexion();
         PreparedStatement preparaConsulta = conex.prepareStatement(sql);
         ResultSet resultado = preparaConsulta.executeQuery()) {

        while (resultado.next()) {
            modeloTabla.addRow(new Object[]{
                resultado.getInt("Id_cancha"),
                resultado.getString("Disponible"),
                resultado.getFloat("Precios"),
                resultado.getString("Mantenimiento"),
                resultado.getString("Nombre")    
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
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
        sql = "SELECT * FROM cancha WHERE Disponible LIKE '%S%' " + condicion;
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
           
            sql = "INSERT INTO cancha(Disponible,Precios,Mantenimiento,Nombre)" + 
                    "VALUES (?,?,?,?)";
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, pCancha.getDisponible());
            consulta.setFloat(2, pCancha.getPrecio());
            consulta.setString(3, pCancha.getMantenimiento());
            consulta.setString(4, pCancha.getNombre());
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
            sql = "UPDATE cancha SET Disponible = ?, Precios = ?,Mantenimiento = ? , Nombre = ?" + 
                "WHERE Id_cancha = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setString(1, pCancha.getDisponible());
            consulta.setFloat(2, pCancha.getPrecio());
            consulta.setString(3, pCancha.getMantenimiento());
            consulta.setString(4, pCancha.getNombre());
            consulta.setInt(5, pCancha.getId_cancha());
            consulta.executeUpdate();
            return true; 
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
 
     public boolean eliminarCancha(modeloCancha pCancha)
    {
        Connection conex = getAbrirConexion();
        PreparedStatement consulta = null;
        String sql; 
        try 
        {
            sql = "UPDATE cancha SET Disponible = 'NO' WHERE Id_cancha = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, pCancha.getId_cancha());
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
        String sql = "";
        ResultSet resultado = null;
       
       try {
            sql = "SELECT * FROM cancha WHERE Id_cancha = ?";
            consulta = conex.prepareStatement(sql);
            consulta.setInt(1, pCancha.getId_cancha());
            resultado = consulta.executeQuery();
            if(resultado.next()){
            pCancha.setDisponible(resultado.getString("Disponible"));
            pCancha.setId_cancha(resultado.getInt("Id_cancha"));
            pCancha.setMantenimiento(resultado.getString("Mantenimiento"));
            pCancha.setPrecio(resultado.getFloat("Precios"));
            pCancha.setNombre(resultado.getString("Nombre"));
            return pCancha;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return pCancha;
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

