package co.uceva.edu.base.beans;

import co.uceva.edu.base.models.Coment;
import co.uceva.edu.base.models.Pack;
import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.services.UsuarioService;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.swing.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Named
@SessionScoped

public class UsuarioBean implements Serializable {
    private String nameCom="",correoCom="",com="",exitoCom;
    private int calificacionCom=5;
    private UsuarioService usuarioService;
    private Usuario usuario;
    private String warningMessage="";
    private Usuario usuarioAutentico;
    protected float interes = (float) 0.19;
    protected float inflacion = (float) 0.12;


    public UsuarioBean (){
        usuarioService  = new UsuarioService();
        usuario= usuarioAutentico = new Usuario();
        warningMessage="";
    }

    public String autenticar() {
        System.out.println("autenticando...");
        usuarioAutentico =  usuarioService.autenticar(usuario.getCorreo(),usuario.getId());
        System.out.println(usuarioAutentico.getId() +" revisado " +usuarioAutentico.getNombre());

        if("".equals(usuarioAutentico.getNombre()) || 0==usuarioAutentico.getTelefono()){
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Autenticando","Error en la autenticacion");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            System.out.println("no se autentica");
            warningMessage="hubo error en la autenticacion ";
            return "";

        }else{
            FacesMessage mensaje= new FacesMessage(FacesMessage.SEVERITY_WARN,"Autenticado","Atenticado correctmente");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            //System.out.println("Autenticado Exitosamente, bienvenido "+usuario.getNombre());
            System.out.println("si se autentica.");
            return "index.xhtml?faces-redirect=true";
           // return "listado-empleados.xhtml?faces-redirect=true";  TODO porque no funciona el redireccionamiento ni los faces messages?
        }
    }

    public String desautenticar(){
        usuario=new Usuario();
        usuarioAutentico=new Usuario();
        warningMessage="";
        return "index.xhtml?faces-redirect=true";

    }

    public String comprarPack(String idPack){
        if(usuarioAutentico.getTelefono() == 0 || "".equals(usuarioAutentico.getId())){
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Autenticando","Error en la autenticacion");
            System.out.println("error en la compra usuario no identificado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "acceder.xhtml?faces-redirect=true";
        }else{
            usuarioAutentico.setIdPack(idPack);
            usuarioService.nuevaCompra(usuarioAutentico);
            System.out.println("supuestamente lo crea comprado");
            System.out.println(usuarioAutentico.toString());

            return"";
        }

    }

    public List<Usuario> listarUsuario(){
        return usuarioService.listarUsuario();
    }

    public String irCrear(){
        usuario=new Usuario();
     return "crear-usuario.xhtml?faces-redirect=true";
    }

    public String irActualizarUsuario(String id){
        this.usuario = usuarioService.consultarUsuario(id).get(0);
        return "actualizar-usuario.xhtml?faces-redirect=true";
    }

    public String crearUsuario(){
        if(usuarioService.crearUsuario(this.usuario)){
            System.out.println("Creado Exitosamente");
            return "listar-usuarios.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Guardando","Error Guardando, e id posiblemente ya existe");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public String crearUsuario2(){
        if(usuarioService.crearUsuario(this.usuario)){
            System.out.println("Creado Exitosamente");
            usuarioAutentico= usuario;
            return "index.xhtml?faces-redirect=true";

        }else{
            usuarioAutentico=new Usuario();
            usuario=new Usuario();
            warningMessage="la identificacion se encuentra registrada actualmente";
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Guardando","Error Guardando, el id posiblemente ya existe");
            FacesContext.getCurrentInstance().addMessage("mensajeacceso",mensaje);
            return "";
        }
    }

    public String actualizarUsuario(){
        if(usuarioService.actualizarUsuario(this.usuario)){
            System.out.println("actualizado Exitosamente");

            return "listar-usuarios.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Actualizando","no actualizado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public String eliminarUsuario(String identificacion){
        if(usuarioService.eliminarUsuario(identificacion)){
            System.out.println("Eliminado Exitosamente");
            return "listar-usuarios.xhtml?faces-redirect=true";
        }else{
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Eliminando","no eliminado");
            FacesContext.getCurrentInstance().addMessage("",mensaje);
            return "";
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }

    public Usuario getUsuarioAutentico() {
        return usuarioAutentico;
    }

    public void setUsuarioAutentico(Usuario usuarioAutentico) {
        this.usuarioAutentico = usuarioAutentico;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public String  amortizacion(long precio,int periodo,String idPack){
        NumberFormat formato =NumberFormat.getCurrencyInstance(new Locale("es","ES"));
        DecimalFormat cuotasF = new DecimalFormat("#.00");
        int  n=periodo*-1;
        float interesReal = ((1+ interes)/(1+inflacion)) -1;
        double elevacion = Math.pow((1+interesReal),n);
        double anualidad = precio*((interesReal/(1-(   elevacion   ))));

        System.out.println("\nPRECIO DEL PAQUETE: "+ formato.format(precio) + "\nCUOTAS MENSUALES: $ " + cuotasF.format(anualidad)+ "\nPERIODO DE PAGO: " +"DURANTE "+periodo +" meses");

        usuarioAutentico.setCuota(Double.parseDouble(cuotasF.format(anualidad)));
        usuarioAutentico.setConCredito("SI-"+periodo +" meses");
        return comprarPack(idPack);
    }

    public void generarCSV(){
        usuarioService.generarCSV();
    }

    public void comentar(){
        System.out.println(nameCom+calificacionCom+correoCom);
        String comentarios[]={nameCom,correoCom,com};
        exitoCom = usuarioService.comentar(comentarios,calificacionCom);
        nameCom=correoCom=com="";
        calificacionCom=5;
    }

    public List<Coment> listarComentarios(){
       return usuarioService.listarComentarios();
    }

    public String getNameCom() {
        return nameCom;
    }

    public void setNameCom(String nameCom) {
        this.nameCom = nameCom;
    }

    public String getCorreoCom() {
        return correoCom;
    }

    public void setCorreoCom(String correoCom) {
        this.correoCom = correoCom;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public int getCalificacionCom() {
        return calificacionCom;
    }

    public void setCalificacionCom(int calificacionCom) {
        this.calificacionCom = calificacionCom;
    }

    public String getExitoCom() {
        return exitoCom;
    }

    public void setExitoCom(String exitoCom) {
        this.exitoCom = exitoCom;
    }
}
