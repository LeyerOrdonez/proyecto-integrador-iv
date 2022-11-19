package co.uceva.edu.base.services;

import co.uceva.edu.base.models.Coment;
import co.uceva.edu.base.models.Empleado;
import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.repositories.UsuarioRepository;

import java.io.FileWriter;
import java.util.List;

public class UsuarioService {
    UsuarioRepository  usuarioRepository;

    public UsuarioService() {
        usuarioRepository = new UsuarioRepository();
    }

    public Usuario autenticar(String correo, String password){
        return usuarioRepository.autenticarUsuario(correo,password);
    }

    public List<Usuario> listarUsuario (){
       return usuarioRepository.listar();
    }

    public boolean crearUsuario(Usuario usuario){
        if(usuarioRepository.consulta(usuario.getId()).size() > 0){
            return false;
        }else{
            return usuarioRepository.crear(usuario);
        }
    }// TODO hacer validacion por id,correo


    public List<Usuario> consultarUsuario (String id ){
        return usuarioRepository.consulta(id);
    }

    public void nuevaCompra(Usuario usuarioNuevo){
        usuarioRepository.nuevaCompra(usuarioNuevo);
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioRepository.actualizarUsuario(usuario);
    }

    public boolean eliminarUsuario(String identificacion) {
        return usuarioRepository.eliminarUsuario(identificacion);
    }

    public void generarCSV(){
        List<Usuario> listadousuario  = usuarioRepository.listar();

        FileWriter fw = null;
        boolean error = false;
        try {
            fw = new FileWriter("z_clientes.csv",false);
        } catch (Exception e) {
            error = true;
            System.out.println("Error al crear el archivo");
        }

        if(!error){
            for (int i = 0; i < listadousuario.size(); i++) {

                try {
                    fw.write(listadousuario.get(i).getId() + ";" + listadousuario.get(i).getNombre() + ";" +
                            listadousuario.get(i).getCorreo() + ";" + listadousuario.get(i).getNacimiento()+ ";" +
                            listadousuario.get(i).getTelefono() + ";" + listadousuario.get(i).getIdPack()+";"+
                            listadousuario.get(i).getOtroNombre() + ";" + listadousuario.get(i).getOtroTelefono()+ ";"+
                            listadousuario.get(i).getConCredito() + ";" + listadousuario.get(i).getCuota()+ ";" + "\r\n");
                } catch (Exception e) {
                    System.out.println("Error al guardar en el archivo");
                    break;
                }
            }//fin del for

            try {
                fw.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el archivo");
            }
        }

    }

    public String comentar(String comentario[],int calificacionCom){
        if(usuarioRepository.comentar(comentario,calificacionCom)) {
            return "comentario realizado con exito!!";
        }else{
            return "comentario no relizado :(";
        }
    }

    public List<Coment> listarComentarios(){
      return usuarioRepository.listarComentarios();
    }
}
