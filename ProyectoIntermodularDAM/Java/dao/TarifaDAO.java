package dao;

import modelo.Tarifa;
import conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarifaDAO {

    private Connection conn;

    public TarifaDAO() throws SQLException {
        this.conn = conexion.getConnection();
    }

    public List<Tarifa> obtenerTarifas() throws SQLException {

        List<Tarifa> tarifas = new ArrayList<>();
        String sql = "SELECT * FROM tarifa";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Tarifa t = new Tarifa(
                    rs.getInt("id_tarifa"),
                    rs.getString("tipo_pista"),
                    rs.getString("tipo_dia"),
                    rs.getString("franja_horaria"),
                    rs.getDouble("precio")
                );

                tarifas.add(t);
            }
        }

        return tarifas;
    }

    public void insertarTarifa(Tarifa tarifa) throws SQLException {

        String sql = "INSERT INTO tarifa (tipo_pista, tipo_dia, franja_horaria, precio) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tarifa.getTipo_pista());
            ps.setString(2, tarifa.getTipo_dia());
            ps.setString(3, tarifa.getFranja_horaria());
            ps.setDouble(4, tarifa.getPrecio());

            ps.executeUpdate();
        }
    }

    public void actualizarTarifa(Tarifa tarifa) throws SQLException {

        String sql = "UPDATE tarifa SET tipo_pista=?, tipo_dia=?, franja_horaria=?, precio=? WHERE id_tarifa=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tarifa.getTipo_pista());
            ps.setString(2, tarifa.getTipo_dia());
            ps.setString(3, tarifa.getFranja_horaria());
            ps.setDouble(4, tarifa.getPrecio());
            ps.setInt(5, tarifa.getId_tarifa());

            ps.executeUpdate();
        }
    }

    public void eliminarTarifa(int id_tarifa) throws SQLException {

        String sql = "DELETE FROM tarifa WHERE id_tarifa=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_tarifa);

            ps.executeUpdate();
        }
    }
}
