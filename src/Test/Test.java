package Test;

import dao.VueloDao;
import database.DbManager;
import factory.RutaAereasFactory;
import model.Grafo;
import model.RutaAerea;

public class Test {
    public static void main(String[] args) {
        DbManager.getInstance();
        Grafo grafo = new Grafo("Trelew(REL)","Buenos Aires(AEP)");
     //   VueloDao vueloDao = new VueloDao();
        RutaAereasFactory rutaAereasFactory =  new RutaAereasFactory();
     //   System.out.print(vueloDao.obtenerTodos());
        System.out.println(rutaAereasFactory.ObtenerFactory("Precio","Trelew(REL)","Buenos Aires(AEP)").obternerDistancia());
        System.out.println(rutaAereasFactory.ObtenerFactory("Precio","Trelew(REL)","Buenos Aires(AEP)")
                .duracionTotal(
                        rutaAereasFactory.ObtenerFactory("Precio","Trelew(REL)","Buenos Aires(AEP)").obternerDistancia()
                ));
        System.out.println(rutaAereasFactory.ObtenerFactory("Precio","Trelew(REL)","Buenos Aires(AEP)")
                .precioTotal(
                        rutaAereasFactory.ObtenerFactory("Precio","Trelew(REL)","Buenos Aires(AEP)").obternerDistancia()
                ));


        System.out.print("*************************************");
        //System.out.println(grafo.calcularDistancia(2));
        //System.out.println(grafo.calcularDistancia(2));


    }
}
