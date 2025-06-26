package modelo;


public class modeloProducto
{
    private int     Id_producto;
    private float   Precio;
    private String  Nombre_producto;
    private int     Stock;
    private float   Costo;
    private int     Estado;
    private int     Fk_proveedor;
    
    //Constructor
    public modeloProducto() 
    {
        
    }
    
    //Constructor Con Parametros
    public modeloProducto(int Id_producto, float Precio, String Nombre_producto, int Stock, float Costo, int estado, int Fk_proveedor) {
        this.Id_producto = Id_producto;
        this.Precio = Precio;
        this.Nombre_producto = Nombre_producto;
        this.Stock = Stock;
        this.Costo = Costo;
        this.Estado = estado;
        this.Fk_proveedor = Fk_proveedor;
    }
    
    //Setters y Getters
    public int getId_producto() {
        return Id_producto;
    }

    public void setId_producto(int Id_producto) {
        this.Id_producto = Id_producto;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public String getNombre_producto() {
        return Nombre_producto;
    }

    public void setNombre_producto(String Nombre_producto) {
        this.Nombre_producto = Nombre_producto;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public float getCosto() {
        return Costo;
    }

    public void setCosto(float Costo) {
        this.Costo = Costo;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        this.Estado = estado;
    }

    public int getFk_proveedor() {
        return Fk_proveedor;
    }

    public void setFk_proveedor(int Fk_proveedor) {
        this.Fk_proveedor = Fk_proveedor;
    }  

    public int getIdProducto() {
    return this.Id_producto;
}

public void setIdProducto(int idProducto) {
    this.Id_producto = idProducto;
}

public String getNombre() {
    return this.Nombre_producto;
}

public void setNombre(String nombre) {
    this.Nombre_producto = nombre;
}

public void setPrecioVenta(double precioVenta) {
    this.Precio = (float) precioVenta;
}
}
