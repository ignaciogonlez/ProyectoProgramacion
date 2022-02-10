package Clases;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class TestBD {
	
	private BD dbManager;

	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		dbManager.connect();
	}
	
	public void testAnadirAlumno() {
		
		String nom = "Juan Perez";
		String dni = "11111111f";
		String grado = "Derecho" ;
		int anoAcademico = 1;
		
		dbManager.anadirAlumno(nom, dni, grado, anoAcademico);
		List<Alumno> alumnos = dbManager.obtenerAlumnos();
		assertTrue(alumnos.get(-1).getDni().equals(dni));
	}
	
	public void testEliminarAlumno() {
			
		List<Alumno> alumnosViejo= dbManager.obtenerAlumnos();
		String nom = "Juan Perez";
		String dni = "11111111f";
		String grado = "Derecho" ;
		int anoAcademico = 1;
		List<Alumno> alumnosActual = dbManager.obtenerAlumnos();
		dbManager.eliminarAlumno(nom, dni, grado, anoAcademico);
		
		assertTrue(alumnosActual.size()==alumnosViejo.size()-1);
	}
	
	@After
	public void tearDown() throws SQLException {
		dbManager.disconnect();
	}

}
