package modelo;


public class modeloCliente 
{
    private int     Id_cliente;
    private String  Ci;
    private String  Telefono;
    private String  Nombre;
    private int     Estado ;
    
    //Constructor
    public modeloCliente()
    {
        
    }
    
    //Constructor con parametrso
    public modeloCliente(int Id_cliente, String Ci, String Telefono, String Nombre, int Estado) {
        this.Id_cliente = Id_cliente;
        this.Ci = Ci;
        this.Telefono = Telefono;
        this.Nombre = Nombre;
        this.Estado = Estado;
    }

    //Setters y Getters
    public int getId_cliente() {
        return Id_cliente;
    }

    public void setId_cliente(int Id_cliente) {
        this.Id_cliente = Id_cliente;
    }

    public String getCi() {
        return Ci;
    }

    public void setCi(String Ci) {
        this.Ci = Ci;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    } 

    public void setId(int fkCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getRazonSocial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
