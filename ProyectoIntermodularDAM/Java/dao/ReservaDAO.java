package dao;

import modelo.Reserva;
import conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private Connection conn;

    public ReservaDAO() throws SQLException {
        this.conn = conexion.getConnection();
    }


    
    public List<Reserva> obtenerReservas() throws SQLException {

        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Reserva r = new Reserva(
                    rs.getInt("id_reserva"),
                    rs.getInt("id_usuario"),
                    rs.getInt("id_pista"),
                    rs.getInt("id_tarifa"),
                    rs.getDate("fecha"),
                    rs.getTime("hora_inicio"),
                    rs.getTime("hora_fin")
                );

                reservas.add(r);
            }
        }

        return reservas;
    }



    public void insertarReserva(Reserva reserva) throws SQLException {

        String sql = "INSERT INTO reserva (id_usuario, id_pista, fecha, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reserva.getId_usuario());
            ps.setInt(2, reserva.getId_pista());
            ps.setInt(3, reserva.getId_tarifa());
            ps.setDate(4, reserva.getFecha());
            ps.setTime(5, reserva.getHora_inicio());
            ps.setTime(6, reserva.getHora_fin());

            ps.executeUpdate();
        }
    }



    public void actualizarReserva(Reserva reserva) throws SQLException {

        String sql = "UPDATE reserva SET id_usuario=?, id_pista=?, fecha=?, hora_inicio=?, hora_fin=? WHERE id_reserva=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reserva.getId_usuario());
            ps.setInt(2, reserva.getId_pista());
            ps.setInt(3, reserva.getId_tarifa());
            ps.setDate(4, reserva.getFecha());
            ps.setTime(5, reserva.getHora_inicio());
            ps.setTime(6, reserva.getHora_fin());
            ps.setInt(7, reserva.getId_reserva());

            ps.executeUpdate();
        }
    }



    public void eliminarReserva(int id_reserva) throws SQLException {

        String sql = "DELETE FROM reserva WHERE id_reserva=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_reserva);

            ps.executeUpdate();
        }
    }
}