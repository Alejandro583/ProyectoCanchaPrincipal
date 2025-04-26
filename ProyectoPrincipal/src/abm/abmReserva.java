package abm;

import config.conexion;
import config.sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.horariosCancha;
import modelo.horariosCancha.Franja;
import modelo.modeloReserva;

public class abmReserva extends conexion {

    sesion oSesion;

    public abmReserva(sesion pSesion) {
        oSesion = pSesion;
    }

    public abmReserva() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DefaultComboBoxModel cargarHorarios(int idCanchaSeleccionada,String fechaReserva) {
        List<Franja> franjas = horariosCancha.generarHorarios();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
       // modelo.setColumnIdentifiers(new Object[]{"HORARIO", "ESTADO"});

        try (Connection conex = getAbrirConexion()) {
            for (Franja franja : franjas) {

                boolean reservado = estaReservado(franja.inicio, franja.fin, idCanchaSeleccionada,fechaReserva);
                String valor;
                if(!reservado)
                {
                    valor = String.valueOf(franja.inicio) +" - "+ String.valueOf(franja.fin);
                    modelo.addElement(valor);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), oSesion.getTituloMensaje(), JOptionPane.ERROR_MESSAGE);
        }
        return modelo;
    }

    public DefaultTableModel cargarReservas() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new Object[]{"Observación", "Cliente", "Cancha", "Inicio", "Fin" ,});

        PreparedStatement ps = null;
        Connection conex = getAbrirConexion();
        String sql = "SELECT r.Obs, cl.Nombre AS Cliente, c.Nombre AS Cancha, r.Horario_inicio, r.Horario_fin "
                + "FROM reserva r "
                + "JOIN cliente cl ON r.Fk_cliente = cl.Id_cliente "
                + "JOIN cancha c ON r.Fk_cancha = c.Id_cancha "
                + "ORDER BY r.Horario_inicio";

        ResultSet rs = null;

        try {
            ps = conex.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    rs.getString("Obs"),
                    rs.getString("Cliente"),
                    rs.getString("Cancha"),
                    rs.getString("Horario_inicio"),
                    rs.getString("Horario_fin")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return modeloTabla;
    }

   public boolean estaReservado(String inicio, String fin, int idCancha, String fechaReserva) {
    String sql = "SELECT COUNT(*) FROM reserva WHERE Fk_cancha = ? "
               + "AND Horario_inicio < ? AND Horario_fin > ? "
               + "AND Fecha_reserva = ?"; // Se agrega la condición de la fecha

    try (Connection conex = getAbrirConexion();
         PreparedStatement ps = conex.prepareStatement(sql)) {

        ps.setInt(1, idCancha);
        ps.setString(2, fin);   // fin del bloque consultado
        ps.setString(3, inicio); // inicio del bloque consultado
        ps.setString(4, fechaReserva); // La fecha de reserva que se pasa

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


    public boolean modificarReserva(modeloReserva reserva) {
        Connection conex = getAbrirConexion();
        String sql = "UPDATE reserva SET Obs = ?, Horario_inicio = ?, Horario_fin = ?, Fk_cancha = ?, Fk_cliente = ? WHERE Id_reserva = ?";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {
            ps.setString(1, reserva.getObs());
            ps.setString(2, reserva.getHorario_inicio());
            ps.setString(3, reserva.getHorario_fin());
            ps.setInt(4, reserva.getFk_cancha());
            ps.setInt(5, reserva.getFk_cliente());
            ps.setInt(6, reserva.getId_reserva());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminarReserva(modeloReserva reserva) {
        Connection conex = getAbrirConexion();
        String sql = "DELETE FROM reserva WHERE Id_reserva = ?";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {
            ps.setInt(1, reserva.getId_reserva());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // En caso de error o si no se eliminó ninguna fila
    }

    public boolean agregarReserva(modeloReserva reserva) {
        Connection conex = getAbrirConexion();
        String sql = "INSERT INTO reserva (Obs, Horario_inicio, Horario_fin, Fk_cancha, Fk_cliente,Fecha_reserva) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conex.prepareStatement(sql)) {
            // Establecer los parámetros de la consulta
            ps.setString(1, reserva.getObs()); // Observación de la reserva
            ps.setString(2, reserva.getHorario_inicio()); // Horario de inicio de la reserva
            ps.setString(3, reserva.getHorario_fin()); // Horario de fin de la reserva
            ps.setInt(4, reserva.getFk_cancha()); // ID de la cancha
            ps.setInt(5, reserva.getFk_cliente()); // ID del cliente
            ps.setString(6, reserva.getFechaReserva());

            int filasAfectadas = ps.executeUpdate(); // Ejecutar la inserción

            return filasAfectadas > 0; // Si se insertó al menos una fila, la reserva se agregó con éxito
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // En caso de error o si no se insertó ninguna fila
    }
    
    
    public modeloReserva reservaExiste(int pId_reserva) {
    Connection conex = getAbrirConexion();
    PreparedStatement consulta = null;
    String sql;
    ResultSet resultado = null;
    modeloReserva reserva = new modeloReserva();
    reserva = null;
    
    try {
        sql = "SELECT * FROM reserva WHERE Id_reserva = ?";
        consulta = conex.prepareStatement(sql);
        consulta.setInt(1, pId_reserva);
        resultado = consulta.executeQuery();
        
        if (resultado.next() == true) {
            reserva = new modeloReserva();
            reserva.setId_reserva(resultado.getInt("Id_reserva"));
            reserva.setHorario_inicio(resultado.getString("Horario_inicio"));
            reserva.setHorario_fin(resultado.getString("Horario_fin"));
            reserva.setObs(resultado.getString("Obs"));
            reserva.setFk_cancha(resultado.getInt("Fk_cancha"));
            reserva.setFk_cliente(resultado.getInt("Fk_cliente"));
            return reserva;
        } else {
            return reserva; // null
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        return reserva; // null
    }
}

    public DefaultComboBoxModel<String> cargarFechas() {
    DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

    // Formato de fecha (podés cambiarlo si querés)
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM");

    // Día de hoy
    LocalDate hoy = LocalDate.now();

    // Agregar hoy y los próximos 7 días
    for (int i = 0; i <= 7; i++) {
        LocalDate fecha = hoy.plusDays(i);
        modelo.addElement(fecha.format(formato));
    }

    return modelo;
}

}
