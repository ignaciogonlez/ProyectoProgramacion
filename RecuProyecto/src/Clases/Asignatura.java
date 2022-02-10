package Clases;

public class Asignatura {

	private int id;
	private String nom;
	private String profesor;
	private int creditos;
	private int anoAcademico;

	
	
	public Asignatura(int id, String nom, String profesor, int creditos, int anoAcademico) {
		super();
		this.id = id;
		this.nom = nom;
		this.profesor = profesor;
		this.creditos = creditos;
		this.anoAcademico = anoAcademico;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public int getAnoAcademico() {
		return anoAcademico;
	}
	public void setAnoAcademico(int anoAcademico) {
		this.anoAcademico = anoAcademico;
	}
	
	

}
