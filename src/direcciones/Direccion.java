package direcciones;

public class Direccion {
    private int id;
    private String calle;
    private String avenida;
    private String referencia;
    private String colonia;
    private String estado;

    // Constructor completo
    public Direccion(int id, String calle, String avenida, String referencia, String colonia, String estado) {
        this.id = id;
        this.calle = calle;
        this.avenida = avenida;
        this.referencia = referencia;
        this.colonia = colonia;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public String getAvenida() { return avenida; }
    public void setAvenida(String avenida) { this.avenida = avenida; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}