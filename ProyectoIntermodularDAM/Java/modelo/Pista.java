package modelo;

public class Pista {
    private int id_pista;
    private String nombre;
    private String tipo;
    private String estado;

    public Pista() {}

    public Pista(int id_pista, String nombre, String tipo, String estado) {
        this.id_pista = id_pista;
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getId_pista() { return id_pista; }
    public void setId_pista(int id_pista) { this.id_pista = id_pista; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
