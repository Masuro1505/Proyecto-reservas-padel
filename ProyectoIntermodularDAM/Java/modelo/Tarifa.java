package modelo;

public class Tarifa {

    private int id_tarifa;
    private String tipo_pista;
    private String tipo_dia;
    private String franja_horaria;
    private double precio;

    public Tarifa() {}

    public Tarifa(int id_tarifa, String tipo_pista, String tipo_dia, String franja_horaria, double precio) {
        this.id_tarifa = id_tarifa;
        this.tipo_pista = tipo_pista;
        this.tipo_dia = tipo_dia;
        this.franja_horaria = franja_horaria;
        this.precio = precio;
    }

    public int getId_tarifa() { return id_tarifa; }
    public void setId_tarifa(int id_tarifa) { this.id_tarifa = id_tarifa; }

    public String getTipo_pista() { return tipo_pista; }
    public void setTipo_pista(String tipo_pista) { this.tipo_pista = tipo_pista; }

    public String getTipo_dia() { return tipo_dia; }
    public void setTipo_dia(String tipo_dia) { this.tipo_dia = tipo_dia; }

    public String getFranja_horaria() { return franja_horaria; }
    public void setFranja_horaria(String franja_horaria) { this.franja_horaria = franja_horaria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
