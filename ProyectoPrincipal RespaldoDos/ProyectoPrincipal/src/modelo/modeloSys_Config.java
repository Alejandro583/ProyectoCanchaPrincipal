package modelo;


public class modeloSys_Config 
{
    private int     id;
    private int     dec_costo;
    private int     dec_precio;
    private int     dec_cantidad;
    private String  config_moneda;
    private String  config_regional;
    private String  factura;
    private int     factura_seq;
    
    //Constructor
    public modeloSys_Config() 
    {
        
    }
    
    //Constructor Con Parametros
    public modeloSys_Config(int id, int dec_costo, int dec_precio, int dec_cantidad, String config_moneda, String config_regional, String factura, int factura_seq) {
        this.id = id;
        this.dec_costo = dec_costo;
        this.dec_precio = dec_precio;
        this.dec_cantidad = dec_cantidad;
        this.config_moneda = config_moneda;
        this.config_regional = config_regional;
        this.factura = factura;
        this.factura_seq = factura_seq;
    }
    
    //Setters y Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDec_costo() {
        return dec_costo;
    }

    public void setDec_costo(int dec_costo) {
        this.dec_costo = dec_costo;
    }

    public int getDec_precio() {
        return dec_precio;
    }

    public void setDec_precio(int dec_precio) {
        this.dec_precio = dec_precio;
    }

    public int getDec_cantidad() {
        return dec_cantidad;
    }

    public void setDec_cantidad(int dec_cantidad) {
        this.dec_cantidad = dec_cantidad;
    }

    public String getConfig_moneda() {
        return config_moneda;
    }

    public void setConfig_moneda(String config_moneda) {
        this.config_moneda = config_moneda;
    }

    public String getConfig_regional() {
        return config_regional;
    }

    public void setConfig_regional(String config_regional) {
        this.config_regional = config_regional;
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
}
