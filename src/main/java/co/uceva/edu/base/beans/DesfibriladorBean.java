package co.uceva.edu.base.beans;
import co.uceva.edu.base.models.Desfibrilador;
import co.uceva.edu.base.services.DesfibriladorService;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped

public class DesfibriladorBean implements Serializable {
    private DesfibriladorService defService;
    private Desfibrilador def;



    public DesfibriladorBean (){
        defService  = new DesfibriladorService();
    }



    public List<Desfibrilador> listarDesfibrilador(){
        return defService.listarDesfibrilador();
    }

    public String irCrear(){
        def = new Desfibrilador();
        return "crear-desfibrilador.xhtml?faces-redirect=true";
    }

    public String irActualizarDesfibrilador(String id){
        this.def = defService.consultarDesfibrilador(id).get(0);
        return "actualizar-desfibrilador.xhtml?faces-redirect=true";
    }

    public String crearDesfibrilador(){
        if(defService.crearDesfibrilador(this.def)){
            System.out.println("Creado Exitosamente");
            return "listar-desfibriladores-adm.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Guardando","Error Guardando, el id posiblemente ya existe");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public String actualizarDesfibrilador(){
        if(defService.actualizarDesfibrilador(this.def)){
            System.out.println("actualizado Exitosamente");

            return "listar-desfibriladores-adm.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Actualizando","no actualizado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public String eliminarDesfibrilador(String identificacion){
        if(defService.eliminarDesfibrilador(identificacion)){
            System.out.println("Eliminado Exitosamente");
            return "listar-desfibriladores-adm.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Eliminando","no eliminado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }


    public void generarCSV(){
        defService.generarCSV();
    }

    public Desfibrilador getDesfibrilador() {
        return def;
    }
}
