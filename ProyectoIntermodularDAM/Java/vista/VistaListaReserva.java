package vista;

import dao.ReservaDAO;
import modelo.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class VistaListaReserva extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnEliminar;
    private JButton btnEditar;

    public VistaListaReserva() {

        setTitle("Listado de Reservas");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        String[] columnas = {"ID", "Usuario", "Pista", "Tarifa", "Fecha", "Hora Inicio", "Hora Fin"};

        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        btnEliminar = new JButton("Eliminar Reserva");
        btnEditar = new JButton("Editar Reserva");
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarReserva();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirEditar();
            }
        });

        add(panelBotones, BorderLayout.SOUTH);

        cargarReservas();
    }



    private void cargarReservas() {
        try {
            ReservaDAO dao = new ReservaDAO();
            List<Reserva> lista = dao.obtenerReservas();

            for (Reserva r : lista) {
                Object[] fila = {
                        r.getId_reserva(),
                        r.getId_usuario(),
                        r.getId_pista(),
                        r.getId_tarifa(),
                        r.getFecha(),
                        r.getHora_inicio(),
                        r.getHora_fin()
                };

                modelo.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar reservas: " + e.getMessage());
        }
    }



    private void eliminarReserva() {
        try {
            int filaSeleccionada = tabla.getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una reserva");
                return;
            }

            int idReserva = (int) modelo.getValueAt(filaSeleccionada, 0);

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que quieres eliminar esta reserva?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {

                ReservaDAO dao = new ReservaDAO();
                dao.eliminarReserva(idReserva);

                modelo.removeRow(filaSeleccionada);

                JOptionPane.showMessageDialog(this, "Reserva eliminada");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }



    private void abrirEditar() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una reserva");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);
        int usuario = (int) modelo.getValueAt(fila, 1);
        int pista = (int) modelo.getValueAt(fila, 2);

        java.sql.Date fecha = (java.sql.Date) modelo.getValueAt(fila, 4);
        java.sql.Time horaInicio = (java.sql.Time) modelo.getValueAt(fila, 5);
        java.sql.Time horaFin = (java.sql.Time) modelo.getValueAt(fila, 6);

        new VistaEditarReserva(id, usuario, pista, fecha, horaInicio, horaFin).setVisible(true);
    }
}