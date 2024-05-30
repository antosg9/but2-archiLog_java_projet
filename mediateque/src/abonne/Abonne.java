package abonne;

import java.util.Date;

public class Abonne{

	private int numAbo;
	private String nom;
	private Date dateNaiss; //ajouter un timer task qui s'éxecute tous les ans?
	private int age;

	private static final int ageAdult = 16;

	public Abonne(int numAbo, String nom, Date dateNaiss){
		this.numAbo = numAbo;
		this.nom = nom;
		this.dateNaiss=dateNaiss;
		this.age=Age.getAge(dateNaiss);
	}

	public int getNumAbo(){
		return this.numAbo;
	}

	public boolean isAdult(){
		return (this.age >= Abonne.ageAdult);
	}
}