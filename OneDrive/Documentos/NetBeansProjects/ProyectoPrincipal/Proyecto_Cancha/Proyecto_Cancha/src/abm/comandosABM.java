package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class comandosABM extends conexion {

    //instancia de sesion---------------------------------------------------------------------------
    sesion oSesion;

    //constructor------------------------------------------------------------------------------------
    public comandosABM(sesion pSesion) {
        oSesion = pSesion;
    }

    //funcion para cargar tabla----------------------------------------------------------------------
    public DefaultTableModel cargarTabla(String condicion) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"COL1", "COL2", "COL3"});

        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;
        try {
            sql = "select * from ... " + condicion;
            preparaConsulta = conex.prepareStatement(sql);
            resultado = preparaConsulta.executeQuery();

            while (resultado.next() == true) {
                modeloTabla.addRow(new Object[]{
                    resultado.getInt("campo 1"),
                    resultado.getString("campo 2"),
                    resultado.getString("campo 3"),});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
        }
        return modeloTabla;
    }

    //funcion para cargar registro al precionar modifcar---------------------------------------------
    //necesita codigo para modicar y un modelo en donde va guardar los registros capturados de la db
    public boolean cargarRegistros(String codigo /*modeloCliente pModelo */) {
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        ResultSet resultado = null;

        try {
            sql = "select * from .... where id= " + codigo;
            preparaConsulta = conex.prepareStatement(sql);
            resultado = preparaConsulta.executeQuery();
            if (resultado.next() == true) {
                //se carga en el modelo los datos obtenidos de la db-----------------------------------

                //pModelo.setId(resultado.getInt("campo"));
                //pModelo.setNombre(resultado.getString("campo"));
                //pModelo.setDireccion(resultado.getString("campo"));
                conex.close();
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
            return false;
        }

    }
    
    //para insertar necesita un modelo en donde se encuentra los datos a insertar en la db
    public boolean insertarRegistros(/*modeloCliente modelo*/) {
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        try {
            sql = "insert into tabla (campo1,campo2,campo3,campo4)values(?,?,?,?)";
            preparaConsulta = conex.prepareStatement(sql);
            //preparaConsulta.setString(1, modelo.getNombre());
            //preparaConsulta.setString(2, modelo.getTelefono());
            //preparaConsulta.setString(3, modelo.getRuc());
            //preparaConsulta.setString(4, modelo.getDireccion());
            preparaConsulta.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
            return false;
        }
    }
    
     //para actualizar necesita un modelo en donde se encuentra los datos a insertar en la db
    public boolean modificarRegistros(/*modeloCliente modelo*/) {
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        try {
            sql = "update cliente set campo=?, campo=?, campo=?, campo=? where campo=?";
            preparaConsulta = conex.prepareStatement(sql);
           // preparaConsulta.setString(1, modelo.getNombre());
           // preparaConsulta.setString(2, modelo.getRuc());
           // preparaConsulta.setString(3, modelo.getDireccion());
           // preparaConsulta.setString(4, modelo.getTelefono());
           // preparaConsulta.setInt(5, modelo.getId());
            preparaConsulta.execute();
            conex.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
            return false;
        }

    }
    
    public boolean eliminarRegistros(String codigo) {
        PreparedStatement preparaConsulta = null;
        Connection conex = getAbrirConexion();
        String sql = "";
        try {
            sql = "delete from tabla where id=?";
            preparaConsulta = conex.prepareStatement(sql);
            preparaConsulta.setInt(1, Integer.parseInt(codigo));
            preparaConsulta.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, oSesion.getTituloMensaje(), 1);
            return false;
        }

    }

}
