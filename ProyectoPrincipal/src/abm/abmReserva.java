package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.horariosCancha;
import modelo.horariosCancha.Franja;

public class abmReserva extends conexion {

    sesion oSesion;

    public abmReserva(sesion pSesion) {
        oSesion = pSesion;
    }

    public DefaultTableModel cargarHorarios(int idCanchaSeleccionada) 
    {
        List<Franja> franjas = horariosCancha.generarHorarios();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"HORARIO", "ESTADO"});

        try (Connection conex = getAbrirConexion()) {
            for (Franja franja : franjas) {
                
                boolean reservado = estaReservado(franja.inicio, franja.fin, idCanchaSeleccionada);

                
                modelo.addRow(new Object[]{
                    franja.inicio + " - " + franja.fin,
                    reservado ? "Ocupado" : "Disponible"
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
        }
        return modelo;
    }
    
    public boolean estaReservado(String inicio, String fin, int idCancha) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE Fk_cancha = ? AND " +
                     "((Horario_inicio >= ? AND Horario_inicio < ?) OR " +
                     "(Horario_fin > ? AND Horario_fin <= ?) OR " +
                     "(Horario_inicio <= ? AND Horario_fin >= ?))";

        try (Connection conex = getAbrirConexion();
             PreparedStatement ps = conex.prepareStatement(sql)) {

            ps.setInt(1, idCancha);
            ps.setString(2, inicio);
            ps.setString(3, fin);
            ps.setString(4, inicio);
            ps.setString(5, fin);
            ps.setString(6, inicio);
            ps.setString(7, fin);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
