package co.uceva.edu.base.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos{

    private static String url = "jdbc:sqlite:C:\\Users\\USUARIO\\Documents\\IntelligentIde\\proyecto-integrador-iv\\proyecto.sqlite";  //sqlite= nombre del driver
    private static String username = "root";
    private static String password = "sasa";
    


    private ConexionBaseDatos(){

    }
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, username, password);
    }
}
