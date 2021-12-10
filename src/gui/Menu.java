/*
 *
 */
package gui;


import factory.RutaAereasFactory;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;


public class Menu extends JFrame {


    //  private Grafo vuelo;
    private VentanaPrincipal ventana = new VentanaPrincipal();
    ;
    private JPanel contentPane;
    private String vueloTxtOrigen = " ";
    private String vueloTxtDestino = " ";

    private String[] ciudades = {"Mar del Plata(MDQ)", "Buenos Aires(AEP)",
            "Ushuaia(USH)", "Rio Gallegos(RGL)", "Puerto Iguazu(IGR)", "El Calafate(FTE)", "Resistencia(RES)",
            "Posada(PSS)", "Formosa(FMA)", "Rio Grande(RGA)", "Trelew(REL)", "Buenos Aires(EZE)", "Rosario(ROS)",
            "Mendoza(MDZ)", "Cordoba(COR)", "Salta(SLA)", "Bariloche(BRC)", "Neuquen(NQN)", "El Calafate(FTE)",
            "Tucuman(TUC)", "Santiago Del Estero(SDE)", "La Rioja(IRJ)", "Comodoro Rivadavia(CRD)",
            "San Martin Andes(CPC)", "Bahia Blanca(BHI)", "San Juan(AUQ)", "San Rafael(AFA)", "San Luis(LUQ)",
            "Rio Gallegos(RGL)"};


    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JComboBox comboBoxFiltrar = new JComboBox();
        JComboBox comboBoxOrg = new JComboBox();
        comboBoxOrg.setBounds(25, 111, 176, 20);
        comboBoxOrg.setModel(new DefaultComboBoxModel(ciudades));
        contentPane.add(comboBoxOrg);


        JComboBox comboBoxDest = new JComboBox();
        comboBoxDest.setModel(new DefaultComboBoxModel(ciudades));
        comboBoxDest.setBounds(237, 111, 176, 20);
        contentPane.add(comboBoxDest);

        JLabel lblOrigen = new JLabel("Origen");
        lblOrigen.setBounds(79, 87, 46, 14);
        contentPane.add(lblOrigen);

        JLabel lblDestino = new JLabel("Destino");
        lblDestino.setBounds(280, 87, 46, 14);
        contentPane.add(lblDestino);
        JLabel lblError = new JLabel("El Destino no puede ser el mismo que el Origen ");
        lblError.setVisible(false);
        lblError.setForeground(Color.RED);

        lblError.setBounds(44, 159, 384, 16);
        contentPane.add(lblError);

        comboBoxFiltrar.setModel(new DefaultComboBoxModel(new String[]{"Duracion", "Precio", "Escalas"}));
        comboBoxFiltrar.setBounds(54, 44, 89, 20);
        contentPane.add(comboBoxFiltrar);

        JButton bntSalir = new JButton("Salir");
        bntSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        bntSalir.setBounds(68, 211, 89, 23);
        contentPane.add(bntSalir);

        JLabel lblFiltrar = new JLabel("Filtrar Por");
        lblFiltrar.setBounds(44, 16, 99, 16);
        contentPane.add(lblFiltrar);

        JButton bntBuscar = new JButton("Buscar");
        bntBuscar.setBounds(269, 211, 89, 23);
        contentPane.add(bntBuscar);
        bntBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vueloTxtOrigen = comboBoxOrg.getSelectedItem().toString();
                vueloTxtDestino = comboBoxDest.getSelectedItem().toString();
                if (vueloTxtOrigen.equals(vueloTxtDestino))
                    lblError.setVisible(true);

                else {
                    lblError.setVisible(false);

                    ventana.setVisible(true);

                    ventana.setSize(650, 350);


                    RutaAereasFactory rutasAereasFactory = new RutaAereasFactory();

                    try {
                        ventana.setTextDetalles(rutasAereasFactory.ObtenerFactory(comboBoxFiltrar.getSelectedItem().toString(), vueloTxtOrigen, vueloTxtDestino).obternerDistancia());
                        ventana.setTextEscribir("$" +
                                rutasAereasFactory.ObtenerFactory(comboBoxFiltrar.getSelectedItem().toString(), vueloTxtOrigen, vueloTxtDestino).precioTotal(
                                        rutasAereasFactory.ObtenerFactory(comboBoxFiltrar.getSelectedItem().toString(),vueloTxtOrigen,vueloTxtDestino).obternerDistancia()

                                ),
                                rutasAereasFactory.ObtenerFactory(comboBoxFiltrar.getSelectedItem().toString(), vueloTxtOrigen, vueloTxtDestino).duracionTotal(
                                        rutasAereasFactory.ObtenerFactory(comboBoxFiltrar.getSelectedItem().toString(),vueloTxtOrigen,vueloTxtDestino).obternerDistancia()

                                ),

                                vueloTxtOrigen, vueloTxtDestino);

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "No hay vuelos Disponibles", "Vuelo",
                                JOptionPane.WARNING_MESSAGE);

                    }

                }
            }
        });
    }
}
