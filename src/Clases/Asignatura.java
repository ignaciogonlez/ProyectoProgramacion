package Clases;

public class Asignatura {

	private int anoAcademico;
	private String nomAsignatura;
	private String nomprofesor;
	private int creditos;
	private String carrera;
	
	
	/**
	 * Constructor específico de la clase asignatura
	 * @param anoAcademico
	 * @param nomAsignatura
	 * @param nomprofesor
	 * @param creditos
	 * @param carrera
	 */
	public Asignatura(int anoAcademico, String nomAsignatura, String nomprofesor, int creditos, String carrera) {
		super();
		this.anoAcademico = anoAcademico;
		this.nomAsignatura = nomAsignatura;
		this.nomprofesor = nomprofesor;
		this.creditos = creditos;
		this.carrera = carrera;
	}

	public int getAnoAcademico() {
		return anoAcademico;
	}

	public void setAnoAcademico(int anoAcademico) {
		this.anoAcademico = anoAcademico;
	}

	public String getNomAsignatura() {
		return nomAsignatura;
	}

	public void setNomAsignatura(String nomAsignatura) {
		this.nomAsignatura = nomAsignatura;
	}

	public String getNomprofesor() {
		return nomprofesor;
	}

	public void setNomprofesor(String nomprofesor) {
		this.nomprofesor = nomprofesor;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
	/**
	 * Método toString de la clase asignatura
	 */
	@Override
	public String toString() {
		String toString = String.format("%s %s %d", this.getNomAsignatura(), this.getNomprofesor(), this.getCreditos());
		return toString;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Asignatura) {
			Asignatura asig = (Asignatura) obj;
			return this.getAnoAcademico() == asig.getAnoAcademico() && this.getCarrera().equals(asig.getCarrera())
					&& this.getCreditos() == asig.getCreditos()
					&& this.getNomAsignatura().equals(asig.getNomAsignatura())
					&& this.getNomprofesor().equals(asig.getNomprofesor());
		}
		return super.equals(obj);
	}

}
