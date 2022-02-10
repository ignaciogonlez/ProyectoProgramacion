package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;





public class DBManager {
	
	private static Connection conn; 
	
	/**
	 * Abre la BD
	 * @throws ClassNotFoundException
	 * @throws SQLException error con la BD
	 */
	public static void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		
		conn = DriverManager.getConnection("jdbc:sqlite:basedatos.bd");
	}
	
	/**
	 * Cierra la BD
	 * @throws SQLException error con la BD
	 */
	public static void disconnect() throws SQLException {
		conn.close();
	}
	
	/**
	 * Obtiene la lista de todas las asignaturas de la BD.
	 * @return lista de todos las asignaturas de la base de datos.
	 * @throws SQLException error al intentar obtener la lista . 
	 */
	
	
	public static void crearTablas() {
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
			if(st!=null) {
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
	 * añade una asigantura a la BD
	 * @param con
	 * @param id
	 * @param nom
	 * @param profesor
	 * @param creditos
	 * @param anoAcademico
	 */
	public static void anadirAsignatura(int anoAcademico, String nomAsignatura, String nomprofesor, int creditos, String carrera) {
		String sent = " INSERT INTO asignaturas VALUES('"+anoAcademico+"','"+ nomAsignatura+"','"+nomprofesor+ "','"+creditos+ "','"+carrera+"')";
		Statement st = null;
		
		try {
			st = conn.createStatement();
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
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
		List<Asignatura> asignaturas = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			try (ResultSet rs = stmt.executeQuery("SELECT * FROM asignaturas order by anoAcademico")) {
				while(rs.next()) {
					Asignatura asig = new Asignatura(
						rs.getInt("anoAcademico"),
						rs.getString("nomAsignatura"),
						rs.getString("nomprofesor"),
						rs.getInt("creditos"),
						rs.getString("carrera")
					);
					
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
		String sent = "SELECT * FROM asignaturas WHERE anoAcademico='"+anoAcademico+"';";
		Statement st = null;
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				Asignatura asig = new Asignatura(
						rs.getInt("anoAcademico"),
						rs.getString("nomAsignatura"),
						rs.getString("nomprofesor"),
						rs.getInt("creditos"),
						rs.getString("carrera")
					);
					
					asignaturas.add(asig);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
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
		String sent = "SELECT * FROM asignaturas WHERE carrera='"+carrera+"';";
		Statement st = null;
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				Asignatura asig = new Asignatura(
						rs.getInt("anoAcademico"),
						rs.getString("nomAsignatura"),
						rs.getString("nomprofesor"),
						rs.getInt("creditos"),
						rs.getString("carrera")
					);
					
					asignaturas.add(asig);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
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
	 * @param con
	 * @param dni
	 * @param contrasena
	 */
	public static void anadirAdmin(String dni, String contrasena) {
		String sent = " INSERT INTO Admin VALUES('"+dni+"','"+ contrasena+"')";
		Statement st = null;
		
		try {
			st = conn.createStatement();
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
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
	 * @param con
	 * @return
	 */
	public static ArrayList<Admin> obtenerAdmin(){
		String sent = "SELECT * FROM Admin";
		Statement st = null;
		ArrayList<Admin> aAdmin = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String n = rs.getString("dni");
				String a = rs.getString("contrasena");
				Admin ad = new Admin(n, a);
				aAdmin.add(ad);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
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
		String sent = "DELETE FROM Admin WHERE dni='"+dni+"' AND contrasena='"+contrasena+"'";
		Statement st = null;
		
		try {
			st = conn.createStatement();
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static ArrayList<Admin> obtenerAdmin(String dni, String pass){
		String sent = String.format("SELECT * FROM Admin WHERE dni='%s' AND contrasena='%s';", dni, pass);
		Statement st = null;
		ArrayList<Admin> aAdmin = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String n = rs.getString("dni");
				String a = rs.getString("contrasena");
				Admin ad = new Admin(n, a);
				aAdmin.add(ad);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
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
	
	public static ArrayList<Admin> obtenerAdmin(String dni){
		String sent = String.format("SELECT * FROM Admin WHERE dni='%s';", dni);
		Statement st = null;
		ArrayList<Admin> aAdmin = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String n = rs.getString("dni");
				String a = rs.getString("contrasena");
				Admin ad = new Admin(n, a);
				aAdmin.add(ad);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
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
