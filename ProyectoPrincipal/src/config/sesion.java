package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public final class sesion extends conexion {
        
    //variables para sesion de usuarios
    private String Usuario;
    private String NombreUsuario;
    private String Cargo;
    private int    IdUsuario;
    private String TituloMensaje = "UPE SOFTWARE";
    
    private String usuarioPrecio = "unitario";
    private String usuarioSinStock = "no";
    private String configMoneda = "US";
    
    
    //variables para mascara y formatos 
    private DecimalFormatSymbols simbolo;
    private DecimalFormat mascara;
    private String configRegional;

    //variables para decimales en cantidad, precio y costo
    private byte decimal_Precio;
    private byte decimal_Costo;
    private byte decimal_Cantidad;
    
    private String factura;
    private int factura_seq;
    

    public sesion() {
        cargarConfig();
        //asigna tipo de simbolo atravez de la config regional indicada desde la bd
        //Es: usa , para simbolo decimal     us: usar . para simbolo decimal
       // simbolo = DecimalFormatSymbols.getInstance(Locale.forLanguageTag(configRegional));
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public int getFactura_seq() {
        return factura_seq;
    }

    public void setFactura_seq(int factura_seq) {
        this.factura_seq = factura_seq;
    }

    
    

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.NombreUsuario = nombreUsuario;
    }

    
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        this.Cargo = cargo;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.IdUsuario = idUsuario;
    }

    public String getTituloMensaje() {
        return TituloMensaje;
    }

    public void setTituloMensaje(String TituloMensaje) {
        this.TituloMensaje = TituloMensaje;
    }

    public DecimalFormatSymbols getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(DecimalFormatSymbols simbolo) {
        this.simbolo = simbolo;
    }

    public DecimalFormat getMascara() {
        return mascara;
    }

    public void setMascara(DecimalFormat mascara) {
        this.mascara = mascara;
    }

    public String getConfigRegional() {
        return configRegional;
    }

    public void setConfigRegional(String configRegional) {
        this.configRegional = configRegional;
    }

    public byte getDecimal_Precio() {
        return decimal_Precio;
    }

    public void setDecimal_Precio(byte decimal_Precio) {
        this.decimal_Precio = decimal_Precio;
    }

    public byte getDecimal_Costo() {
        return decimal_Costo;
    }

    public void setDecimal_Costo(byte decimal_Costo) {
        this.decimal_Costo = decimal_Costo;
    }

    public byte getDecimal_Cantidad() {
        return decimal_Cantidad;
    }

    public void setDecimal_Cantidad(byte decimal_Cantidad) {
        this.decimal_Cantidad = decimal_Cantidad;
    }

    public String getUsuarioPrecio() {
        return usuarioPrecio;
    }

    public void setUsuarioPrecio(String usuarioPrecio) {
        this.usuarioPrecio = usuarioPrecio;
    }

    public String getUsuarioSinStock() {
        return usuarioSinStock;
    }

    public void setUsuarioSinStock(String usuarioSinStock) {
        this.usuarioSinStock = usuarioSinStock;
    }

    public String getConfigMoneda() {
        return configMoneda;
    }

    public void setConfigMoneda(String configMoneda) {
        this.configMoneda = configMoneda;
    }
    
    

    

    //funcion para capturar la configuracion desde la base de datos
    public void cargarConfig() {
        PreparedStatement ps = null;
        Connection con = getAbrirConexion();
        ResultSet rs = null;
        
        try {
            String sql = "select * from sys_config";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next() == true) {
                decimal_Precio = rs.getByte("dec_precio");
                decimal_Costo = rs.getByte("dec_Costo");
                decimal_Cantidad = rs.getByte("dec_Cantidad");
                configRegional = rs.getString("config_regional");
                configMoneda = rs.getString("config_moneda");
                factura = rs.getString("factura");
                factura_seq = rs.getInt("factura_seq");
            }
                        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar configuracion de sistemas.Cerrando aplicacion...\n" + e, TituloMensaje, 0);
            System.exit(0);
        } finally {
            setCerrarConexion(con);
        }
    }
    
    public boolean verificarAcceso(String pUsuario, String pPassword){
        PreparedStatement ps = null;
        Connection con = getAbrirConexion();
        ResultSet rs = null;
        String sql = "";
        sql = "select * from usuario where Usuario =? and Contrasenha = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pUsuario);
            ps.setString(2, pPassword);
            rs = ps.executeQuery();
            
            if(rs.next()==true){
                Usuario = rs.getString("Usuario");
                NombreUsuario = rs.getString("Nombre_usuario");
                IdUsuario = rs.getInt("Id_usuario");
                Cargo = rs.getString("Cargo");
                con.close();
                return true;
            }else{
                con.close();
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar Usuario", TituloMensaje, 0 );
            return false;
        }
        
    } 
    //funcion para aplicar formato o mascara a los numeros -> costo
    public String formatoCosto(float costo) {
        //decimal_Costo=2
        if (decimal_Costo == 0) {
            mascara = new DecimalFormat("###,##0", simbolo);
        } else if (decimal_Costo == 1) {
            mascara = new DecimalFormat("###,##0.0", simbolo);
        } else if (decimal_Costo == 2) {
            mascara = new DecimalFormat("###,##0.00", simbolo);
        } else if (decimal_Costo == 3) {
            mascara = new DecimalFormat("###,##0.000", simbolo);
        } else if (decimal_Costo == 4) {
            mascara = new DecimalFormat("###,##0.0000", simbolo);
        } else if (decimal_Costo == 5) {
            mascara = new DecimalFormat("###,##0.00000", simbolo);
        }
        
        return mascara.format(costo);
    }
    
    //funcion para aplicar formato o mascara a los numeros -> precio
    public String formatoPrecio(float precio){                        
        
        if(decimal_Precio==0){
            mascara = new DecimalFormat("###,##0",simbolo);
        }else if(decimal_Precio == 1){
            mascara = new DecimalFormat("###,##0.0",simbolo);
        }else if(decimal_Precio == 2){
            mascara = new DecimalFormat("###,##0.00",simbolo);
        }else if(decimal_Precio == 3){
            mascara = new DecimalFormat("###,##0.000",simbolo);
        }else if(decimal_Precio == 4){
            mascara = new DecimalFormat("###,##0.0000",simbolo);
        }else if(decimal_Precio == 5){
            mascara = new DecimalFormat("###,##0.00000",simbolo);
        }              
        return mascara.format(precio);                
    }
    
     public String formatoCantidad(float qty){                            
        if(decimal_Cantidad==0){
            mascara = new DecimalFormat("###,##0",simbolo);
        }else if(decimal_Cantidad == 1){
            mascara = new DecimalFormat("###,##0.0",simbolo);
        }else if(decimal_Cantidad == 2){
            mascara = new DecimalFormat("###,##0.00",simbolo);
        }else if(decimal_Cantidad == 3){
            mascara = new DecimalFormat("###,##0.000",simbolo);
        }else if(decimal_Cantidad == 4){
            mascara = new DecimalFormat("###,##0.0000",simbolo);
        }else if(decimal_Cantidad == 5){
            mascara = new DecimalFormat("###,##0.00000",simbolo);
        }              
        return mascara.format(qty);                
    }
     
     public boolean validarInt(String valor){
         try {
             Integer.parseInt(valor);
             return true;
         } catch (Exception e) {
             return false;
         }
     }
     
     
     public void validarNumeros(String texto, java.awt.event.KeyEvent evt) {
        char letra = evt.getKeyChar();
        int codigoLetra = (int) evt.getKeyChar();
        //evt.consume rechaza tecla
        if (codigoLetra >= 44 && codigoLetra <= 57) {
            if (configRegional.equals("us")) {
                if (letra == ',' && (letra < '0' || letra > '9')) {
                    evt.consume();
                } else {
                    if (letra == ',') {
                        if (texto.indexOf(",") != -1) {
                            evt.consume();
                        }
                    }
                }
            } else {
                if (letra == '.' && (letra < '0' || letra > '9')) {
                    evt.consume();
                } else {
                    if (letra == '.') {
                        if (texto.indexOf(".") != -1) {
                            evt.consume();
                        }
                    }
                }
            }
        } else {
            evt.consume();
        }

       
    }
     
     public float convertirFloat(String numero){
      
        DecimalFormat format = new DecimalFormat("###,##0.000", simbolo);
        
        float valor = 0;
        
        try {
            //convierte el string recibido en float
            valor = format.parse(numero).floatValue();
        } catch (ParseException ex) {
            Logger.getLogger(sesion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede convertir valor a float");
        }

        return valor;
     }
     
     public String formatoValorEnGs(float precio) {

        mascara = new DecimalFormat("###,##0", simbolo);
        return mascara.format(precio);
    }
     
    public boolean verificarEnter(java.awt.event.KeyEvent tecla){
        int codigoTecla = (int) tecla.getKeyChar();

        if (codigoTecla == 10) {
            return true;
        }else{
            return false;
        }
    }
        
}
