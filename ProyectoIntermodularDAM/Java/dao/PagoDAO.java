package dao;

import modelo.Pago;
import conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    private Connection conn;

    public PagoDAO() throws SQLException {
        this.conn = conexion.getConnection();
    }

    public List<Pago> obtenerPagos() throws SQLException {

        List<Pago> pagos = new ArrayList<>();
        String sql = "SELECT * FROM pago";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Pago p = new Pago(
                    rs.getInt("id_pago"),
                    rs.getInt("id_reserva"),
                    rs.getDate("fecha_pago"),
                    rs.getString("metodo_pago"),
                    rs.getDouble("cantidad")
                );

                pagos.add(p);
            }
        }

        return pagos;
    }

    public void insertarPago(Pago pago) throws SQLException {

        String sql = "INSERT INTO pago (id_reserva, fecha_pago, metodo_pago, cantidad) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pago.getId_reserva());
            ps.setDate(2, pago.getFecha_pago());
            ps.setString(3, pago.getMetodo_pago());
            ps.setDouble(4, pago.getCantidad());

            ps.executeUpdate();
        }
    }

    public void actualizarPago(Pago pago) throws SQLException {

        String sql = "UPDATE pago SET id_reserva=?, fecha_pago=?, metodo_pago=?, cantidad=? WHERE id_pago=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pago.getId_reserva());
            ps.setDate(2, pago.getFecha_pago());
            ps.setString(3, pago.getMetodo_pago());
            ps.setDouble(4, pago.getCantidad());
            ps.setInt(5, pago.getId_pago());

            ps.executeUpdate();
        }
    }

    public void eliminarPago(int id_pago) throws SQLException {

        String sql = "DELETE FROM pago WHERE id_pago=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_pago);

            ps.executeUpdate();
        }
    }
}
