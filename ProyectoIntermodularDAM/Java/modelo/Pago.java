package modelo;

import java.sql.Date;

public class Pago {

    private int id_pago;
    private int id_reserva;
    private Date fecha_pago;
    private String metodo_pago;
    private double cantidad;

    public Pago() {}

    public Pago(int id_pago, int id_reserva, Date fecha_pago, String metodo_pago, double cantidad) {
        this.id_pago = id_pago;
        this.id_reserva = id_reserva;
        this.fecha_pago = fecha_pago;
        this.metodo_pago = metodo_pago;
        this.cantidad = cantidad;
    }

    public int getId_pago() { return id_pago; }
    public void setId_pago(int id_pago) { this.id_pago = id_pago; }

    public int getId_reserva() { return id_reserva; }
    public void setId_reserva(int id_reserva) { this.id_reserva = id_reserva; }

    public Date getFecha_pago() { return fecha_pago; }
    public void setFecha_pago(Date fecha_pago) { this.fecha_pago = fecha_pago; }

    public String getMetodo_pago() { return metodo_pago; }
    public void setMetodo_pago(String metodo_pago) { this.metodo_pago = metodo_pago; }

    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }
}
