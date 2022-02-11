package Clases;

public class ExcepcionAlud extends Exception {
	private String mensaje;
	
	/**
	 * Constructor de la clase ExcepcionAlud
	 * @param msg
	 */
	public ExcepcionAlud(String msg) {
		this.mensaje = msg;
	}
	
	/**
	 * Método toString de la clase ExcepcionAlud
	 */
	public String toString() {
		return mensaje;
	}
}
