package Clases;

import java.time.LocalDateTime;


public class Asignatura {
	
	private int anoAcademico;
	private String nomAsignatura;
	private String nomprofesor;
	private int creditos;
	private String carrera;
	
	
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

	
}
