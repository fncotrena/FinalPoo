package stateless;

import model.Vuelo;

import java.util.List;

public interface CalcularDistancia {
    List<Vuelo> obternerDistancia();
    void cargarVuelos();
    int precioTotal(List<Vuelo> re);
    String duracionTotal(List<Vuelo> re);

}
