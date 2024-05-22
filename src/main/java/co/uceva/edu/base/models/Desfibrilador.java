package co.uceva.edu.base.models;

public class Desfibrilador {
    private String id,hotel, descripcion ,posRelativa,coordenadas,fechaRevis, estado;


    public Desfibrilador(String id,String hotel,String descripcion , String posRelativa, String coordenadas, String fechaRevis, String estado ) {
        this.id = id;
        this.hotel = hotel;
        this.descripcion= descripcion;
        this.posRelativa = posRelativa;
        this.coordenadas = coordenadas;
        this.fechaRevis = fechaRevis;
        this.estado = estado;

    }

    public Desfibrilador() {
        this.id = "";
        this.hotel = "";
        this.descripcion= "";
        this.posRelativa = "";
        this.coordenadas = "";
        this.fechaRevis = "";
        this.estado = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPosRelativa() {
        return posRelativa;
    }

    public void setPosRelativa(String posRelativa) {
        this.posRelativa = posRelativa;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getFechaRevis() {
        return fechaRevis;
    }

    public void setFechaRevis(String fechaRevis) {
        this.fechaRevis = fechaRevis;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


