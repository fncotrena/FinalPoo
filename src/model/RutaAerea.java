package model;



public class RutaAerea {

    private String vueloOrigen;
    private String vueloDestino;

    public RutaAerea(String vueloOrigen, String vueloDestino) {
        super();
        this.vueloOrigen = vueloOrigen;
        this.vueloDestino = vueloDestino;
    }

    public String getVueloOrigen() {
        return vueloOrigen;
    }

    public void setVueloOrigen(String vueloOrigen) {
        this.vueloOrigen = vueloOrigen;
    }

    public String getVueloDestino() {
        return vueloDestino;
    }

    public void setVueloDestino(String vueloDestino) {
        this.vueloDestino = vueloDestino;
    }





}
