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

	public static Connection conn;

	/**
	 * Método que abre la BD.
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
	 * Método que cierra la BD.
	 * 
	 * @throws SQLException error con la BD
	 */
	public static void disconnect() throws SQLException {
		conn.close();
		logger.log(Level.FINE, "Conexión cerrada con la BD");
	}

	/**
	 * Método que crea las tablas de la BD.
	 * @throws ExcepcionAlud
	 */
	public static void crearTablas() throws ExcepcionAlud {
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
					throw new ExcepcionAlud(e.getMessage());
				}
			}
		}
	}

	/**
	 * Método que añade una asignatura a la tabla asignaturas de la BD.
	 * @param asig
	 * @throws ExcepcionAlud
	 */
	public static void anadirAsignatura(Asignatura asig) throws ExcepcionAlud {
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
					throw new ExcepcionAlud(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Método que devuelve una lista de todas las asignaturas de la tabla asignaturas ordenadas por año academico.
	 * @return
	 * @throws SQLException
	 */
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
	 * Método que devuelve todas las asignaturas de un año académico en especial.
	 * @param anoAcademico
	 * @return lista de asignaturas
	 * @throws SQLException
	 */
	public static List<Asignatura> getAsignaturaAno(int anoAcademico) throws ExcepcionAlud {
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
					throw new ExcepcionAlud(e.getMessage());
				}
			}
		}
		return asignaturas;
	}

	/**
	 * Método que devuelve las asignaturas de una carrera.
	 * @param carrera
	 * @return
	 * @throws ExcepcionAlud
	 */
	public static List<Asignatura> getAsignaturaAno(String carrera) throws ExcepcionAlud {
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
					throw new ExcepcionAlud(e.getMessage());
				}
			}
		}
		return asignaturas;
	}

	/**
	 * Método que sirve para añadir un nuevo admin a la BD.
	 * @param dni
	 * @param contrasena
	 * @throws ExcepcionAlud
	 */
	public static void anadirAdmin(String dni, String contrasena) throws ExcepcionAlud {
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
					throw new ExcepcionAlud(e.getMessage());
				}
			}
		}
	}

	/**
	 * Método que devuelve todos los admins de la tabla admin de la BD.
	 * @return
	 * @throws ExcepcionAlud
	 */
	public static ArrayList<Admin> obtenerAdmin() throws ExcepcionAlud {
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
					throw new ExcepcionAlud(e.getMessage());
				}
			}
		}
		return aAdmin;
	}
	
	/**
	 * Método que elimina un admin de la tabla BD.
	 * @param dni
	 * @param contrasena
	 * @throws ExcepcionAlud
	 */
	public static void eliminarAdmin(String dni, String contrasena) throws ExcepcionAlud {
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
					throw new ExcepcionAlud(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Método que devuelve el admin con ese dni y contraña pass de la tabla admin.
	 * @param dni
	 * @param pass
	 * @return
	 * @throws ExcepcionAlud
	 */
	public static ArrayList<Admin> obtenerAdmin(String dni, String pass) throws ExcepcionAlud {
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
					throw new ExcepcionAlud(e.getMessage());
				}
			}
		}
		return aAdmin;
	}
	
	/**
	 * Método que devuelve el admin de la tabla admin de la BD con ese dni.
	 * @param dni
	 * @return
	 * @throws ExcepcionAlud
	 */
	public static ArrayList<Admin> obtenerAdmin(String dni) throws ExcepcionAlud {
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
					throw new ExcepcionAlud(e.getMessage());
				}
			}
		}
		return aAdmin;
	}
}
