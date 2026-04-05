package vista;

import dao.ReservaDAO;
import modelo.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;

public class VistaEditarReserva extends JFrame {

    private JTextField txtUsuario;
    private JTextField txtPista;
    private JTextField txtFecha;
    private JTextField txtHoraInicio;
    private JTextField txtHoraFin;

    private JComboBox<String> comboTipoDia;
    private JComboBox<String> comboFranja;

    private JButton btnActualizar;

    private int idReserva;

    public VistaEditarReserva(int id, int usuario, int pista, Date fecha, Time horaInicio, Time horaFin) {

        this.idReserva = id;

        setTitle("Editar Reserva");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8,2));

        txtUsuario = new JTextField(String.valueOf(usuario));
        txtPista = new JTextField(String.valueOf(pista));
        txtFecha = new JTextField(fecha.toString());
        txtHoraInicio = new JTextField(horaInicio.toString());
        txtHoraFin = new JTextField(horaFin.toString());

        comboTipoDia = new JComboBox<>(new String[]{"Laborable", "FinDeSemana"});
        comboFranja = new JComboBox<>(new String[]{"Mañana", "Tarde"});

        add(new JLabel("Usuario")); add(txtUsuario);
        add(new JLabel("Pista")); add(txtPista);
        add(new JLabel("Fecha")); add(txtFecha);
        add(new JLabel("Hora inicio")); add(txtHoraInicio);
        add(new JLabel("Hora fin")); add(txtHoraFin);
        add(new JLabel("Tipo día")); add(comboTipoDia);
        add(new JLabel("Franja")); add(comboFranja);

        btnActualizar = new JButton("Actualizar");
        add(btnActualizar);

        
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarReserva();
            }
        });
    }


    
    private void actualizarReserva() {
        try {
            int usuario = Integer.parseInt(txtUsuario.getText());
            int pista = Integer.parseInt(txtPista.getText());

            Date fecha = Date.valueOf(txtFecha.getText());
            Time inicio = Time.valueOf(txtHoraInicio.getText());
            Time fin = Time.valueOf(txtHoraFin.getText());

            String tipoDia = comboTipoDia.getSelectedItem().toString();
            String franja = comboFranja.getSelectedItem().toString();

            Reserva r = new Reserva(idReserva, usuario, pista, 0, fecha, inicio, fin);

            ReservaDAO dao = new ReservaDAO();
            dao.actualizarReserva(r, tipoDia, franja);

            JOptionPane.showMessageDialog(this, "Reserva actualizada");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}