package manejo;

import java.sql.*;
import conexion.conexion;

public class ManejoTarifa {

    public static int obtenerIdTarifa(String tipoPista, String tipoDia, String franjaHoraria) throws SQLException {

        String sql = "SELECT id_tarifa FROM tarifa WHERE tipo_pista=? AND tipo_dia=? AND franja_horaria=? LIMIT 1";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tipoPista);
            ps.setString(2, tipoDia);
            ps.setString(3, franjaHoraria);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_tarifa");
                } else {
                    throw new SQLException("No se encontró tarifa");
                }
            }
        }
    }
}
