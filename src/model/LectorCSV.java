
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {

	public LectorCSV() {
		super();

	}
	private BufferedReader br = null;
	private String line = "";
	private String cvsSplitBy = ";";
	private List<Vuelo> vuelos = new ArrayList<>();

	/**
	 * Este metodo se encarga de leer los vuelos que estan en el archivo y cargar en
	 * una lista .
	 *
	 * @return lista con los vuelos
	 */

	public List<Vuelo> cargarVuelo() {
		try {
			br = new BufferedReader(new FileReader("vuelos.csv"));
			while ((line = br.readLine()) != null) {
				String[] vuelo = line.split(cvsSplitBy);
                      
				vuelos.add(new Vuelo(vuelo[0], vuelo[1], vuelo[2], vuelo[3], Integer.parseInt(vuelo[4]),
						Integer.parseInt(vuelo[5]), Double.parseDouble(vuelo[6]), 0));
			}
		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return vuelos;

	}
}
