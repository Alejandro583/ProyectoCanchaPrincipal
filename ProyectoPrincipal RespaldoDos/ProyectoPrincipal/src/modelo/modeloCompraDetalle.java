package modelo;

public class modeloCompraDetalle {
    private int Id_compra_detalle;
    private double Costo;
    private int Cantidad;
    private String Compra_producto;
    private double Costo_medio;
    private int Iva;
    private int Estado;
    private int Fk_compra;
    private int Fk_producto;

    // Constructor vacío
    public modeloCompraDetalle() {
    }

    // Constructor con parámetros
    public modeloCompraDetalle(int Id_compra_detalle, double Costo, int Cantidad, String Compra_producto,
                               double Costo_medio, int Iva, int Estado, int Fk_compra, int Fk_producto) {
        this.Id_compra_detalle = Id_compra_detalle;
        this.Costo = Costo;
        this.Cantidad = Cantidad;
        this.Compra_producto = Compra_producto;
        this.Costo_medio = Costo_medio;
        this.Iva = Iva;
        this.Estado = Estado;
        this.Fk_compra = Fk_compra;
        this.Fk_producto = Fk_producto;
    }

    // Getters y Setters
    public int getId_compra_detalle() {
        return Id_compra_detalle;
    }

    public void setId_compra_detalle(int Id_compra_detalle) {
        this.Id_compra_detalle = Id_compra_detalle;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getCompra_producto() {
        return Compra_producto;
    }

    public void setCompra_producto(String Compra_producto) {
        this.Compra_producto = Compra_producto;
    }

    public double getCosto_medio() {
        return Costo_medio;
    }

    public void setCosto_medio(double Costo_medio) {
        this.Costo_medio = Costo_medio;
    }

    public int getIva() {
        return Iva;
    }

    public void setIva(int Iva) {
        this.Iva = Iva;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public int getFk_compra() {
        return Fk_compra;
    }

    public void setFk_compra(int Fk_compra) {
        this.Fk_compra = Fk_compra;
    }

    public int getFk_producto() {
        return Fk_producto;
    }

    public void setFk_producto(int Fk_producto) {
        this.Fk_producto = Fk_producto;
    }
}

