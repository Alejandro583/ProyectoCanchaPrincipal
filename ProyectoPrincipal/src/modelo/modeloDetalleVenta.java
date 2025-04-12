/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author two_r
 */


import java.math.BigDecimal;

public class modeloDetalleVenta {
    private int id_venta_detalle;
    private BigDecimal precio;
    private BigDecimal venta_producto;
    private BigDecimal costo;
    private int cantidad;
    private int fk_venta;
    private int fk_reserva;

    // Getters
    public int getId_venta_detalle() {
        return id_venta_detalle;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public BigDecimal getVenta_producto() {
        return venta_producto;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getFk_venta() {
        return fk_venta;
    }

    public int getFk_reserva() {
        return fk_reserva;
    }

    // Setters
    public void setId_venta_detalle(int id_venta_detalle) {
        this.id_venta_detalle = id_venta_detalle;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setVenta_producto(BigDecimal venta_producto) {
        this.venta_producto = venta_producto;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFk_venta(int fk_venta) {
        this.fk_venta = fk_venta;
    }

    public void setFk_reserva(int fk_reserva) {
        this.fk_reserva = fk_reserva;
    }
}

