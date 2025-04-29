/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import config.sesion;
import java.sql.Date;

/**
 *
 * @author two_r
 */
public class modeloVenta {
    
    private int idVenta;
    private java.sql.Date fecha;
    private double saldo;
    private double totalCosto;
    private double subtotal;
    private double iva0;
    private double iva5;
    private double iva10;
    private double estado;
    private double facturaNro;
    private String tipoVenta;
    private double totalNeto;
    private double ttlPago;
    private double ttlDescuento;
    private double ttlSaldo;
    private int fkCliente;
    private int fkCaja;
    private int fkUsuario;

    public modeloVenta(sesion Osesion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters y Setters...

    public int getIdVenta() {
        return idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getTotalCosto() {
        return totalCosto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva0() {
        return iva0;
    }

    public double getIva5() {
        return iva5;
    }

    public double getIva10() {
        return iva10;
    }

    public double getEstado() {
        return estado;
    }

    public double getFacturaNro() {
        return facturaNro;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public double getTotalNeto() {
        return totalNeto;
    }

    public double getTtlPago() {
        return ttlPago;
    }

    public double getTtlDescuento() {
        return ttlDescuento;
    }

    public double getTtlSaldo() {
        return ttlSaldo;
    }

    public int getFkCliente() {
        return fkCliente;
    }

    public int getFkCaja() {
        return fkCaja;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTotalCosto(double totalCosto) {
        this.totalCosto = totalCosto;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setIva0(double iva0) {
        this.iva0 = iva0;
    }

    public void setIva5(double iva5) {
        this.iva5 = iva5;
    }

    public void setIva10(double iva10) {
        this.iva10 = iva10;
    }

    public void setEstado(double estado) {
        this.estado = estado;
    }

    public void setFacturaNro(double facturaNro) {
        this.facturaNro = facturaNro;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public void setTotalNeto(double totalNeto) {
        this.totalNeto = totalNeto;
    }

    public void setTtlPago(double ttlPago) {
        this.ttlPago = ttlPago;
    }

    public void setTtlDescuento(double ttlDescuento) {
        this.ttlDescuento = ttlDescuento;
    }

    public void setTtlSaldo(double ttlSaldo) {
        this.ttlSaldo = ttlSaldo;
    }

    public void setFkCliente(int fkCliente) {
        this.fkCliente = fkCliente;
    }

    public void setFkCaja(int fkCaja) {
        this.fkCaja = fkCaja;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Object getIdOperacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
