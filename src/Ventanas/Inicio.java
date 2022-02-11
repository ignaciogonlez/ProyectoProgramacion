package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Admin;
import Clases.DBManager;
import Clases.ExcepcionAlud;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Inicio extends JFrame {

	private JPanel contentPane, panelSur, panelCentral;
	private JLabel lblNombre, lblContrasenia;
	private JTextField textDni, textContrasenia;
	private JButton btnSalir, btnRegistro;
	private JFrame ventanaActual;
	private JButton btnGestion;
	private JLabel lblLogoUD;
	private static Logger logger = Logger.getLogger("Asignatura");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		ventanaActual = this;

		setTitle("VENTANA INICIAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		btnSalir = new JButton("SALIR");

		panelSur.add(btnSalir);

		btnRegistro = new JButton("REGISTRO");
		panelSur.add(btnRegistro);

		btnGestion = new JButton("GESTION");

		panelSur.add(btnGestion);

		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 0));

		lblNombre = new JLabel("Introduce tu dni:");
		panelCentral.add(lblNombre);

		textDni = new JTextField(30);

		panelCentral.add(textDni);
		textDni.setColumns(10);

		lblContrasenia = new JLabel("Intoduce la contrase\u00F1a:");
		panelCentral.add(lblContrasenia);

		textContrasenia = new JTextField();
		textContrasenia.setText("");
		panelCentral.add(textContrasenia);
		textContrasenia.setColumns(10);

		lblLogoUD = new JLabel("ALUD");
		lblLogoUD.setFont(new Font("Tahoma", Font.BOLD, 67));
		lblLogoUD.setIcon(new ImageIcon("logo-vector-universidad-deusto.jpg"));
		contentPane.add(lblLogoUD, BorderLayout.NORTH);
		
		/**
		 * Botón que cierra la ventana.
		 */
		btnSalir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				logger.log(Level.SEVERE, "Saliendo....");

				System.exit(0);
			}
		});
		
		/**
		 * Botón que lleva a la ventana registro.
		 */
		btnRegistro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Registro();
				} catch (Exception e1) {
					logger.log(Level.SEVERE, "Error en Registro", e1);

				}
				ventanaActual.setVisible(false);
			}
		});
		
		/**
		 * Botón que lleva a la ventana gestión si los datos introducidos en dni y contraseña son correctos.
		 */
		btnGestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String erdni = "[0-9]{8}[A-Z]";
				String d = textDni.getText();
				boolean correctoDni = Pattern.matches(erdni, d);
				try {
					DBManager.connect();
				} catch (ClassNotFoundException | SQLException e1) {
					logger.log(Level.SEVERE, "Error conectando con bd", e1);
				}

				try {
					DBManager.crearTablas();
				} catch (ExcepcionAlud e2) {
					logger.log(Level.SEVERE, "Error creando tablas", e2);
				}

				ArrayList<Admin> aAdmins = new ArrayList<Admin>();
				try {
					aAdmins = DBManager.obtenerAdmin(d, textContrasenia.getText());
				} catch (ExcepcionAlud e2) {
					logger.log(Level.SEVERE, "Error obtenerAdmin", e2);
				}

				if (!correctoDni) {
					JOptionPane.showMessageDialog(null, "El dni no tiene el formato correcto", "ERROR!!",
							JOptionPane.ERROR_MESSAGE);

				} else if (aAdmins.size() > 0) {
					JOptionPane.showMessageDialog(null, "Bienvenido", "ALUD!!", JOptionPane.INFORMATION_MESSAGE);
					try {
						new Gestion();
					} catch (ExcepcionAlud e1) {
						logger.log(Level.SEVERE, "Error en Gestion", e1);
					}
					ventanaActual.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "El dni o la contraseña no son correctos", "INICIO INCORRECTO",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		setVisible(true);

	}

}
