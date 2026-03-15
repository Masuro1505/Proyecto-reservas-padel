package modelo;

import java.sql.Date;
import java.sql.Time;

public class Reserva {
    private int id_reserva;
    private int id_usuario;
    private int id_pista;
    private int id_tarifa;
    private Date fecha;
    private Time hora_inicio;
    private Time hora_fin;

    public Reserva() {}

    public Reserva(int id_reserva, int id_usuario, int id_pista, int id_tarifa, Date fecha, Time hora_inicio, Time hora_fin) {
        this.id_reserva = id_reserva;
        this.id_usuario = id_usuario;
        this.id_pista = id_pista;
        this.id_tarifa = id_tarifa;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
    }

    public int getId_reserva() { return id_reserva; }
    public void setId_reserva(int id_reserva) { this.id_reserva = id_reserva; }

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public int getId_pista() { return id_pista; }
    public void setId_pista(int id_pista) { this.id_pista = id_pista; }

    public int getId_tarifa() { return id_tarifa; }
    public void setId_tarifa(int id_tarifa) { this.id_tarifa = id_tarifa; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Time getHora_inicio() { return hora_inicio; }
    public void setHora_inicio(Time hora_inicio) { this.hora_inicio = hora_inicio; }

    public Time getHora_fin() { return hora_fin; }
    public void setHora_fin(Time hora_fin) { this.hora_fin = hora_fin; }
}
