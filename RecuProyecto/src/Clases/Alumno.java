package Clases;

public class Alumno {
	
	private String nom;
	private String dni;
	private String grado;
	private Integer anoAcademico;
	

	
	public Alumno(String nom, String dni, String grado, Integer anoAcademico) {
		super();
		this.nom = nom;
		this.dni = dni;
		this.grado = grado;
		this.anoAcademico = anoAcademico;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public Integer getAnoAcademico() {
		return anoAcademico;
	}
	public void setAnoAcademico(Integer anoAcademico) {
		this.anoAcademico = anoAcademico;
	}
	
	
	
}
