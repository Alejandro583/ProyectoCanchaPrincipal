package modelo;

public class modeloCaja {
    private int id_caja;
    private double total;
    private double efectivo;
    private double tarjeta;
    private String fecha; // o puedes usar java.sql.Date si lo preferís
    private boolean estado;
    private int fk_usuario;

    // Getters y setters
    public int getId_caja() { return id_caja; }
    public void setId_caja(int id_caja) { this.id_caja = id_caja; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public double getEfectivo() { return efectivo; }
    public void setEfectivo(double efectivo) { this.efectivo = efectivo; }

    public double getTarjeta() { return tarjeta; }
    public void setTarjeta(double tarjeta) { this.tarjeta = tarjeta; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public int getFk_usuario() { return fk_usuario; }
    public void setFk_usuario(int fk_usuario) { this.fk_usuario = fk_usuario; }
}

