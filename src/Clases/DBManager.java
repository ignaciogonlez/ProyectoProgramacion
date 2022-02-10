package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
	private static Logger logger = Logger.getLogger("DBManager");

	private static Connection conn;

	/**
	 * Abre la BD
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException           error con la BD
	 */
	public static void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		logger.log(Level.FINE, "Estableciendo conexion con la bd");
		conn = DriverManager.getConnection("jdbc:sqlite:basedatos.bd");
		logger.log(Level.FINE, "Establecida la conexión con la bd");
	}

	/**
	 * Cierra la BD
	 * 
	 * @throws SQLException error con la BD
	 */
	public static void disconnect() throws SQLException {
		conn.close();
		logger.log(Level.FINE, "Conexión cerrada con la BD");
	}

	/**
	 * Obtiene la lista de todas las asignaturas de la BD.
	 * 
	 * @return lista de todos las asignaturas de la base de datos.
	 * @throws SQLException error al intentar obtener la lista .
	 */

	public static void crearTablas() {
		logger.log(Level.FINE, "Creando tablas");

		String sent = "CREATE TABLE IF NOT EXISTS admin(dni String, contrasena String)";
		String sent2 = " CREATE TABLE IF NOT EXISTS asignaturas(anoAcademico Integer, nomAsignatura String, nomprofesor String, creditos Integer, carrera String)";
		Statement st = null;

		try {
			st = conn.createStatement();
			st.executeUpdate(sent);
			st.executeUpdate(sent2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	
	 */
	public static void anadirAsignatura(Asignatura asig) {
		logger.log(Level.FINE, "Añadiendo asignatura: " + asig);

		String sent = " INSERT INTO asignaturas VALUES('" + asig.getAnoAcademico() + "','" + asig.getNomAsignatura()
				+ "','" + asig.getNomprofesor() + "','" + asig.getCreditos() + "','" + asig.getCarrera() + "')";
		Statement st = null;

		try {
			st = conn.createStatement();
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static List<Asignatura> getAsignaturas() throws SQLException {
		logger.log(Level.FINE, "Buscando todas las asignaturas...");

		List<Asignatura> asignaturas = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			try (ResultSet rs = stmt.executeQuery("SELECT * FROM asignaturas order by anoAcademico")) {
				while (rs.next()) {
					Asignatura asig = new Asignatura(rs.getInt("anoAcademico"), rs.getString("nomAsignatura"),
							rs.getString("nomprofesor"), rs.getInt("creditos"), rs.getString("carrera"));

					asignaturas.add(asig);
				}
			}
		}

		return asignaturas;
	}

	/**
	 * 
	 * @param anoAcademico
	 * @return asignaturas de el param anoAcademico
	 * @throws SQLException
	 */
	public static List<Asignatura> getAsignaturaAno(int anoAcademico) throws SQLException {
		logger.log(Level.FINE, "Buscando asignaturas con anoAcademico: " + anoAcademico);

		String sent = "SELECT * FROM asignaturas WHERE anoAcademico='" + anoAcademico + "';";
		Statement st = null;
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while (rs.next()) {
				Asignatura asig = new Asignatura(rs.getInt("anoAcademico"), rs.getString("nomAsignatura"),
						rs.getString("nomprofesor"), rs.getInt("creditos"), rs.getString("carrera"));

				asignaturas.add(asig);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return asignaturas;
	}

	/**
	 * 
	 * @param
	 * @return asignaturas de el param carrera
	 * @throws SQLException
	 */
	public static List<Asignatura> getAsignaturaAno(String carrera) throws SQLException {
		logger.log(Level.FINE, "Buscando asignaturas con carrera: " + carrera);

		String sent = "SELECT * FROM asignaturas WHERE carrera='" + carrera + "';";
		Statement st = null;
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while (rs.next()) {
				Asignatura asig = new Asignatura(rs.getInt("anoAcademico"), rs.getString("nomAsignatura"),
						rs.getString("nomprofesor"), rs.getInt("creditos"), rs.getString("carrera"));

				asignaturas.add(asig);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return asignaturas;
	}

	/**
	 * Añadir un admin
	 * 
	 * @param con
	 * @param dni
	 * @param contrasena
	 */
	public static void anadirAdmin(String dni, String contrasena) {
		logger.log(Level.FINE, "Añadiendo admin con dni: " + dni);
		String sent = " INSERT INTO Admin VALUES('" + dni + "','" + contrasena + "')";
		Statement st = null;

		try {
			st = conn.createStatement();
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * listado de admins
	 * 
	 * @param con
	 * @return
	 */
	public static ArrayList<Admin> obtenerAdmin() {
		logger.log(Level.FINE, "Buscando admins...");
		String sent = "SELECT * FROM Admin";
		Statement st = null;
		ArrayList<Admin> aAdmin = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while (rs.next()) {
				String n = rs.getString("dni");
				String a = rs.getString("contrasena");
				Admin ad = new Admin(n, a);
				aAdmin.add(ad);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return aAdmin;
	}

	public static void eliminarAdmin(String dni, String contrasena) {
		logger.log(Level.FINE, "Eliminar admin con dni: " + dni);

		String sent = "DELETE FROM Admin WHERE dni='" + dni + "' AND contrasena='" + contrasena + "'";
		Statement st = null;

		try {
			st = conn.createStatement();
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static ArrayList<Admin> obtenerAdmin(String dni, String pass) {
		logger.log(Level.FINE, "Obteniendo admin con dni: " + dni);

		String sent = String.format("SELECT * FROM Admin WHERE dni='%s' AND contrasena='%s';", dni, pass);
		Statement st = null;
		ArrayList<Admin> aAdmin = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while (rs.next()) {
				String n = rs.getString("dni");
				String a = rs.getString("contrasena");
				Admin ad = new Admin(n, a);
				aAdmin.add(ad);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return aAdmin;
	}

	public static ArrayList<Admin> obtenerAdmin(String dni) {
		logger.log(Level.FINE, "Obteniendo admin con dni: " + dni);

		String sent = String.format("SELECT * FROM Admin WHERE dni='%s';", dni);
		Statement st = null;
		ArrayList<Admin> aAdmin = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while (rs.next()) {
				String n = rs.getString("dni");
				String a = rs.getString("contrasena");
				Admin ad = new Admin(n, a);
				aAdmin.add(ad);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return aAdmin;
	}
}
