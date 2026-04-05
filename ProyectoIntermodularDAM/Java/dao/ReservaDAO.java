package dao;

import modelo.Reserva;
import conexion.conexion;
import manejo.ManejoTarifa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    
    public List<Reserva> obtenerReservas() throws SQLException {

        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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



    public void insertarReserva(Reserva reserva, String tipoDia, String franjaHoraria) throws SQLException {

        if (estaDisponible(reserva.getId_pista(), reserva.getFecha(), reserva.getHora_inicio(), reserva.getHora_fin()) == false) {
            throw new SQLException("La pista ya está reservada en ese horario");
        }

        String tipoPista = obtenerTipoPista(reserva.getId_pista());

        int idTarifa = ManejoTarifa.obtenerIdTarifa(tipoPista, tipoDia, franjaHoraria);

        String sql = "INSERT INTO reserva (id_usuario, id_pista, id_tarifa, fecha, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reserva.getId_usuario());
            ps.setInt(2, reserva.getId_pista());
            ps.setInt(3, idTarifa);
            ps.setDate(4, reserva.getFecha());
            ps.setTime(5, reserva.getHora_inicio());
            ps.setTime(6, reserva.getHora_fin());

            ps.executeUpdate();
        }
    }



    public void actualizarReserva(Reserva reserva, String tipoDia, String franjaHoraria) throws SQLException {

        if (estaDisponibleActualizar(reserva.getId_reserva(), reserva.getId_pista(), reserva.getFecha(), reserva.getHora_inicio(), reserva.getHora_fin()) == false) {
            throw new SQLException("La pista ya está reservada en ese horario");
        }
        
        String tipoPista = obtenerTipoPista(reserva.getId_pista());

        int idTarifa = ManejoTarifa.obtenerIdTarifa(tipoPista, tipoDia, franjaHoraria);
        
        String sql = "UPDATE reserva SET id_usuario=?, id_pista=?, id_tarifa=?, fecha=?, hora_inicio=?, hora_fin=? WHERE id_reserva=?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reserva.getId_usuario());
            ps.setInt(2, reserva.getId_pista());
            ps.setInt(3, idTarifa);
            ps.setDate(4, reserva.getFecha());
            ps.setTime(5, reserva.getHora_inicio());
            ps.setTime(6, reserva.getHora_fin());
            ps.setInt(7, reserva.getId_reserva());

            ps.executeUpdate();
        }
    }



    public void eliminarReserva(int id_reserva) throws SQLException {

        String sql = "DELETE FROM reserva WHERE id_reserva=?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_reserva);

            ps.executeUpdate();
        }
    }



    private String obtenerTipoPista(int idPista) throws SQLException {

        String sql = "SELECT tipo FROM pista WHERE id_pista=?";

        try (Connection conn = conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPista);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tipo");
                } else {
                    throw new SQLException("Pista no encontrada");
                }
            }
        }
    }



   private boolean estaDisponible(int idPista, Date fecha, Time horaInicio, Time horaFin) throws SQLException {

        String sql = "SELECT COUNT(*) AS total FROM reserva " +
                     "WHERE id_pista=? AND fecha=? " +
                     "AND (hora_inicio < ? AND hora_fin > ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPista);
            ps.setDate(2, fecha);
            ps.setTime(3, horaFin);
            ps.setTime(4, horaInicio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") == 0;
                }
            }
        }

        return true;
    }
    
    

    private boolean estaDisponibleActualizar(int idReserva, int idPista, Date fecha, Time horaInicio, Time horaFin) throws SQLException {

        String sql = "SELECT COUNT(*) AS total FROM reserva " +
                     "WHERE id_pista=? AND fecha=? " +
                     "AND id_reserva<>? " +
                     "AND (hora_inicio < ? AND hora_fin > ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPista);
            ps.setDate(2, fecha);
            ps.setInt(3, idReserva);
            ps.setTime(4, horaFin);
            ps.setTime(5, horaInicio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") == 0;
                }
            }
        }

        return true;
    }
}