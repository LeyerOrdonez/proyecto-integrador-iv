package co.uceva.edu.base.beans;
import co.uceva.edu.base.models.Empleado;
import co.uceva.edu.base.models.Pack;
import co.uceva.edu.base.services.EmpleadoService;
import co.uceva.edu.base.services.PackService;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped

public class PackBean implements Serializable{

    PackService paqueteService;
    Pack paquete;

    int idEmpleado=0;

    public PackBean() {
        paqueteService= new PackService();
        paquete = new Pack();
    }

    public List<Pack> listar() {
        return paqueteService.listar();
    }

    public List<Pack> listarXTipo(String tipo){
        return paqueteService.consultarXTipo(tipo);
    }


    public String irCrear() {
        paquete=new Pack();
        return "crearpack.xhtml?faces-redirect=true";
    }
    public String irActualizar(String id){
        this.paquete = paqueteService.consultar(id).get(0);
        return "actualizar-paquete.xhtml?faces-redirect=true";
    }
    public String crear(int id){
        idEmpleado = id;
        conocerPrecio();
        if(paqueteService.crear(this.paquete)){
            System.out.println("Creado Exitosamente");
            paquete=new Pack();
            return "crearpack.xhtml?faces-redirect=true";

        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Guardando","Error Guardando");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }

    }
    public String actualizarPack(){
        if(paqueteService.actualizarPack(this.paquete)){
            System.out.println("actualizado Exitosamente");
            return "listar-paquetes.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Actualizando","no actualizado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public String eliminarPack(String identificacion){
        if(paqueteService.eliminarPack(identificacion)){
            System.out.println("Eliminado Exitosamente");
            return "listar-paquetes.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Eliminando","no eliminado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public Pack getPaquete() {
        return paquete;
    }

    public void setPaquete(Pack paquete) {
        this.paquete = paquete;
    }


    public String crearID(){
       String conteoVisitas[] = paquete.getPuntosVisita().split("_");

       String codigo = conteoVisitas.length + "_" + paquete.getDias() +"_"
               + paquete.getNoches() +"_"+ paquete.getComidas() + "_" + idEmpleado;

        paquete.setId(codigo);
        return codigo;
    }

    public String conocerPrecio(){
       String identify =  crearID();
       String partes[] = identify.split("_");
       int xVisitas= Integer.parseInt(partes[0]);
       int yDias=Integer.parseInt(partes[1]);
       int  zNoches=Integer.parseInt(partes[2]);
       int  aDesayunos=Integer.parseInt(partes[3]);
       int x=0,y=0,z=0,a=0,precio=0;

        switch (xVisitas) {
            case 1: x=1;
                break;
            case 2: x=2;
                break;
            case 3: x=3;
                break;
            case 4: x=4;
                break;
            case 5: x=5;
                break;
            case 6: x=6;
                break;
            case 7: x=7;
                break;
            case 8: x=8;
                break;
            case 9: x=9;
                break;
            case 10: x=10;
                break;
            case 11: x=11;
                break;

        }

        switch (yDias) {
            case 1: y=1;
                break;
            case 2: y=2;
                break;
            case 3: y=3;
                break;
            case 4: y=4;
                break;
            case 5: y=5;
                break;
            case 6: y=6;
                break;
            case 7: y=7;
                break;
            case 8: y=8;
                break;
            case 9: y=9;
                break;
            case 10: y=10;
                break;
            case 11: y=11;
                break;
        }

        switch (zNoches) {
            case 1: z=1;
                break;
            case 2: z=2;
                break;
            case 3: z=3;
                break;
            case 4: z=4;
                break;
            case 5: z=5;
                break;
            case 6: z=6;
                break;
            case 7: z=7;
                break;
            case 8: z=8;
                break;
            case 9: z=9;
                break;
            case 10: z=10;
                break;
            case 11: z=11;
                break;
        }

        switch (aDesayunos) {
            case 0: a=0;
                break;
            case 1: a=1;
                break;
        }

        Date diaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        paquete.setFecha( formato.format(diaActual) );
        precio= x*50000 + y*100000 + z*120000 + a*20000;
        paquete.setPrecio(precio);
        return "crearpack.xhtml?faces-redirect=true";

    }

    public String limpiar(){
        paquete=new Pack();
        return "crearpack.xhtml?faces-redirect=true";
    }

    public void generarCSV(){
        paqueteService.generarCSV();
    }

}
