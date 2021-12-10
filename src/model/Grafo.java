package model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import net.AdjacencyMapGraph;
import net.Graph;
import net.GraphAlgorithms;
import net.PositionalList;

import net.Vertex;
/**
 * Esta clase se utiliza leer los vuelos ,para crear y cargar el  grafo con los vuelos   que se utilizan para 
 * calcular la distancia  de camino minimo entre el origen y destino.
 * @author federico cotrena
 *@version 1.0
 */
public class Grafo {
	private final LectorCSV archivo = new LectorCSV();
	private static int TIEMPO_AEROPUERTO = 45;
	private Graph<Vuelo, Integer> g = new AdjacencyMapGraph<>(true);
	private List<Vuelo> resultado = new ArrayList<>();
	private String vueloOrigen;
	private String vueloDestino;

	public Grafo(String vueloOrigen, String vueloDestino) {
		super();
		this.vueloOrigen = vueloOrigen;
		this.vueloDestino = vueloDestino;
	}

	public String duracionTotal() {
		int llegada = 0;
		int salida = 0;
		salida = resultado.get(1).getSalida();
		llegada = resultado.get(resultado.size() - 2).getLlegada();
		return LocalTime.MIN.plus(Duration.ofMinutes(llegada - salida)).toString();
	}
	/**
	 * Este metodo es el encargado  de determinar el camino mas corto dado los vertices origen y destino.
	 * .
	 * @param  n se encarga de cargar los arcos entre los vertices dependiendo si recibe 1=precio ,2=duracion ,3= escala;
	 */
	public List<Vuelo> calcularDistancia(int n) {
		resultado = new ArrayList<Vuelo>();
		for (Vuelo v : archivo.cargarVuelo())
			g.insertVertex(v);

		//System.out.println(g);

		switch (n) {
		case 1:
			n = 1; {
				for (Vertex<Vuelo> x : g.vertices()) {
					for (Vertex<Vuelo> y : g.vertices()) {
						if (x.getElement().getDestino().equals(y.getElement().getOrigen())

								&& x.getElement().getLlegada() + TIEMPO_AEROPUERTO < y.getElement().getSalida())
							g.insertEdge(x, y, y.getElement().getLlegada()-x.getElement().getLlegada());
						
					}
				}	
				Vertex<Vuelo> origen = g.insertVertex(new Vuelo("--",vueloOrigen, "--", "--", 0, 0, 0, 0));
				for (Vertex<Vuelo> y : g.vertices())
					if (origen.getElement().getDestino().equals(y.getElement().getOrigen())) {
						
						g.insertEdge(origen, y, y.getElement().getLlegada()-y.getElement().getSalida());}
						
					

				// Aeropuerto destino
				Vertex<Vuelo> destino = g.insertVertex(new Vuelo(vueloDestino, "--", "--", "--", 0, 0, 0, 0));
				for (Vertex<Vuelo> y : g.vertices())
					if (destino.getElement().getOrigen().equals(y.getElement().getDestino()))
						g.insertEdge(y, destino, 0);

			
				PositionalList<Vertex<Vuelo>> p = GraphAlgorithms.shortestPathList(g, origen, destino);
				for (Vertex<Vuelo> v : p)
				 resultado.add(v.getElement());
			
			
			

			break;}
		
		case 2:
			n = 2; {
			for (Vertex<Vuelo> x : g.vertices()) {
				for (Vertex<Vuelo> y : g.vertices()) {
					if (x.getElement().getDestino().equals(y.getElement().getOrigen())
							&& x.getElement().getLlegada() + TIEMPO_AEROPUERTO < y.getElement().getSalida())
						g.insertEdge(x, y, (int) y.getElement().getPrecio());
				}
			}

			// Aeropuerto origen
			Vertex<Vuelo> origen = g.insertVertex(new Vuelo("---", vueloOrigen, "", "", 0, 0, 0, 0));

			for (Vertex<Vuelo> y : g.vertices()) {
				if (origen.getElement().getDestino().equals(y.getElement().getOrigen()))

					// Precio mas bajo
					g.insertEdge(origen, y, (int) y.getElement().getPrecio());
			}

			// Aeropuerto destino
			Vertex<Vuelo> destino = g.insertVertex(new Vuelo(vueloDestino, "---", "", "", 0, 0, 0, 0));
			for (Vertex<Vuelo> y : g.vertices()) {
				if (destino.getElement().getOrigen().equals(y.getElement().getDestino()))
					g.insertEdge(y, destino, 0);
			}
			System.out.println("origenGrafo : "  + vueloOrigen +" destinoGrafo "+ vueloDestino);

			PositionalList<Vertex<Vuelo>> p = GraphAlgorithms.shortestPathList(g, origen, destino);
			for (Vertex<Vuelo> v : p)
				resultado.add(v.getElement());
			
			break;
		}
		case 3:
			n = 3; {
			for (Vertex<Vuelo> x : g.vertices())
				for (Vertex<Vuelo> y : g.vertices())

					if (x.getElement().getDestino().equals(y.getElement().getOrigen())
							&& x.getElement().getLlegada() + TIEMPO_AEROPUERTO < y.getElement().getSalida())
						g.insertEdge(x, y, (int) y.getElement().getEscalas() + 1);

			// Aeropuerto origen
			Vertex<Vuelo> origen = g.insertVertex(new Vuelo("---", vueloOrigen, "", "", 0, 0, 0, 0));
			for (Vertex<Vuelo> y : g.vertices())
				if (origen.getElement().getDestino().equals(y.getElement().getOrigen()))
					g.insertEdge(origen, y, (int) y.getElement().getEscalas() + 1);
			// Aeropuerto destino
			Vertex<Vuelo> destino = g.insertVertex(new Vuelo(vueloDestino, "---", "", "", 0, 0, 0, 0));

			for (Vertex<Vuelo> y : g.vertices()) {
				// cant++;
				if (destino.getElement().getOrigen().equals(y.getElement().getDestino()))
					g.insertEdge(y, destino, 0);
			}

			PositionalList<Vertex<Vuelo>> p = GraphAlgorithms.shortestPathList(g, origen, destino);
			for (Vertex<Vuelo> v : p) {
				resultado.add(v.getElement());

			}

			break;
		}

		}return resultado;

	}

	public int precioTotal() {
		int precio = 0;
		for (Vuelo v : resultado) {
			precio += v.getPrecio();
		}
		return precio;
	}
}
