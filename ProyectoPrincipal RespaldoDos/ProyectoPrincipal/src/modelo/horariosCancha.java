package modelo;

import java.util.ArrayList;
import java.util.List;

public class horariosCancha {
    
    public static class Franja {
        public String inicio;
        public String fin;

        public Franja(String inicio, String fin) {
            this.inicio = inicio;
            this.fin = fin;
        }
    }

    // Método para generar los horarios de una cancha
    public static List<Franja> generarHorarios() {
        List<Franja> franjas = new ArrayList<>();
        int horaInicio = 8;  // Hora de inicio a las 8
        int horaFin = 22;    // Hora de fin a las 22

        // Generamos franjas horarias de 1 hora
        for (int i = horaInicio; i < horaFin; i++) {
            String desde = String.format("%02d:00", i);
            String hasta = String.format("%02d:00", i + 1);
            franjas.add(new Franja(desde, hasta));
        }

        return franjas;
    }
}
