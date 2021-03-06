package tests;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Admin;
import Clases.Asignatura;
import Clases.DBManager;
import Clases.ExcepcionAlud;

public class BDTest {
	
	/**
	 * Código que se ejecuta antes de cada test
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		DBManager.connect();
	}

	/**
	 * Test de añadir un admin a la BD.
	 * @throws ExcepcionAlud
	 */
	@Test
	public void testAddAdmin() throws ExcepcionAlud {

		String dni = "11111111f";
		String pass = "12345";

		DBManager.anadirAdmin(dni, pass);
		List<Admin> admins = DBManager.obtenerAdmin();
		assertTrue(admins.get(admins.size() - 1).getDni().equals(dni));
	}

	/**
	 * Test de eliminar un admin de la BD.
	 * @throws ExcepcionAlud
	 */
	@Test
	public void testDeleteAdmin() throws ExcepcionAlud{

		String dni = "12345679F";
		String pass = "12345";
		DBManager.anadirAdmin(dni, pass);
		List<Admin> adminsVIejos = DBManager.obtenerAdmin();
		DBManager.eliminarAdmin(dni, pass);
		List<Admin> adminsNuevos = DBManager.obtenerAdmin();

		assertTrue(adminsNuevos.size() == adminsVIejos.size() - 1);
	}

	/**
	 * Test de establecimiento de la conexion con la BD.
	 * @throws SQLException
	 */
	@Test
	public void conexionEstablecida() throws SQLException {
		assertTrue(!DBManager.conn.isClosed());
	}
	
	/**
	 * Test de añadir asignatura a la tabla asignaturas de la BD.
	 * @throws SQLException
	 * @throws ExcepcionAlud
	 */
	@Test
	public void anadirAsignatura() throws SQLException, ExcepcionAlud {
		Asignatura asig = new Asignatura(2, "Programacion", "Andoni", 6, "2021");
		DBManager.anadirAsignatura(asig);
		List<Asignatura> asignaturas = DBManager.getAsignaturas();
		
		boolean found = false;
		for (Asignatura asignatura : asignaturas) {
			if (asignatura.equals(asig)) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	
	/**
	 * Test que comprueba que se ha filtrado bien por año.
	 * @throws SQLException
	 * @throws ExcepcionAlud
	 */
	@Test
	public void filtroAnyo() throws SQLException, ExcepcionAlud {
		Asignatura asig = new Asignatura(3, "Programacion", "Andoni2", 6, "2022");
		DBManager.anadirAsignatura(asig);
		List<Asignatura> asignaturas = DBManager.getAsignaturaAno(3);
		
		boolean found = false;
		for (Asignatura asignatura : asignaturas) {
			if (asignatura.equals(asig)) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	/**
	 * Test que comprueba que se ha filtrado bien por carrera.
	 * @throws ExcepcionAlud
	 */
	@Test
	public void filtroCarrera() throws ExcepcionAlud {
		Asignatura asig = new Asignatura(3, "Programacion", "Andoni2", 6, "2022");
		DBManager.anadirAsignatura(asig);
		List<Asignatura> asignaturas = DBManager.getAsignaturaAno("2022");
		
		boolean found = false;
		for (Asignatura asignatura : asignaturas) {
			if (asignatura.equals(asig)) {
				found = true;
				break;
			}
		}
		assertTrue(found);
	}

	/**
	 * Test que comprueba que se busca bien el admin.
	 * @throws ExcepcionAlud
	 */
	@Test
	public void buscarAdmin() throws ExcepcionAlud {
		String dni = "11111111f";
		String pass = "12345";

		DBManager.anadirAdmin(dni, pass);
		List<Admin> admins = DBManager.obtenerAdmin(dni);
		assertTrue(admins.get(0).getDni().equals(dni));
	}

	/**
	 * Código que se ejecuta después de cada test
	 * @throws SQLException
	 */
	@After
	public void tearDown() throws SQLException {
		DBManager.disconnect();
	}
}
