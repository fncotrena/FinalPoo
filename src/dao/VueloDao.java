package dao;

import database.DbManager;
import model.Vuelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author federico cotrena
 */
public class VueloDao implements Dao<Vuelo, Integer> {

    //Arreglo con las consultas que se usarán para la tabla Vuelo
    private final String[] QUERIES = {
            "INSERT INTO vuelos (nroVuelo, llegada, salida) VALUES ( ?, ? , ? )",
            "SELECT * FROM vuelos WHERE id = ?;",
            "UPDATE vuelos SET nroVuelo = ? , llegada = ?, salida = ? WHERE (id = ?);",
            "DELETE FROM vuelos WHERE (id = ?);",
            "SELECT * FROM vuelos;"
    };
    //Declaración de variable para preparar las consultas
    private Connection connection;
    //Declaración de variable para insertar valores a consultas
    private PreparedStatement preQuery;
    //Devuelve true si se ejecutó correctamente y false si algo falló
    private boolean isSuccesfully = false;
    //Objeto que servirá para almacenar información al buscar un registro
    private Vuelo vuelo;
    //Lista de registros
    private List<Vuelo> vuelos ;

    //Constructor de la clase en donde se inicializarán las variables previamente declaradas
    public VueloDao() {
        connection = DbManager.getInstance().getConnection();
        vuelo = new Vuelo();
        vuelos = new ArrayList<>();
    }

    @Override
    public void insertar(Vuelo a) {

    }

    @Override
    public void modificar(Vuelo a) {

    }

    @Override
    public void eliminar(Vuelo a) {

    }

    @Override
    public List<Vuelo> obtenerTodos() {
        try {
            try {
                preQuery = connection.prepareStatement(QUERIES[4]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                vuelos.add(new Vuelo(data.getString("origen"), data.getString("destino"), data.getString("nroVuelo"),
                        data.getString("nombreAerolinea"), data.getInt("salida")
                ,data.getInt("llegada"),data.getDouble("precio"),data.getInt("escala")
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(VueloDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vuelos;
    }

    @Override
    public void obtener(Vuelo a) {

    }
}
