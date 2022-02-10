package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Admin;

import Clases.DBManager;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Registro extends JFrame {

		/**
	 * 
	 */
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
		private DBManager DBmanager;

		/**
		 * Create the frame.
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
		public Registro() throws ClassNotFoundException, SQLException {
			
			DBmanager = new DBManager();
			DBmanager.connect();
			
			setTitle("Registro Admin");
			ventanaActual = this;
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			panelSur = new JPanel();
			contentPane.add(panelSur, BorderLayout.SOUTH);
			
			btnVolver = new JButton("VOLVER");
			panelSur.add(btnVolver);
			
			btnRegistrarPersona = new JButton("REGISTRAR PERSONA");
			btnRegistrarPersona.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String erdni = "[0-9]{8}[A-Z]";
					String d = textDni.getText();
					boolean correctoDni = Pattern.matches(erdni, d);
					String n = textContrasena.getText();
					
					DBmanager.crearTablas();

					if(!correctoDni){ 	
						JOptionPane.showMessageDialog(null, "El dni no es correcto", "ERROR!!", JOptionPane.ERROR_MESSAGE);
						vaciarCampos();
						DBmanager.eliminarAdmin(d, n);
						return;
					}
			
					ArrayList<Admin> aAdmins = DBmanager.obtenerAdmin(d);
					
					if(aAdmins.size()>0) {
						JOptionPane.showMessageDialog(null, "Este admin ya está en la base de datos", "ERROR!!", JOptionPane.ERROR_MESSAGE);
						vaciarCampos();
						return;
					}
					
					DBmanager.anadirAdmin(d, n);
					JOptionPane.showMessageDialog(null, "Persona registrada correctamente", "REGISTRO CORRECTO", JOptionPane.INFORMATION_MESSAGE);
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
			
			/*EVENTOS*/
			btnVolver.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ventanaActual.dispose();
					//Inicio.setVisible(true);
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
