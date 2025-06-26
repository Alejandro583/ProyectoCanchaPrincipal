package modelo;

import java.sql.Date;

public class modeloCaja {
    private int Id_caja;
    private double Total;
    private double Efectivo;
    private double Tarjeta;
    private Date Fecha; 
    private boolean Estado;
    private int Fk_usuario;


   

//Constructor
    public modeloCaja() 
    {
        
    }
    
    //Constructor Con Parametros
    public modeloCaja(int Id_caja, double Total, double Efectivo, double Tarjeta, Date Fecha, boolean Estado, int Fk_usuario) {
        this.Id_caja = Id_caja;
        this.Total = Total;
        this.Efectivo = Efectivo;
        this.Tarjeta = Tarjeta;
        this.Fecha = Fecha;
        this.Estado = Estado;
        this.Fk_usuario= Fk_usuario;
    }

    
  // Getters y setters
    public int getId_caja() {
        return Id_caja;
    }

    public void setId_caja(int Id_caja) {
        this.Id_caja = Id_caja;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public double getEfectivo() {
        return Efectivo;
    }

    public void setEfectivo(double Efectivo) {
        this.Efectivo = Efectivo;
    }

    public double getTarjeta() {
        return Tarjeta;
    }

    public void setTarjeta(double Tarjeta) {
        this.Tarjeta = Tarjeta;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public int getFk_usuario() {
        return Fk_usuario;
    }

    public void setFk_usuario(int Fk_usuario) {
        this.Fk_usuario = Fk_usuario;
    }
    
    
  
    
    
}
    