package vista;

import dao.ReservaDAO;
import modelo.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;


public class VistaReserva extends JFrame {

    private JTextField txtUsuario;
    private JTextField txtPista;
    private JTextField txtFecha;
    private JTextField txtHoraInicio;
    private JTextField txtHoraFin;

    private JComboBox<String> comboTipoDia;
    private JComboBox<String> comboFranja;

    private JButton btnReservar;
    private JButton btnVerReservas;

    public VistaReserva() {

        setTitle("Gestión de Reservas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        panel.add(new JLabel("ID Usuario:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("ID Pista:"));
        txtPista = new JTextField();
        panel.add(txtPista);

        panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField();
        panel.add(txtFecha);

        panel.add(new JLabel("Hora inicio (HH:MM:SS):"));
        txtHoraInicio = new JTextField();
        panel.add(txtHoraInicio);

        panel.add(new JLabel("Hora fin (HH:MM:SS):"));
        txtHoraFin = new JTextField();
        panel.add(txtHoraFin);

        panel.add(new JLabel("Tipo día:"));
        comboTipoDia = new JComboBox<>(new String[]{"Laborable", "FinDeSemana"});
        panel.add(comboTipoDia);

        panel.add(new JLabel("Franja horaria:"));
        comboFranja = new JComboBox<>(new String[]{"Mañana", "Tarde"});
        panel.add(comboFranja);

        btnReservar = new JButton("Crear Reserva");
        btnVerReservas = new JButton("Ver Reservas");
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnReservar);
        panelBoton.add(btnVerReservas);


        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearReserva();
            }
        });

        btnVerReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VistaListaReserva().setVisible(true);
            }
        });


        add(panel, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
    }



    private void crearReserva() {
        try {

            int idUsuario = Integer.parseInt(txtUsuario.getText());
            int idPista = Integer.parseInt(txtPista.getText());

            Date fecha = Date.valueOf(txtFecha.getText());
            Time horaInicio = Time.valueOf(txtHoraInicio.getText());
            Time horaFin = Time.valueOf(txtHoraFin.getText());

            String tipoDia = comboTipoDia.getSelectedItem().toString();
            String franja = comboFranja.getSelectedItem().toString();

            Reserva reserva = new Reserva(
                    0,
                    idUsuario,
                    idPista,
                    0,
                    fecha,
                    horaInicio,
                    horaFin
            );

            ReservaDAO dao = new ReservaDAO();
            dao.insertarReserva(reserva, tipoDia, franja);

            JOptionPane.showMessageDialog(this, "Reserva creada correctamente");
            limpiarCampos();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }



    private void limpiarCampos() {

        txtUsuario.setText("");
        txtPista.setText("");
        txtFecha.setText("");
        txtHoraInicio.setText("");
        txtHoraFin.setText("");

        comboTipoDia.setSelectedIndex(0);
        comboFranja.setSelectedIndex(0);
    }
}