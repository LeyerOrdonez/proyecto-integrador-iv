package co.uceva.edu.base.models;

public class Coment{
    private String nombreComentador,correoComentador,comentario,calificacion;

    public Coment() {
        this.nombreComentador="";
        this.correoComentador="";
        this.comentario="";
        this.calificacion="";
    }

    public String getNombreComentador() {
        return nombreComentador;
    }

    public void setNombreComentador(String nombreComentador) {
        this.nombreComentador = nombreComentador;
    }

    public String getCorreoComentador() {
        return correoComentador;
    }

    public void setCorreoComentador(String correoComentador) {
        this.correoComentador = correoComentador;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}
