package Ventanas;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import Clases.Alumno;
import Clases.AlumnosTableModel;
import Clases.Asignatura;
import Clases.AsignaturasTableModel;
import Clases.BD;


import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.awt.event.ActionEvent;

public class Gestion extends JFrame{
	private JTable tablaAsignaturas;
	private JTable tablaAlumnos;
	private BD BDmanager;
	private List<Alumno> aAlumnos;
	private List<Asignatura> aAsignaturas;
	private JFrame ventanaActual;
	
	
	public Gestion() {
		ventanaActual = this;
		BDmanager = new BD();
		
		try {
			BDmanager.connect();
			aAlumnos = BDmanager.obtenerAlumnos();
			aAsignaturas = BD.obtenerAsignaturas();
		} catch (SQLException | ClassNotFoundException e) {
		}
		
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		aAlumnos = new ArrayList<>();
		tablaAlumnos.setModel(new AlumnosTableModel(aAlumnos));
		//tablaAsignaturas.setModel(new AsignaturasTableModel(aAsignaturas));
		
		JButton btnAnadirAlumno = new JButton("AnadirAlumno");
		panel.add(btnAnadirAlumno);
		
		btnAnadirAlumno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AnadirAlumno();
				ventanaActual.setVisible(false);
			}
		});
		
		JButton btnAnadirAsignatura = new JButton("AnadirAsignatura");
		panel.add(btnAnadirAsignatura);
		
		btnAnadirAsignatura.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AnadirAsignatura();
				ventanaActual.setVisible(false);
			}
		});

		
		JButton btnMatricular = new JButton("Matricular");
		panel.add(btnMatricular);
		
		tablaAsignaturas = new JTable();
		getContentPane().add(tablaAsignaturas, BorderLayout.WEST);
		
		tablaAlumnos = new JTable();
		getContentPane().add(tablaAlumnos, BorderLayout.CENTER);
		
		setVisible(true);
	}

}
