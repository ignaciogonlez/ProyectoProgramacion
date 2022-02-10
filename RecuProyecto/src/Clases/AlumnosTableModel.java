package Clases;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class AlumnosTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<String> headers = Arrays.asList( "nom", "dni", "grado", "anoAcademico");
	private List<Alumno> aAlumnos;

	public AlumnosTableModel(List<Alumno> aAlumnos) {
		this.aAlumnos = aAlumnos;
	}
	
	public String getColumnName(int column) {
		return headers.get(column);
	}

	public int getRowCount() {
		return aAlumnos.size();
	}

	public int getColumnCount() {
		return headers.size(); 
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Alumno a = aAlumnos.get(rowIndex);
		switch (columnIndex) {
			case 0: return a.getNom();
			case 1: return a.getDni();
			case 2: return a.getGrado();
			case 3: return a.getAnoAcademico();
			default: return null;
		}
	}
}
