package tests;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Admin;
import Clases.DBManager;

public class BDTest {

	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		DBManager.connect();
	}

	@Test
	public void testAddAdmin() {

		String dni = "11111111f";
		String pass = "12345";

		DBManager.anadirAdmin(dni, pass);
		List<Admin> admins = DBManager.obtenerAdmin();
		assertTrue(admins.get(admins.size() - 1).getDni().equals(dni));
	}

	@Test
	public void testDeleteAdmin() {

		String dni = "11111111f";
		String pass = "12345";

		List<Admin> adminsVIejos = DBManager.obtenerAdmin();
		DBManager.eliminarAdmin(dni, pass);
		List<Admin> adminsNuevos = DBManager.obtenerAdmin();

		assertTrue(adminsNuevos.size() == adminsVIejos.size() - 1);
	}

	@After
	public void tearDown() throws SQLException {
		DBManager.disconnect();
	}
}
