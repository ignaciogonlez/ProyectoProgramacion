package Clases;

public class ExcepcionAlud extends Exception {
	private String mensaje;

	public ExcepcionAlud(String msg) {
		this.mensaje = msg;
	}

	public String toString() {
		return mensaje;
	}
}
