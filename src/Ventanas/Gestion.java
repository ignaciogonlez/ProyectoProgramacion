package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.tree.DefaultTreeModel;

import Clases.Asignatura;
import Clases.AsignaturasTableModel;
import Clases.DBManager;

public class Gestion extends JFrame {

	private Clases.DBManager dbManager;
	private JTable asignaturasJTable;
	private List<Asignatura> asignaturas;
	private JLabel informacionLabel;
	JTextField carreraFilter;

	private DefaultTreeModel modeloArbolcursos;
	private JMenuBar menu;
	private JMenu mArchivo;
	private JTextField txtFiltrarPorAno;
	private JButton btnFiltrarPorAno;
	private JButton btnInsertarAsignatura;
	private JButton btnExportar;
	private JButton btnReset;

	public Gestion() {

		dbManager = new Clases.DBManager();

		asignaturasJTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(asignaturasJTable);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		try {
			asignaturas = dbManager.getAsignaturas();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		asignaturasJTable.setModel(new AsignaturasTableModel(asignaturas));

		btnInsertarAsignatura = new JButton("InsertarAsignatura");
		scrollPane.setRowHeaderView(btnInsertarAsignatura);

		carreraFilter = new JTextField("Introduce el nombre de la carrera");
		JButton btnCarrera = new JButton("FILTRAR POR CARRERA");

		informacionLabel = new JLabel("-");
		JPanel pNorte = new JPanel();

		pNorte.add(carreraFilter);
		pNorte.add(btnCarrera);
		getContentPane().add(pNorte, BorderLayout.NORTH);

		txtFiltrarPorAno = new JTextField();
		txtFiltrarPorAno.setText("Introduce el año");
		pNorte.add(txtFiltrarPorAno);
		txtFiltrarPorAno.setColumns(10);



		btnFiltrarPorAno = new JButton("FILTRAR POR ANO");
		pNorte.add(btnFiltrarPorAno);

		btnReset = new JButton("RESET");
		pNorte.add(btnReset);
		
		btnExportar = new JButton("Exportar");
		getContentPane().add(btnExportar, BorderLayout.SOUTH);

		btnExportar.addActionListener(e -> {
			escribirFichero();
		});

		btnCarrera.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String carrera = carreraFilter.getText();
				List<Asignatura> asignaturas;
				try {
					asignaturas = DBManager.getAsignaturaAno(carrera);
					updateUI(asignaturas);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnFiltrarPorAno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String carrera = carreraFilter.getText();
				List<Asignatura> asignaturas;
				try {
					int ano = Integer.parseInt(txtFiltrarPorAno.getText());
					asignaturas = DBManager.getAsignaturaAno(ano);
					updateUI(asignaturas);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnInsertarAsignatura.addActionListener(e -> { // java funcional
			new AnadirAsignatura().addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosed(java.awt.event.WindowEvent windowEvent) {

					try {
						Gestion.this.asignaturas = DBManager.getAsignaturas();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					updateUI(asignaturas);
				}
			});
			;
		});

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Gestion.this.asignaturas = DBManager.getAsignaturas();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				updateUI(asignaturas);

			}

		});

		setVisible(true);

	}

	private void updateUI(List<Asignatura> asignaturas) {
		asignaturasJTable.setModel(new AsignaturasTableModel(asignaturas));
		informacionLabel.setText(String.format("%d launches", asignaturas.size()));
	}

	private void escribirFichero() {
		try {
			FileWriter myWriter = new FileWriter("exported.txt");
			for (Asignatura asignatura : asignaturas) {
				String line = String.format("%d;%d;%s;%s;%s", asignatura.getAnoAcademico(), asignatura.getCreditos(),
						asignatura.getNomAsignatura(), asignatura.getNomprofesor(), asignatura.getCarrera());
				myWriter.write(line + "\n");
			}
			myWriter.close();
			System.out.println("Escrito.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
