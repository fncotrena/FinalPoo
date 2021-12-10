/*
 * 
 */
package model;

import java.time.Duration;
import java.time.LocalTime;

public class Vuelo {

	private String origen;
	private String destino;
	private String nroVuelo;
	private String nombreAerolinea;
	private int salida;
	private int llegada;
	private double precio;
	private int escalas;

	public Vuelo(String origen, String destino, String nroVuelo, String nombreAerolinea, int salida, int llegada,
			double precio, int escalas) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.nroVuelo = nroVuelo;
		this.nombreAerolinea = nombreAerolinea;
		this.salida = salida;
		this.llegada = llegada;
		this.precio = precio;
		this.escalas = escalas;
	}

	public Vuelo() {

	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getNroVuelo() {
		return nroVuelo;
	}

	public void setNroVuelo(String nroVuelo) {
		this.nroVuelo = nroVuelo;
	}

	public int getSalida() {
		return salida;
	}

	public void setSalida(int salida) {
		this.salida = salida;
	}

	public String getNombreAerolinea() {
		return nombreAerolinea;
	}

	public void setNombreAerolinea(String nombreAerolinea) {
		this.nombreAerolinea = nombreAerolinea;
	}

	public int getLlegada() {
		return llegada;
	}

	public void setLlegada(int llegada) {
		this.llegada = llegada;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getEscalas() {
		return escalas;
	}

	public void setEscalas(int escalas) {
		this.escalas = escalas;
	}

	@Override
	public String toString() {
		return "Vuelo [origen=" + origen + ", destino=" + destino + ", nroVuelo=" + nroVuelo +

				", nombreAerolinea=" + nombreAerolinea + ", salida="
				+ LocalTime.MIN.plus(Duration.ofMinutes(salida)).toString() + ", llegada="
				+ LocalTime.MIN.plus(Duration.ofMinutes(llegada)).toString() + ", precio=" + precio + "]";
	}

}
