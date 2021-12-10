package stateless;

import dao.Dao;
import factory.DaoFactory;
import model.RutaAerea;
import model.Vuelo;
import net.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PorMenorDuracion implements CalcularDistancia {
    static Dao dao = DaoFactory.getDao(DaoFactory.DaoType.VUELO);
    private static int TIEMPO_AEROPUERTO = 45;
    private RutaAerea rutaAerea;
    private Graph<Vuelo, Integer> g = new AdjacencyMapGraph<>(true);
    private List<Vuelo> resultado = new ArrayList<>();

    public PorMenorDuracion(RutaAerea rutaAerea) {
        this.rutaAerea = rutaAerea;
    }

    @Override
    public List<Vuelo> obternerDistancia() {
        cargarVuelos();

        for (Vertex<Vuelo> x : g.vertices()) {
            for (Vertex<Vuelo> y : g.vertices()) {
                if (x.getElement().getDestino().equals(y.getElement().getOrigen())

                        && x.getElement().getLlegada() + TIEMPO_AEROPUERTO < y.getElement().getSalida())
                    g.insertEdge(x, y, y.getElement().getLlegada() - x.getElement().getLlegada());

            }
        }
        Vertex<Vuelo> origen = g.insertVertex(new Vuelo("--", this.rutaAerea.getVueloOrigen(), "--", "--", 0, 0, 0, 0));
        for (Vertex<Vuelo> y : g.vertices()) {
            if (origen.getElement().getDestino().equals(y.getElement().getOrigen()))
                g.insertEdge(origen, y, y.getElement().getLlegada() - y.getElement().getSalida());
        }


        // Aeropuerto destino
        Vertex<Vuelo> destino = g.insertVertex(new Vuelo(this.rutaAerea.getVueloDestino(), "--", "--", "--", 0, 0, 0, 0));
        for (Vertex<Vuelo> y : g.vertices()) {
            if (destino.getElement().getOrigen().equals(y.getElement().getDestino()))
                g.insertEdge(y, destino, 0);
        }


        PositionalList<Vertex<Vuelo>> p = GraphAlgorithms.shortestPathList(g, origen, destino);

        for (Vertex<Vuelo> v : p) {
            resultado.add(v.getElement());
        }

        return resultado;

    }

    public String duracionTotal(List<Vuelo> re) {

        int llegada = 0;
        int salida = 0;
        salida = re.get(1).getSalida();
        llegada = re.get(re.size() - 2).getLlegada();
        return LocalTime.MIN.plus(Duration.ofMinutes(llegada - salida)).toString();
    }

    public int precioTotal(List<Vuelo> re) {
        int precio = 0;
        for (Vuelo v : re) {
            precio += v.getPrecio();
        }
        return precio;
    }

    public void cargarVuelos() {
        List<Vuelo> resultado = dao.obtenerTodos();

        for (Vuelo v : resultado)
            g.insertVertex(v);
    }
}