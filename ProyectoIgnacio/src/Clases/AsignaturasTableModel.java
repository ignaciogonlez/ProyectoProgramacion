package Clases;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AsignaturasTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<String> headers = Arrays.asList( "anoAcademico", "nomAsignatura", "nomprofesor", "creditos", "carrera");
	private List<Asignatura> aAsignatura;

	public AsignaturasTableModel(List<Asignatura> aAsignatura) {
		this.aAsignatura = aAsignatura;
	}
	
	public String getColumnName(int column) {
		return headers.get(column);
	}

	public int getRowCount() {
		return aAsignatura.size();
	}

	public int getColumnCount() {
		return headers.size(); 
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Asignatura a = aAsignatura.get(rowIndex);
		switch (columnIndex) {
			case 0: return a.getAnoAcademico();
			case 1: return a.getNomAsignatura();
			case 2: return a.getNomprofesor();
			case 3: return a.getCreditos();
			case 4: return a.getCarrera();
			default: return null;
		}
	}
}
