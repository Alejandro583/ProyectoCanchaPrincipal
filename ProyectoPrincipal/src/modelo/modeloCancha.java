package modelo;


public class modeloCancha 
{
    private int     Id_cancha;
    private String  Disponible;
    private float   Precio;
    private String  Mantenimiento;
    private String Nombre;

    //Constructor
    public modeloCancha() 
    {
        
    }
    
    //Constructor Con Parametros
    public modeloCancha(int Id_cancha, String Disponible, float Precio, String Mantenimiento) {
        this.Id_cancha = Id_cancha;
        this.Disponible = Disponible;
        this.Precio = Precio;
        this.Mantenimiento = Mantenimiento;
    }
    
    //Setters y Getters
    public int getId_cancha() {
        return Id_cancha;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    

    public void setId_cancha(int Id_cancha) {
        this.Id_cancha = Id_cancha;
    }

    public String getDisponible() {
        return Disponible;
    }

    public void setDisponible(String Disponible) {
        this.Disponible = Disponible;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public String getMantenimiento() {
        return Mantenimiento;
    }

    public void setMantenimiento(String Mantenimiento) {
        this.Mantenimiento = Mantenimiento;
    }  
}
