package abonne;

import java.util.Date;

public class Abonne{

	private int numAbo;
	private String nom;
	private Date dateNaiss;
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
	
	public String[] getTable()
	{
		String[] tableToReturn = {String.valueOf(this.numAbo),this.nom,String.valueOf(this.dateNaiss)};
		return tableToReturn;
	}
}