
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conexion {
    private String db = "cancha_sintetica";
    private String usuario = "root";
    private String password = "1234";//CAMBIAR CONTRASEÑA
    private String servidor = "localhost";
    private String puerto = "3306";
    private String url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + db;
    private Connection cvcon = null;
    
    public Connection getAbrirConexion() {
        try {
            cvcon = (Connection) DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede conectar con la Base de datos. Verifique la conexion con el servidor\n", "Sytem T5", 0);
            System.exit(0);
        }
        return cvcon;
        
    }
    
    public void setCerrarConexion(Connection con){
        
        try {
            con.close();
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "No se pudo cerrar Conexion\n" + e, "Sistema T5", 0 );
        }
    }
    
    
}
