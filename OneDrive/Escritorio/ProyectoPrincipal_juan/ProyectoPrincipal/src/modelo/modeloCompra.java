package modelo;

import java.sql.Date;

public class modeloCompra {
    private int Id_compra;
    private String Factura_nro;
    private String Tipo_compra;
    private Date Fecha;
    private double Subtotal;
    private double Iva0;
    private double Iva5;
    private double Iva10;
    private double Total_neto;
    private double Saldo;
    private int Estado;
    private int Fk_usuario;
    private int Fk_proveedor;

    // Constructor vacío
    public modeloCompra() {
    }

    // Constructor con parámetros
    public modeloCompra(int Id_compra, String Factura_nro, String Tipo_compra, Date Fecha,
                        double Subtotal, double Iva0, double Iva5, double Iva10, double Total_neto,
                        double Saldo, int Estado, int Fk_usuario, int Fk_proveedor) {
        this.Id_compra = Id_compra;
        this.Factura_nro = Factura_nro;
        this.Tipo_compra = Tipo_compra;
        this.Fecha = Fecha;
        this.Subtotal = Subtotal;
        this.Iva0 = Iva0;
        this.Iva5 = Iva5;
        this.Iva10 = Iva10;
        this.Total_neto = Total_neto;
        this.Saldo = Saldo;
        this.Estado = Estado;
        this.Fk_usuario = Fk_usuario;
        this.Fk_proveedor = Fk_proveedor;
    }

    // Getters y Setters
    public int getId_compra() {
        return Id_compra;
    }

    public void setId_compra(int Id_compra) {
        this.Id_compra = Id_compra;
    }

    public String getFactura_nro() {
        return Factura_nro;
    }

    public void setFactura_nro(String Factura_nro) {
        this.Factura_nro = Factura_nro;
    }

    public String getTipo_compra() {
        return Tipo_compra;
    }

    public void setTipo_compra(String Tipo_compra) {
        this.Tipo_compra = Tipo_compra;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(double Subtotal) {
        this.Subtotal = Subtotal;
    }

    public double getIva0() {
        return Iva0;
    }

    public void setIva0(double Iva0) {
        this.Iva0 = Iva0;
    }

    public double getIva5() {
        return Iva5;
    }

    public void setIva5(double Iva5) {
        this.Iva5 = Iva5;
    }

    public double getIva10() {
        return Iva10;
    }

    public void setIva10(double Iva10) {
        this.Iva10 = Iva10;
    }

    public double getTotal_neto() {
        return Total_neto;
    }

    public void setTotal_neto(double Total_neto) {
        this.Total_neto = Total_neto;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public int getFk_usuario() {
        return Fk_usuario;
    }

    public void setFk_usuario(int Fk_usuario) {
        this.Fk_usuario = Fk_usuario;
    }

    public int getFk_proveedor() {
        return Fk_proveedor;
    }

    public void setFk_proveedor(int Fk_proveedor) {
        this.Fk_proveedor = Fk_proveedor;
    }
}

