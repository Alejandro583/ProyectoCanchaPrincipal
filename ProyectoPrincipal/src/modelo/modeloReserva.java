package modelo;


public class modeloReserva 
{
    private int Id_reserva;
    private String Horario_inicio;
    private String Horario_fin;
    private String Obs;
    private int Fk_cancha;
    private int Fk_cliente;

    public modeloReserva() {
    }

    public modeloReserva(int Id_reserva, String Horario_inicio, String Horario_fin, String Obs, int Fk_cancha, int Fk_cliente) {
        this.Id_reserva = Id_reserva;
        this.Horario_inicio = Horario_inicio;
        this.Horario_fin = Horario_fin;
        this.Obs = Obs;
        this.Fk_cancha = Fk_cancha;
        this.Fk_cliente = Fk_cliente;
    }

    public int getId_reserva() {
        return Id_reserva;
    }

    public void setId_reserva(int Id_reserva) {
        this.Id_reserva = Id_reserva;
    }

    public String getHorario_inicio() {
        return Horario_inicio;
    }

    public void setHorario_inicio(String Horario_inicio) {
        this.Horario_inicio = Horario_inicio;
    }

    public String getHorario_fin() {
        return Horario_fin;
    }

    public void setHorario_fin(String Horario_fin) {
        this.Horario_fin = Horario_fin;
    }

    public String getObs() {
        return Obs;
    }

    public void setObs(String Obs) {
        this.Obs = Obs;
    }

    public int getFk_cancha() {
        return Fk_cancha;
    }

    public void setFk_cancha(int Fk_cancha) {
        this.Fk_cancha = Fk_cancha;
    }

    public int getFk_cliente() {
        return Fk_cliente;
    }

    public void setFk_cliente(int Fk_cliente) {
        this.Fk_cliente = Fk_cliente;
    }
    
    
}
