package Clases;

public class Admin {
	
	private String dni;
	private String contrasena;
	
	
	public Admin(String dni, String contrasena) {
		super();
		this.dni = dni;
		this.contrasena = contrasena;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	
}
