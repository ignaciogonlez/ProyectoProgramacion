package Ventanas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Clases.DBManager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnadirAsignatura extends JFrame{
	private JTextField textFieldID;
	private JTextField textFieldCreditos;
	private JTextField textFieldnomprofesor;
	private JTextField textFieldNombreAsignatura;
	private JTextField textFieldCarrera;
	private JLabel lblNombreAsignatura;
	private JLabel lblnomprofesor;
	private JLabel lblCreditos;
	private JLabel lblCarrera;
	private JButton btnInsertar;
	private JButton btnVolver;
	private JLabel lbIAnoAcademico;
	private Clases.DBManager dbManager;	
	
	private JFrame ventanaActual;
	
	public AnadirAsignatura() {
		
		dbManager = new Clases.DBManager();
		
		ventanaActual = this;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lbIAnoAcademico = new JLabel("AnoAcademico");
		GridBagConstraints gbc_lbIAnoAcademico = new GridBagConstraints();
		gbc_lbIAnoAcademico.anchor = GridBagConstraints.EAST;
		gbc_lbIAnoAcademico.insets = new Insets(0, 0, 5, 5);
		gbc_lbIAnoAcademico.gridx = 4;
		gbc_lbIAnoAcademico.gridy = 2;
		getContentPane().add(lbIAnoAcademico, gbc_lbIAnoAcademico);
		
		textFieldID = new JTextField();
		GridBagConstraints gbc_textFieldID = new GridBagConstraints();
		gbc_textFieldID.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldID.gridx = 5;
		gbc_textFieldID.gridy = 2;
		getContentPane().add(textFieldID, gbc_textFieldID);
		textFieldID.setColumns(10);
		
		lblNombreAsignatura = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombreAsignatura = new GridBagConstraints();
		gbc_lblNombreAsignatura.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreAsignatura.anchor = GridBagConstraints.EAST;
		gbc_lblNombreAsignatura.gridx = 4;
		gbc_lblNombreAsignatura.gridy = 3;
		getContentPane().add(lblNombreAsignatura, gbc_lblNombreAsignatura);
		
		textFieldNombreAsignatura = new JTextField();
		GridBagConstraints gbc_textFieldNombreAsignatura = new GridBagConstraints();
		gbc_textFieldNombreAsignatura.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreAsignatura.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreAsignatura.gridx = 5;
		gbc_textFieldNombreAsignatura.gridy = 3;
		getContentPane().add(textFieldNombreAsignatura, gbc_textFieldNombreAsignatura);
		textFieldNombreAsignatura.setColumns(10);
		
		lblnomprofesor = new JLabel("Profesor");
		GridBagConstraints gbc_lblnomprofesor = new GridBagConstraints();
		gbc_lblnomprofesor.insets = new Insets(0, 0, 5, 5);
		gbc_lblnomprofesor.anchor = GridBagConstraints.EAST;
		gbc_lblnomprofesor.gridx = 4;
		gbc_lblnomprofesor.gridy = 4;
		getContentPane().add(lblnomprofesor, gbc_lblnomprofesor);
		
		textFieldnomprofesor = new JTextField();
		GridBagConstraints gbc_textFieldnomprofesor = new GridBagConstraints();
		gbc_textFieldnomprofesor.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldnomprofesor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldnomprofesor.gridx = 5;
		gbc_textFieldnomprofesor.gridy = 4;
		getContentPane().add(textFieldnomprofesor, gbc_textFieldnomprofesor);
		textFieldnomprofesor.setColumns(10);
		
		lblCreditos = new JLabel("Creditos");
		GridBagConstraints gbc_lblCreditos = new GridBagConstraints();
		gbc_lblCreditos.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreditos.anchor = GridBagConstraints.EAST;
		gbc_lblCreditos.gridx = 4;
		gbc_lblCreditos.gridy = 5;
		getContentPane().add(lblCreditos, gbc_lblCreditos);
		
		textFieldCreditos = new JTextField();
		GridBagConstraints gbc_textFieldCreditos = new GridBagConstraints();
		gbc_textFieldCreditos.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCreditos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCreditos.gridx = 5;
		gbc_textFieldCreditos.gridy = 5;
		getContentPane().add(textFieldCreditos, gbc_textFieldCreditos);
		textFieldCreditos.setColumns(10);
		
		lblCarrera = new JLabel("Carrera");
		GridBagConstraints gbc_lblCarrera = new GridBagConstraints();
		gbc_lblCarrera.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarrera.anchor = GridBagConstraints.EAST;
		gbc_lblCarrera.gridx = 4;
		gbc_lblCarrera.gridy = 6;
		getContentPane().add(lblCarrera, gbc_lblCarrera);
		
		textFieldCarrera = new JTextField();
		GridBagConstraints gbc_textFieldCarrera = new GridBagConstraints();
		gbc_textFieldCarrera.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCarrera.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCarrera.gridx = 5;
		gbc_textFieldCarrera.gridy = 6;
		getContentPane().add(textFieldCarrera, gbc_textFieldCarrera);
		textFieldCarrera.setColumns(10);
		
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
	
		
		btnInsertar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					String creditosSTR = textFieldCreditos.getText();
					int creditos = Integer.parseInt(creditosSTR);
					
					String AnoAcademicoSTR = textFieldID.getText();
					int anoAcademico = Integer.parseInt(AnoAcademicoSTR);
					String carrera = textFieldCarrera.getText();
					String nombreAsignatura = textFieldNombreAsignatura.getText();
					String nomprofesor = textFieldnomprofesor.getText();
					DBManager.anadirAsignatura(anoAcademico, nombreAsignatura, nomprofesor, creditos, carrera);
					JOptionPane.showMessageDialog(null, "Asignatura insertada", "Operación aceptada", JOptionPane.INFORMATION_MESSAGE);
					ventanaActual.dispose();
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
