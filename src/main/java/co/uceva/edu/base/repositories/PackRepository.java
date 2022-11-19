package co.uceva.edu.base.repositories;
import co.uceva.edu.base.models.Empleado;
import co.uceva.edu.base.models.Pack;
import co.uceva.edu.base.util.ConexionBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackRepository {
    public List<Pack> listar(){
        List<Pack> listadoPaquetes = new ArrayList<>();
        Connection con =null;
        ResultSet rs=null;
        Statement st =null;
        try /*(con = ConexionBaseDatos.getConnection();)*/{                 //aqui ponemos las clases que pueden ser cerradas
            con = ConexionBaseDatos.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM paquetes");
            while(rs.next()){
                Pack pk = new Pack();
                pk.setId(rs.getString("id"));
                pk.setDescripcion(rs.getString("descripcion"));
                pk.setPuntosVisita(rs.getString("puntos_visita"));
                if(rs.getInt(("comidas"))==1) {
                    pk.setsN("SI");
                }else {pk.setsN("NO");}

                pk.setComidas(rs.getInt(("comidas")));
                pk.setDias(rs.getInt("dias"));
                pk.setNoches(rs.getInt("noches"));
                pk.setPrecio(rs.getLong("precio"));
                pk.setTipo(rs.getString("tipo"));
                pk.setFecha(rs.getString("fecha"));
                listadoPaquetes.add(pk);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            try {
                rs.close();
                con.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listadoPaquetes;
    }//fin de la funcion de listado


    public boolean crear(Pack paquete){
        Connection con=null;
        PreparedStatement pst =null;
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("INSERT INTO paquetes (id,descripcion,puntos_visita,comidas,dias,noches,precio,tipo,fecha) VALUES(?,?,?,?,?,?,?,?,?)");
            pst.setString(1,paquete.getId());
            pst.setString(2,paquete.getDescripcion());
            pst.setString(3,paquete.getPuntosVisita());
            pst.setInt(4,paquete.getComidas());
            pst.setInt(5,paquete.getDias());
            pst.setInt(6,paquete.getNoches());
            pst.setLong(7,paquete.getPrecio());
            pst.setString(8,paquete.getTipo());
            pst.setString(9,paquete.getFecha());
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


    public List<Pack> consulta(String id){
        Connection con=null;
        PreparedStatement pst =null;
        ResultSet rs =null;
        List<Pack> listadoPaquetes = new ArrayList<>();
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("SELECT * FROM paquetes WHERE id=?");
            pst.setString(1,id);
            rs  = pst.executeQuery();
            while(rs.next()){
                Pack pk = new Pack();
                pk.setId(rs.getString("id"));
                pk.setDescripcion(rs.getString("descripcion"));
                pk.setPuntosVisita(rs.getString("puntos_visita"));
                pk.setComidas(rs.getInt("comidas"));
                pk.setDias(rs.getInt("dias"));
                pk.setNoches(rs.getInt("noches"));
                pk.setPrecio(rs.getLong("precio"));
                pk.setTipo(rs.getString("tipo"));
                pk.setFecha(rs.getString("fecha"));
                listadoPaquetes.add(pk);
            }

        }catch (Exception e){
            e.printStackTrace();
            return listadoPaquetes;
        }finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                return listadoPaquetes;
            }

        }
        return listadoPaquetes;
    }// fin de la funcion consultar

    public boolean eliminarPack(String identify){
        Connection con =null;
        PreparedStatement st =null;
        try {
            con = ConexionBaseDatos.getConnection();
            st = con.prepareStatement("DELETE FROM paquetes WHERE id=?");
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

    public boolean actualizarPack(Pack paquete){
        Connection con =null;
        PreparedStatement st =null;
        try {
            con = ConexionBaseDatos.getConnection();
            st = con.prepareStatement("UPDATE paquetes SET descripcion=?, puntos_visita=?, comidas=?, dias=?, noches=?, precio=?, tipo=?, fecha=? WHERE id=?");
            st.setString(1,paquete.getDescripcion());
            st.setString(2,paquete.getPuntosVisita());
            st.setInt(3,paquete.getComidas());
            st.setInt(4,paquete.getDias());
            st.setInt(5,paquete.getNoches());
            st.setLong(6,paquete.getPrecio());
            st.setString(7,paquete.getTipo());
            st.setString(8,paquete.getFecha());
            st.setString(9,paquete.getId());
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

    public List<Pack> consultaXTipo(String tipo){
        Connection con=null;
        PreparedStatement pst =null;
        ResultSet rs =null;
        List<Pack> listadoPaquetes = new ArrayList<>();
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("SELECT * FROM paquetes WHERE tipo=?");
            pst.setString(1,tipo);
            rs  = pst.executeQuery();
            while(rs.next()){
                Pack pk = new Pack();
                pk.setId(rs.getString("id"));
                pk.setDescripcion(rs.getString("descripcion"));
                pk.setPuntosVisita(rs.getString("puntos_visita"));
                if(rs.getInt(("comidas"))==1) {
                    pk.setsN("SI");
                }else {pk.setsN("NO");}
                pk.setDias(rs.getInt("dias"));
                pk.setNoches(rs.getInt("noches"));
                pk.setPrecio(rs.getLong("precio"));
                listadoPaquetes.add(pk);
            }

        }catch (Exception e){
            e.printStackTrace();
            return listadoPaquetes;
        }finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                return listadoPaquetes;
            }

        }
        return listadoPaquetes;
    }// fin de la funcion consultar

}
