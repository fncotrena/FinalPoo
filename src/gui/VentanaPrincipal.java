
package gui;


import model.Vuelo;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalTime;
import java.awt.Font;

/**
 * Esta clase carga en pantalla  la informacion  correspondieste a los vuelos .
 * @author federico cotrena
 *
 */

public class VentanaPrincipal extends JFrame {

	private JTextField textfPrecio;
	private TextArea textArea;
	private JTextField textfDuracion;
	private JTextField textfCantidad;
	private JTextField textfTiempo;
	private JTextField textfOrigen;
	private JTextField textfDestino;

	
	public VentanaPrincipal() {
		getContentPane().setLayout(null);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPrecio.setBounds(280, 74, 46, 14);
		getContentPane().add(lblPrecio);

		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDuracion.setBounds(474, 74, 68, 14);
		getContentPane().add(lblDuracion);

		JButton btnMostrar = new JButton("Detalles");
		btnMostrar.setBounds(276, 146, 89, 23);
		getContentPane().add(btnMostrar);

		textfPrecio = new JTextField();
		textfPrecio.setEditable(false);
		textfPrecio.setEnabled(true);
		textfPrecio.setBounds(267, 99, 89, 20);
		getContentPane().add(textfPrecio);
		textfPrecio.setColumns(10);

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 175, 610, 76);
		getContentPane().add(textArea);
		textArea.setColumns(10);
		textArea.setVisible(false);
	
		textfPrecio.setText("");
		textfDuracion = new JTextField();
		textfDuracion.setText("");
		textfDuracion.setEnabled(true);
		textfDuracion.setEditable(false);
		textfDuracion.setColumns(10);
		textfDuracion.setBounds(474, 99, 68, 20);
		getContentPane().add(textfDuracion);

		JLabel Lbtiempo = new JLabel("Salida-Llegada");
		Lbtiempo.setFont(new Font("Arial", Font.PLAIN, 16));
		Lbtiempo.setBounds(357, 74, 107, 14);
		getContentPane().add(Lbtiempo);

		JLabel lblEscalas = new JLabel("Escalas");
		lblEscalas.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEscalas.setBounds(552, 74, 68, 14);
		getContentPane().add(lblEscalas);

		textfCantidad = new JTextField();
		textfCantidad.setEnabled(true);
		textfCantidad.setEditable(false);
		textfCantidad.setText("");
		textfCantidad.setColumns(10);
		textfCantidad.setBounds(552, 99, 68, 20);
		getContentPane().add(textfCantidad);

		textfTiempo = new JTextField();
		textfTiempo.setText("");
		textfTiempo.setEnabled(true);
		textfTiempo.setEditable(false);
		textfTiempo.setColumns(10);
		textfTiempo.setBounds(364, 99, 89, 20);
		getContentPane().add(textfTiempo);

		textfOrigen = new JTextField();
		textfOrigen.setText("");
		textfOrigen.setEnabled(true);
		textfOrigen.setEditable(false);
		textfOrigen.setColumns(10);
		textfOrigen.setBounds(10, 99, 112, 20);
		getContentPane().add(textfOrigen);

		textfDestino = new JTextField();
		textfDestino.setEnabled(true);
		textfDestino.setEditable(false);
		textfDestino.setColumns(10);
		textfDestino.setBounds(132, 99, 115, 20);
		getContentPane().add(textfDestino);

		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOrigen.setBounds(28, 74, 82, 14);
		getContentPane().add(lblOrigen);

		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDestino.setBounds(146, 74, 89, 14);
		getContentPane().add(lblDestino);

		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setVisible(true);

			}
		});

	}

	/**
	 * Este metodo se encarga de cargar los textArea y textfield de la clase con la informacion de los vuelos.
	 *
	 * @param texto the new text detalles
	 */
	public void setTextDetalles(List<Vuelo> texto) {
		textArea.selectAll();
		textArea.setText("");
		if (texto.size() - 3 == 0)
			textfCantidad.setText("Directo");
		else
			textfCantidad.setText(Integer.toString(texto.size() - 3));
		textfTiempo.setText(LocalTime.MIN.plus(Duration.ofMinutes(texto.get(1).getSalida())).toString() + " - "
				+ LocalTime.MIN.plus(Duration.ofMinutes(texto.get(texto.size() - 2).getLlegada())).toString());

		for (int i = 1; i < texto.size() - 1; i++)
			textArea.append(texto.get(i).toString() + '\n');

	}


	/**
	 *  Este metodo carga los textfiel de la clase con la informacion de los vuelos.
	 *
	 * @param textoPrecio precio total del vuelo
	 * @param textoDuracion duracionTotal del vuelo
	 * @param origen la ciudad origen
	 * @param destino la ciudad destino
	 */
	public void setTextEscribir(String textoPrecio, String textoDuracion, String origen, String destino) {
		textfOrigen.setText(origen);
		textfDuracion.setText(textoDuracion);
		textfDestino.setText(destino);
		textfPrecio.setText(textoPrecio);

	}
}
