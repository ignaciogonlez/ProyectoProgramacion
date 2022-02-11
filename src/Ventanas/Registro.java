package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Admin;

import Clases.DBManager;
import Clases.ExcepcionAlud;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Registro extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelSur;
	private JButton btnVolver;
	private JFrame ventanaActual;
	private JPanel panelCentral;
	private JLabel lblDni;
	private JTextField textDni;
	private JLabel lblContrasena;
	private JTextField textContrasena;
	private JButton btnRegistrarPersona;
	private static Logger logger = Logger.getLogger("Asignatura");


	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Registro() throws ClassNotFoundException, SQLException {

		DBManager.connect();

		setTitle("Registro Admin");
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 140);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);

		btnRegistrarPersona = new JButton("REGISTRAR PERSONA");
		
		
		/**
		 * Bóton que registra a la persona en la tabla admin de la BD si el dni y la contraseña a registrar son correctos.
		 */
		btnRegistrarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String erdni = "[0-9]{8}[A-Z]";
				String d = textDni.getText();
				boolean correctoDni = Pattern.matches(erdni, d);
				String n = textContrasena.getText();

				try {
					DBManager.crearTablas();
				} catch (ExcepcionAlud e2) {
					logger.log(Level.SEVERE, "Error creando tablas", e2);
				}

				if (!correctoDni) {
					JOptionPane.showMessageDialog(null, "El dni no es correcto", "ERROR!!", JOptionPane.ERROR_MESSAGE);
					vaciarCampos();
					try {
						DBManager.eliminarAdmin(d, n);
					} catch (ExcepcionAlud e1) {
						logger.log(Level.SEVERE, "Error eliminarAdmin", e1);
					}
					return;
				}

				ArrayList<Admin> aAdmins = new ArrayList<Admin>();
				try {
					aAdmins = DBManager.obtenerAdmin(d);
				} catch (ExcepcionAlud e1) {
					logger.log(Level.SEVERE, "Error obtenerAdmin", e1);
				}

				if (aAdmins.size() > 0) {
					JOptionPane.showMessageDialog(null, "Este admin ya está en la base de datos", "ERROR!!",
							JOptionPane.ERROR_MESSAGE);
					vaciarCampos();
					return;
				}

				try {
					DBManager.anadirAdmin(d, n);
				} catch (ExcepcionAlud e1) {
					logger.log(Level.SEVERE, "Error anadirAdmin", e1);

				}
				JOptionPane.showMessageDialog(null, "Persona registrada correctamente", "REGISTRO CORRECTO",
						JOptionPane.INFORMATION_MESSAGE);
				vaciarCampos();

			}
		});
		panelSur.add(btnRegistrarPersona);

		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 0));

		lblDni = new JLabel("Introduce tu dni:");
		panelCentral.add(lblDni);

		textDni = new JTextField();
		panelCentral.add(textDni);
		textDni.setColumns(10);

		lblContrasena = new JLabel("Introduce tu contrasena:");
		panelCentral.add(lblContrasena);

		textContrasena = new JTextField();
		panelCentral.add(textContrasena);
		textContrasena.setColumns(10);

		
		/**
		 * Botón que regresa a la ventana Inicio.
		 */
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new Inicio();
			}
		});
		setVisible(true);
	}

	private void vaciarCampos() {
		textDni.setText("");
		textContrasena.setText("");
	}

}
