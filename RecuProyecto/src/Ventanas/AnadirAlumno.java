package Ventanas;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import Clases.BD;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AnadirAlumno extends JFrame{
	private JTextField textFieldGrado;
	private JTextField textFieldDni;
	private JTextField textFieldNom;
	private JTextField textFieldAnoAcademico;
	private JLabel lblNom;
	private JLabel lblDni;
	private JLabel lblGrado;
	private JLabel lblAnoAcademico;
	private JButton btnInsertar;
	private JButton btnVolver;
	private JFrame ventanaActual;
	
	public AnadirAlumno() {
		
		ventanaActual = this;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblNom = new JLabel("Nombre");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.anchor = GridBagConstraints.EAST;
		gbc_lblNom.gridx = 4;
		gbc_lblNom.gridy = 3;
		getContentPane().add(lblNom, gbc_lblNom);
		
		textFieldNom = new JTextField();
		GridBagConstraints gbc_textFieldNom = new GridBagConstraints();
		gbc_textFieldNom.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNom.gridx = 5;
		gbc_textFieldNom.gridy = 3;
		getContentPane().add(textFieldNom, gbc_textFieldNom);
		textFieldNom.setColumns(10);
		
		lblDni = new JLabel("Dni");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.anchor = GridBagConstraints.EAST;
		gbc_lblDni.gridx = 4;
		gbc_lblDni.gridy = 4;
		getContentPane().add(lblDni, gbc_lblDni);
		
		textFieldDni = new JTextField();
		GridBagConstraints gbc_textFieldDni = new GridBagConstraints();
		gbc_textFieldDni.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDni.gridx = 5;
		gbc_textFieldDni.gridy = 4;
		getContentPane().add(textFieldDni, gbc_textFieldDni);
		textFieldDni.setColumns(10);
		
		lblGrado = new JLabel("Grado");
		GridBagConstraints gbc_lblGrado = new GridBagConstraints();
		gbc_lblGrado.insets = new Insets(0, 0, 5, 5);
		gbc_lblGrado.anchor = GridBagConstraints.EAST;
		gbc_lblGrado.gridx = 4;
		gbc_lblGrado.gridy = 5;
		getContentPane().add(lblGrado, gbc_lblGrado);
		
		textFieldGrado = new JTextField();
		GridBagConstraints gbc_textFieldGrado = new GridBagConstraints();
		gbc_textFieldGrado.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldGrado.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGrado.gridx = 5;
		gbc_textFieldGrado.gridy = 5;
		getContentPane().add(textFieldGrado, gbc_textFieldGrado);
		textFieldGrado.setColumns(10);
		
		lblAnoAcademico = new JLabel("AnoAcademico");
		GridBagConstraints gbc_lblAnoAcademico = new GridBagConstraints();
		gbc_lblAnoAcademico.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnoAcademico.anchor = GridBagConstraints.EAST;
		gbc_lblAnoAcademico.gridx = 4;
		gbc_lblAnoAcademico.gridy = 6;
		getContentPane().add(lblAnoAcademico, gbc_lblAnoAcademico);
		
		textFieldAnoAcademico = new JTextField();
		GridBagConstraints gbc_textFieldAnoAcademico = new GridBagConstraints();
		gbc_textFieldAnoAcademico.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAnoAcademico.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAnoAcademico.gridx = 5;
		gbc_textFieldAnoAcademico.gridy = 6;
		getContentPane().add(textFieldAnoAcademico, gbc_textFieldAnoAcademico);
		textFieldAnoAcademico.setColumns(10);
		
		btnVolver = new JButton("Volver");
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 4;
		gbc_btnVolver.gridy = 8;
		getContentPane().add(btnVolver, gbc_btnVolver);
		
		btnInsertar = new JButton("Insertar");
		GridBagConstraints gbc_btnInsertar = new GridBagConstraints();
		gbc_btnInsertar.insets = new Insets(0, 0, 0, 5);
		gbc_btnInsertar.gridx = 5;
		gbc_btnInsertar.gridy = 8;
		getContentPane().add(btnInsertar, gbc_btnInsertar);
		
		String nom = textFieldNom.getText();
		String dni = textFieldDni.getText();
		String grado = textFieldGrado.getText();
		
			
		
		btnInsertar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String AnoAcademicoSTR = textFieldAnoAcademico.getText();
					int anoAcademico = Integer.parseInt(AnoAcademicoSTR);
					BD.anadirAlumno(nom, dni, grado, anoAcademico);
					JOptionPane.showMessageDialog(null, "Alumno insertado", "Operación aceptada", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception f) {
					f.printStackTrace();
					
				}
			}
			});
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				new Gestion();
			}
		});
		
		setVisible(true);
		
		
	}

}
