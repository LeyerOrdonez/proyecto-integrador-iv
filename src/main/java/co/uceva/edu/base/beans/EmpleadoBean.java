package co.uceva.edu.base.beans;
import co.uceva.edu.base.models.Empleado;
import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.services.EmpleadoService;


import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped

public class EmpleadoBean implements Serializable {
    EmpleadoService empleadoService;
    Empleado empleado,empleadoAutentico;

    public EmpleadoBean() {
        empleadoService = new EmpleadoService();
        empleado = new Empleado();
        empleadoAutentico =new Empleado();
    }

    public String autenticar() {
        System.out.println("autenticando...");
        empleadoAutentico =  empleadoService.autenticar(empleado.getCorreo(),empleado.getPassword());
        System.out.println(empleadoAutentico.getId() +" revisado " +empleadoAutentico.getNombre());

        if("".equals(empleadoAutentico.getNombre()) || 0==empleadoAutentico.getId()){
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Autenticando","Error en la autenticacion, verifique que los datos ingresados sean correctos");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            System.out.println("no se autentica");
            return "";

        }else{
            FacesMessage mensaje= new FacesMessage(FacesMessage.SEVERITY_WARN,"Autenticado","Atenticado correctmente");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            //System.out.println("Autenticado Exitosamente, bienvenido "+usuario.getNombre());
            System.out.println("si se autentica.");
            return "listar-paquetes.xhtml?faces-redirect=true";
            // return "listado-empleados.xhtml?faces-redirect=true";  TODO porque no funciona el redireccionamiento ni los faces messages?
        }
    }

    public String desautenticar(){
        empleado=new Empleado();
        empleadoAutentico =new Empleado();
        return "login.xhtml?faces-redirect=true";
    }

    public List<Empleado> listar() {
        return empleadoService.listar();
    }

    public String irCrear() {
        empleado = new Empleado();
        return "crear-empleado.xhtml?faces-redirect=true";
    }

    public String irActualizar(int id){
        this.empleado=empleadoService.consultar(id).get(0);
        return "actualizar-empleado.xhtml?faces-redirect=true";
    }

    public String crear(){
        if(empleadoService.crear(this.empleado)){
            System.out.println("Creado Exitosamente");
            empleado=new Empleado();
            return "listar-empleados.xhtml?faces-redirect=true";

        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Guardando","Error Guardando");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }
    public String actualizarEmpleado(){
        if(empleadoService.actualizarEmpleado(this.empleado)){
            System.out.println("actualizado Exitosamente");
            return "listar-empleados.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Actualizando","no actualizado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public String eliminarEmpleado(int identificacion){
        if(empleadoService.eliminarEmpleado(identificacion)){
            System.out.println("Eliminado Exitosamente");
            return "listar-empleados.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Eliminando","no eliminado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleadoAutentico() {
        return empleadoAutentico;
    }

    public void setEmpleadoAutentico(Empleado empleadoAutentico) {
        this.empleadoAutentico = empleadoAutentico;
    }

public void generarCSV(){
        empleadoService.generarCSV();
}

}
