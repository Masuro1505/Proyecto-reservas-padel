package dao;

import modelo.Usuario;
import conexion.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> obtenerUsuarios() throws SQLException {

        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Usuario u = new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getTimestamp("fecha_registro")
                );

                usuarios.add(u);
            }
        }

        return usuarios;
    }

    
    
    public void insertarUsuario(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO usuario (nombre, apellidos, telefono, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getEmail());

            ps.executeUpdate();
        }
    }

    
    
    public void actualizarUsuario(Usuario usuario) throws SQLException {

        String sql = "UPDATE usuario SET nombre=?, apellidos=?, telefono=?, email=? WHERE id_usuario=?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getEmail());
            ps.setInt(5, usuario.getId_usuario());

            ps.executeUpdate();
        }
    }

    
    
    public void eliminarUsuario(int id_usuario) throws SQLException {

        String sql = "DELETE FROM usuario WHERE id_usuario=?";

        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_usuario);

            ps.executeUpdate();
        }
    }

}