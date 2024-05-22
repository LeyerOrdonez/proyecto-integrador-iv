package co.uceva.edu.base.repositories;
import co.uceva.edu.base.models.Coment;
import co.uceva.edu.base.models.SOE;
import co.uceva.edu.base.models.Usuario;
import co.uceva.edu.base.util.ConexionBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SOERepository {



    public List<SOE> listar(){
        List<SOE> listadoSOE = new ArrayList<>();
        Connection con =null;
        ResultSet rs=null;
        Statement st =null;
        try /*(con = ConexionBaseDatos.getConnection();)*/{                 //aqui ponemos las clases que pueden ser cerradas
            con = ConexionBaseDatos.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM soe");
            while(rs.next()){
                SOE soe = new SOE();
                soe.setId(rs.getString("id"));
                soe.setAvionid(rs.getString("avionid"));
                soe.setCantidad(rs.getString("cantidad"));
                soe.setFechaRevis (rs.getString("fecha_revision"));
                listadoSOE.add(soe);


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
        return listadoSOE;
    }//fin de la funcion de listado


    public boolean crear(SOE soe){
        Connection con=null;
        PreparedStatement pst =null;
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("INSERT INTO soe (id, avionid, cantidad, fecha_revision) VALUES(?,?,?,?)");
            pst.setString(1,soe.getId());
            pst.setString(2,soe.getAvionid());
            pst.setString(3,soe.getCantidad());
            pst.setString(4, soe.getFechaRevis());
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



    public List<SOE> consulta(String id){
        Connection con=null;
        PreparedStatement pst =null;
        ResultSet rs =null;
        List<SOE> listadoSOE = new ArrayList<>();
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("SELECT * FROM soe WHERE id=?");
            pst.setString(1,id);
            rs  = pst.executeQuery();
            while(rs.next()){
                SOE soe = new SOE();
                soe.setId(rs.getString("id"));
                soe.setAvionid(rs.getString("avionid"));
                soe.setCantidad(rs.getString("cantidad"));
                soe.setFechaRevis(rs.getString("fecha_revision"));
                listadoSOE.add(soe);
            }

        }catch (Exception e){
            e.printStackTrace();
            return listadoSOE;
        }finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                return listadoSOE;
            }

        }
        return listadoSOE;
    }



    public boolean actualizarSOE(SOE soe){
        Connection con =null;
        PreparedStatement st =null;

        try {
            con = ConexionBaseDatos.getConnection();
            st = con.prepareStatement("UPDATE soe SET avionid=? , cantidad=?, fecha_revision=? WHERE id=?");
            st.setString(1,soe.getAvionid());
            st.setString(2,soe.getCantidad());
            st.setString(3, soe.getFechaRevis());
            st.setString(4,soe.getId());
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

    public boolean eliminarSOE(String identify){
        Connection con =null;
        PreparedStatement st =null;
        try {
            con = ConexionBaseDatos.getConnection();
            st = con.prepareStatement("DELETE FROM soe WHERE id=?");
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
}



