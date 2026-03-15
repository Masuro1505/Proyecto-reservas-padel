import dao.*;
import modelo.*;
import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            System.out.println("Conexión correcta a la base de datos.\n");


            UsuarioDAO usuarioDAO = new UsuarioDAO();
            PistaDAO pistaDAO = new PistaDAO();
            TarifaDAO tarifaDAO = new TarifaDAO();
            ReservaDAO reservaDAO = new ReservaDAO();
            PagoDAO pagoDAO = new PagoDAO();

            
            System.out.println("\nINSERTAR USUARIO DE PRUEBA:");

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre("Marco");
            nuevoUsuario.setApellidos("Suárez");
            nuevoUsuario.setTelefono("600558732");
            nuevoUsuario.setEmail("marco@email.com");

            usuarioDAO.insertarUsuario(nuevoUsuario);

            System.out.println("Usuario insertado correctamente.");

            

            System.out.println("\nACTUALIZAR USUARIO:");

            Usuario usuarioActualizar = new Usuario();
            usuarioActualizar.setId_usuario(1);
            usuarioActualizar.setNombre("Juan");
            usuarioActualizar.setApellidos("Álvarez");
            usuarioActualizar.setTelefono("627463429");
            usuarioActualizar.setEmail("juan_alvarez_actualizado@email.com");

            usuarioDAO.actualizarUsuario(usuarioActualizar);

            System.out.println("Usuario actualizado correctamente.");



            System.out.println("\nELIMINAR USUARIO:");

            usuarioDAO.eliminarUsuario(5);

            System.out.println("Usuario eliminado correctamente.");

            

            System.out.println("USUARIOS:");
            List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
            for (Usuario u : usuarios) {
                System.out.println(u.getId_usuario() + " - " + u.getNombre() + " " + u.getApellidos() + " - " + u.getEmail());
            }


            System.out.println("\nPISTAS:");
            List<Pista> pistas = pistaDAO.obtenerPistas();
            for (Pista p : pistas) {
                System.out.println(p.getId_pista() + " - " + p.getNombre() + " - " + p.getTipo() + " - " + p.getEstado());
            }


            System.out.println("\nTARIFAS:");
            List<Tarifa> tarifas = tarifaDAO.obtenerTarifas();
            for (Tarifa t : tarifas) {
                System.out.println(t.getId_tarifa() + " - " + t.getTipo_pista() + " - " + t.getTipo_dia() + " - " + t.getFranja_horaria() + " - " + t.getPrecio() + "€");
            }


            System.out.println("\nRESERVAS:");
            List<Reserva> reservas = reservaDAO.obtenerReservas();
            for (Reserva r : reservas) {
                System.out.println(r.getId_reserva() + " Usuario: " + r.getId_usuario() + " Pista: " + r.getId_pista() +
                        " Tarifa: " + r.getId_tarifa() + " Fecha: " + r.getFecha() +
                        " Inicio: " + r.getHora_inicio() + " Fin: " + r.getHora_fin());
            }


            System.out.println("\nPAGOS:");
            List<Pago> pagos = pagoDAO.obtenerPagos();
            for (Pago p : pagos) {
                System.out.println(p.getId_pago() + " - Reserva: " + p.getId_reserva() +
                        " - Fecha: " + p.getFecha_pago() + " - Método: " + p.getMetodo_pago() + " - Cantidad: " + p.getCantidad() + "€");
            }

            System.out.println("\n Prueba completa realizada correctamente.");

        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}