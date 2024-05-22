package co.uceva.edu.base.beans;
import co.uceva.edu.base.models.SOE;
import co.uceva.edu.base.services.SOEService;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped

public class SOEBean implements Serializable {
    private SOEService soeService;
    private SOE soe;
    private String warningMessage="";



    public SOEBean (){
        soeService  = new SOEService();
        warningMessage="";

    }



    public List<SOE> listarSOE(){


        return soeService.listarSOE();
    }

    public String irCrear(){
        soe = new SOE();
        return "crear-SOE.xhtml?faces-redirect=true";
    }

    public String irActualizarSOE(String id){
        this.soe = soeService.consultarSOE(id).get(0);
        return "actualizar-soe.xhtml?faces-redirect=true";
    }

    public String crearSOE(){
        if(soeService.crearSOE(this.soe)){
            System.out.println("Creado Exitosamente");
            return "listar-SOE-adm.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Guardando","Error Guardando, el id posiblemente ya existe");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public String actualizarSOE(){
        if(soeService.actualizarSOE(this.soe)){
            System.out.println("actualizado Exitosamente");

            return "listar-SOE-adm.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Actualizando","no actualizado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public String eliminarSOE(String identificacion){
        if(soeService.eliminarSOE(identificacion)){
            System.out.println("Eliminado Exitosamente");
            return "listar-SOE-adm.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Eliminando","no eliminado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }


    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }


    public void generarCSV(){
        soeService.generarCSV();
    }

    public SOE getSoe() {
        return soe;
    }
}