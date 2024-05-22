package co.uceva.edu.base.models;

public class SOE {
    private String id,avionid, cantidad, fechaRevis;


    public SOE(String id,String avionid, String cantidad, String fechaRevis ) {
        this.id = id;
        this.avionid = avionid;
        this.cantidad = cantidad;
        this.fechaRevis = fechaRevis;

    }

    public SOE() {
        this.id = "";
        this.avionid = "";
        this.cantidad = "";
        this.fechaRevis = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvionid() {
        return avionid;
    }

    public void setAvionid(String avionid) {
        this.avionid = avionid;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaRevis() {
        return fechaRevis;
    }

    public void setFechaRevis(String fechaRevis) {
        this.fechaRevis = fechaRevis;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

