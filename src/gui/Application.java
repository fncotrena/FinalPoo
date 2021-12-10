
package gui;

import java.awt.EventQueue;




public class Application {


	public static void main(String[] args) {
		Menu frame = new Menu();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}}

	

	