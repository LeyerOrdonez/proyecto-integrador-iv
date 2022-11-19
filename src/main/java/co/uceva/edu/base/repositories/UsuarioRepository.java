package co.uceva.edu.base.repositories;
import co.uceva.edu.base.models.Coment;
import co.uceva.edu.base.models.Empleado;
import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.util.ConexionBaseDatos;

import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    public List<Usuario> listar(){
        List<Usuario> listadousuario = new ArrayList<>();
        Connection con =null;
        ResultSet rs=null;
        Statement st =null;
        try /*(con = ConexionBaseDatos.getConnection();)*/{                 //aqui ponemos las clases que pueden ser cerradas
            con = ConexionBaseDatos.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM usuarios");
            while(rs.next()){
                Usuario emp = new Usuario();
                emp.setId(rs.getString("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setCorreo(rs.getString("correo"));
                emp.setNacimiento(rs.getString("fecha_nacimiento"));
                emp.setTelefono(rs.getLong("telefono"));
                emp.setOtroNombre(rs.getString("otro_nombre"));
                emp.setOtroTelefono(rs.getLong("otro_telefono"));
                emp.setConCredito(rs.getString("credito"));
                emp.setCuota(rs.getDouble("cuota"));
                listadousuario.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listadousuario;
    }//fin de la funcion de listado


    public boolean crear(Usuario usuario){
        Connection con=null;
        PreparedStatement pst =null;
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("INSERT INTO usuarios (id,nombre,correo,fecha_nacimiento,telefono,otro_nombre,otro_telefono,id_paquete,credito,cuota) VALUES(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,usuario.getId());
            pst.setString(2,usuario.getNombre());
            pst.setString(3,usuario.getCorreo());
            pst.setString(4, usuario.getNacimiento());
            pst.setLong(5,usuario.getTelefono());
            pst.setString(6, usuario.getOtroNombre());
            pst.setLong(7,usuario.getOtroTelefono());
            pst.setString(8, usuario.getIdPack());
            pst.setString(9, usuario.getConCredito());
            pst.setDouble(10,usuario.getCuota());
            pst.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }
        return true;

    }// fin de la funcion crear



    public List<Usuario> consulta(String id){
        Connection con=null;
        PreparedStatement pst =null;
        ResultSet rs =null;
        List<Usuario> listadousuario = new ArrayList<>();
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("SELECT * FROM usuarios WHERE id=?");
            pst.setString(1,id);
            rs  = pst.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getString("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setNacimiento(rs.getString("fecha_nacimiento"));
                usuario.setTelefono(rs.getLong("telefono"));
                usuario.setOtroNombre(rs.getString("otro_nombre"));
                usuario.setOtroTelefono(rs.getLong("otro_telefono"));
                listadousuario.add(usuario);
            }

        }catch (Exception e){
            e.printStackTrace();
            return listadousuario;
        }finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                return listadousuario;
            }

        }
        return listadousuario;
    }



    public boolean actualizarUsuario(Usuario usuario){
        Connection con =null;
        PreparedStatement st =null;

        try {
            con = ConexionBaseDatos.getConnection();
            st = con.prepareStatement("UPDATE usuarios SET nombre=? , correo=?, fecha_nacimiento=?, telefono=?, otro_nombre=?, otro_telefono=?, credito=?, cuota=? WHERE id=?");
            st.setString(1,usuario.getNombre());
            st.setString(2,usuario.getCorreo());
            st.setString(3, usuario.getNacimiento());
            st.setLong(4,usuario.getTelefono());
            st.setString(5, usuario.getOtroNombre());
            st.setLong(6,usuario.getOtroTelefono());
            st.setString(7, usuario.getConCredito());
            st.setDouble(8,usuario.getCuota());
            st.setString(9,usuario.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }//fin de la funcion de actualizado

    public boolean eliminarUsuario(String identify){
        Connection con =null;
        PreparedStatement st =null;
        try {
            con = ConexionBaseDatos.getConnection();
            st = con.prepareStatement("DELETE FROM usuarios WHERE id=?");
            st.setString(1, identify);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }//fin de la funcion de borrado




    public Usuario autenticarUsuario (String correo,String id){
        Connection con=null;
        PreparedStatement pst =null;
        ResultSet rs =null;
        Usuario usuario =new Usuario();
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("SELECT * FROM usuarios WHERE correo=? AND id=?");
            pst.setString(1,correo);
            pst.setString(2,id);
            rs  = pst.executeQuery();

            while(rs.next()){
                usuario = new Usuario();
                usuario.setId(rs.getString("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setNacimiento(rs.getString("fecha_nacimiento"));
                usuario.setTelefono(rs.getLong("telefono"));
                usuario.setOtroNombre(rs.getString("otro_nombre"));
                usuario.setOtroTelefono(rs.getLong("otro_telefono"));
            }


        }catch (Exception e){
                e.printStackTrace();


        }finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return usuario;
    }//fin de la funcion de autenticacion


    public boolean nuevaCompra(Usuario usuario){
        Connection con=null;
        PreparedStatement pst =null;
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("INSERT INTO usuarios (id,nombre,correo,fecha_nacimiento,telefono,otro_nombre,otro_telefono,id_paquete,credito,cuota) VALUES(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,usuario.getId());
            pst.setString(2,usuario.getNombre());
            pst.setString(3,usuario.getCorreo());
            pst.setString(4, usuario.getNacimiento());
            pst.setLong(5,usuario.getTelefono());
            pst.setString(6, usuario.getOtroNombre());
            pst.setLong(7,usuario.getOtroTelefono());
            pst.setString(8, usuario.getIdPack());
            pst.setString(9, usuario.getConCredito());
            pst.setDouble(10,usuario.getCuota());

            pst.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }
        return true;

    }// fin de la funcion crear

    public boolean comentar(String comentario[], int calificacion){
        Connection con=null;
        PreparedStatement pst =null;

        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("INSERT INTO comentarios (nombre_com,correo_com,com,calificacion) VALUES(?,?,?,?)");
            pst.setString(1,comentario[0]);
            pst.setString(2,comentario[1]);
            pst.setString(3,comentario[2]);
            pst.setInt(4,calificacion);
            pst.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }
        return true;
    }

    public List<Coment> listarComentarios(){
        List<Coment> listadoComentarios = new ArrayList<>();

        Connection con =null;
        ResultSet rs=null;
        Statement st =null;
        try{
            con = ConexionBaseDatos.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM comentarios");

            while(rs.next()){
                Coment comentario=new Coment();
                comentario.setNombreComentador(rs.getString("nombre_com"));
                comentario.setCorreoComentador(rs.getString("correo_com"));
                comentario.setComentario(rs.getString("com"));
                int iv= (rs.getInt("calificacion"));
                switch (iv){
                    case 1: comentario.setCalificacion("horrible");
                        break;
                    case 2:comentario.setCalificacion("malo");
                        break;
                    case 3:comentario.setCalificacion("regular");
                        break;
                    case 4:comentario.setCalificacion("BUENO");
                        break;
                    case 5:comentario.setCalificacion("EXCELENTE");
                        break;
                }
                listadoComentarios.add(comentario);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listadoComentarios;
    }//fin de la funcion de listado

}


