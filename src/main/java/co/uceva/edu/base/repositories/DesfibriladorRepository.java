package co.uceva.edu.base.repositories;
import co.uceva.edu.base.models.Desfibrilador;
import co.uceva.edu.base.util.ConexionBaseDatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DesfibriladorRepository {



    public List<Desfibrilador> listar(){
        List<Desfibrilador> listado = new ArrayList<>();
        Connection con =null;
        ResultSet rs=null;
        Statement st =null;
        try /*(con = ConexionBaseDatos.getConnection();)*/{                 //aqui ponemos las clases que pueden ser cerradas
            con = ConexionBaseDatos.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM desfibriladores");
            while(rs.next()){
                Desfibrilador def = new Desfibrilador();
                def.setId(rs.getString("id"));
                def.setHotel(rs.getString("hotel"));
                def.setDescripcion(rs.getString("descripcion"));
                def.setPosRelativa (rs.getString("pos_relativa"));
                def.setCoordenadas(rs.getString("coordenadas"));
                def.setFechaRevis(rs.getString("fecha_revis"));
                def.setEstado (rs.getString("estado"));
                listado.add(def);


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
        return listado;
    }//fin de la funcion de listado


    public boolean crear(Desfibrilador def){
        Connection con=null;
        PreparedStatement pst =null;
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("INSERT INTO desfibriladores (id,hotel, descripcion, pos_relativa, coordenadas, fecha_revis, estado) VALUES(?,?,?,?,?,?,?)");
            pst.setString(1,def.getId());
            pst.setString(2,def.getHotel());
            pst.setString(3,def.getDescripcion());
            pst.setString(4, def.getPosRelativa());
            pst.setString(5,def.getCoordenadas());
            pst.setString(6,def.getFechaRevis());
            pst.setString(7, def.getEstado());
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



    public List<Desfibrilador> consulta(String id){
        Connection con=null;
        PreparedStatement pst =null;
        ResultSet rs =null;
        List<Desfibrilador> listado = new ArrayList<>();
        try{
            con = ConexionBaseDatos.getConnection();
            pst = con.prepareStatement("SELECT * FROM desfibriladores WHERE id=?");
            pst.setString(1,id);
            rs  = pst.executeQuery();
            while(rs.next()){
                Desfibrilador def = new Desfibrilador();
                def.setId(rs.getString("id"));
                def.setHotel(rs.getString("hotel"));
                def.setDescripcion(rs.getString("descripcion"));
                def.setPosRelativa (rs.getString("pos_relativa"));
                def.setCoordenadas(rs.getString("coordenadas"));
                def.setFechaRevis(rs.getString("fecha_revis"));
                def.setEstado (rs.getString("estado"));
                listado.add(def);
            }

        }catch (Exception e){
            e.printStackTrace();
            return listado;
        }finally {
            try {
                pst.close();
                rs.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                return listado;
            }

        }
        return listado;
    }



    public boolean actualizarDesfibrilador(Desfibrilador def){
        Connection con =null;
        PreparedStatement st =null;

        try {
            con = ConexionBaseDatos.getConnection();
            st = con.prepareStatement("UPDATE desfibriladores SET hotel=? , descripcion=?, pos_relativa=?, coordenadas=?, fecha_revis=?, estado=? WHERE id=?");
            st.setString(1,def.getHotel());
            st.setString(2,def.getDescripcion());
            st.setString(3, def.getPosRelativa());
            st.setString(4,def.getCoordenadas());
            st.setString(5,def.getFechaRevis());
            st.setString(6, def.getEstado());
            st.setString(7,def.getId());
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

    public boolean eliminarDesfibrilador(String identify){
        Connection con =null;
        PreparedStatement st =null;
        try {
            con = ConexionBaseDatos.getConnection();
            st = con.prepareStatement("DELETE FROM desfibriladores WHERE id=?");
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

