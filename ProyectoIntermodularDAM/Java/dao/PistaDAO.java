package dao;

import modelo.Pista;
import conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PistaDAO {

    private Connection conn;

    public PistaDAO() throws SQLException {
        this.conn = conexion.getConnection();
    }


    
    public List<Pista> obtenerPistas() throws SQLException {

        List<Pista> pistas = new ArrayList<>();
        String sql = "SELECT * FROM pista";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Pista p = new Pista(
                    rs.getInt("id_pista"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getString("estado")
                );

                pistas.add(p);
            }
        }

        return pistas;
    }



    public void insertarPista(Pista pista) throws SQLException {

        String sql = "INSERT INTO pista (nombre, tipo, estado) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pista.getNombre());
            ps.setString(2, pista.getTipo());
            ps.setString(3, pista.getEstado());

            ps.executeUpdate();
        }
    }



    public void actualizarPista(Pista pista) throws SQLException {

        String sql = "UPDATE pista SET nombre=?, tipo=?, estado=? WHERE id_pista=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pista.getNombre());
            ps.setString(2, pista.getTipo());
            ps.setString(3, pista.getEstado());
            ps.setInt(4, pista.getId_pista());

            ps.executeUpdate();
        }
    }



    public void eliminarPista(int id_pista) throws SQLException {

        String sql = "DELETE FROM pista WHERE id_pista=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_pista);

            ps.executeUpdate();
        }
    }
}
