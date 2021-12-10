package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbManager {
    private static String USER = "postgres";
    private static String PASSWORD = "app";
    private Connection connection;
    private final String URL = "jdbc:postgresql://localhost:5432/RutasAereas";

    private static DbManager connectInstance;

    private DbManager() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado!");
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            System.out.println("OK ");
        }
    }

    public static DbManager getInstance(){
        if (connectInstance == null ){
            connectInstance = new DbManager();
        }
        return connectInstance;
    }


    //Implementación del patrón singleton para acceder a una instancia única de la clase

    public Connection getConnection(){
        return connection;
    }

}






