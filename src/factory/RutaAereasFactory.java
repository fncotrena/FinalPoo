package factory;

import dao.Dao;
import model.*;
import net.*;
import stateless.CalcularDistancia;
import stateless.PorMenorDuracion;
import stateless.PorMenorEscala;
import stateless.PorMenorPrecio;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class RutaAereasFactory {


    private static int TIEMPO_AEROPUERTO = 45;
   //private static List resultado;
    //private final LectorCSV archivo = new LectorCSV();
    private static Graph<Vuelo, Integer> g = new AdjacencyMapGraph<>(true);
    private static List<Vuelo> resultado = new ArrayList<>();



    public CalcularDistancia ObtenerFactory (String tipoVuelos, String origen, String destino) {

        RutaAerea rutaAerea = new RutaAerea(origen, destino);

        switch (tipoVuelos) {
            case "Duracion":
                return new PorMenorDuracion(rutaAerea);


            case "Precio":
                return new PorMenorPrecio(rutaAerea) ;



            case "Escala":
                return new PorMenorEscala(rutaAerea);


            default:
                return null;

        }
    }



}



