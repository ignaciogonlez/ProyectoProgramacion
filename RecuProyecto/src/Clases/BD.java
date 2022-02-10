package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BD {
	
	private static Connection conn;
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		
		conn = DriverManager.getConnection("jdbc:sqlite:basedatos.bd");
	}
	
	public void disconnect() throws SQLException {
		conn.close();
	}
	
	
	/**
	 * 
	 * @param con inicia la conexion
	 * @return Crea las tablas de la BD
	 */
	public static void crearTablas() {
		String sent1 = "CREATE TABLE IF NOT EXISTS Alumno(nom String, dni String, grado String, anoAcademico Integer)";
		String sent2 = " TABLE IF NOT EXISTS Asignatura(id Integer, nom String, Profesor String, creditos Integer, anoAcademico Integer)";
		String sent3 = "CREATE TABLE IF NOT EXISTS Admin(dni String, contrasena String)";
		Statement st = null;
		
		try {
			st = conn.createStatement();
			st.executeUpdate(sent1);
			st.executeUpdate(sent2);
			st.executeUpdate(sent3);
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
	 * 
	 * @param con inicia la conexion
	 * @return Lista de todas las asiganturas de la BD ordenadas por año academico
	 */
	public static ArrayList<Asignatura> obtenerAsignaturas(){
		String sent = "SELECT * FROM Asignatura order by anoAcademico ";
		Statement st = null;
		ArrayList<Asignatura> aAsignaturas = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				int e = rs.getInt("id");
				String n = rs.getString("nom");
				String a = rs.getString("Profesor");
				int s = rs.getInt("creditos");
				int t = rs.getInt("anoAcademico");
				Asignatura asig = new Asignatura(e, n, a, s,t);
				aAsignaturas.add(asig);
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
		return aAsignaturas;
	}
	
	
	/**
	 * 
	 * @param con
	 * @return lista de todos los alumnos de la BD ordenados por año academico
	 */
	public static ArrayList<Alumno> obtenerAlumnos(){
		String sent = "SELECT * FROM Alumno order by anoAcademico ";
		Statement st = null;
		ArrayList<Alumno> aAlumnos = new ArrayList<>();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String n = rs.getString("nom");
				String e = rs.getString("dni");
				String s = rs.getString("grado");
				int t = rs.getInt("anoAcademico");
				Alumno al = new Alumno(n, e, s, t);
				aAlumnos.add(al);
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
		return aAlumnos;
	}
	
	
	/**
	 * Añade un alumno a la BD
	 * @param con
	 * @param nom nombre del alumno  
	 * @param dni dni del alumno
	 * @param grado grado al que se inscribe
	 * @param anoAcademico curso academico
	 */
	public static void anadirAlumno(String nom, String dni, String grado, int anoAcademico) {
		String sent = " INSERT INTO Alumno VALUES('"+nom+"','"+ dni+"','"+grado+ "','"+anoAcademico+"')";
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
	 * elimina un alumno de la BD
	 * @param con
	 * @param nom nombre del alumno
	 * @param dni dni del alumno
	 * @param grado grado del alumno
	 * @param anoAcademico anoAcademico del alumno
	 */
	public static void eliminarAlumno(String nom, String dni, String grado, int anoAcademico) {
		String sent = "DELETE FROM Alumno WHERE nom='"+nom+"' AND dni='"+dni+"' AND grado='"+grado+"' anoAcademico='"+anoAcademico+"'";
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
	 * añade una asigantura a la BD
	 * @param con
	 * @param id
	 * @param nom
	 * @param profesor
	 * @param creditos
	 * @param anoAcademico
	 */
	public static void anadirAsignatura(int id, String nom, String profesor, int creditos, int anoAcademico) {
		String sent = " INSERT INTO Asignatura VALUES('"+id+"','"+ nom+"','"+profesor+ "','"+creditos+ "','"+anoAcademico+"')";
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
	 * elimina una asignatura de la BD
	 * @param con
	 * @param id
	 * @param nom
	 * @param profesor
	 * @param creditos
	 * @param anoAcademico
	 */
	public static void eliminarAsignatura(int id, String nom, String profesor, int creditos, int anoAcademico) {
		String sent = "DELETE FROM Asignatura WHERE id='"+id+"' AND nom='"+nom+"' AND profesor='"+profesor+"' creditos='"+creditos+"' AND anoAcademico='"+anoAcademico+"'";
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
	
	
	public static ArrayList<Admin> obtenerAdmin(String dni){
		String sent = "SELECT * FROM Admin WHERE dni='"+dni+"';";
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