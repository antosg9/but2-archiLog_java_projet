package abonne;

public class Abonne{
	private int numAbo;
	private String nom;
	private String dateNaiss; //Calculer l'âge à partir de la date de naissance et //ajouter un timer task qui s'éxecute tous les ans
	private int age;
	
	private static final int ageAdult = 16;
	
	public Abonne(int numAbo, String nom, String dateNaiss) throws Exception{
		this.nom = nom;
		this.dateNaiss=dateNaiss;
		this.numAbo = numAbo;
		
		try {
			this.age = Age.getAge(this.dateNaiss);
		} catch (Exception e) {
			throw new Exception("Calcul de l'âge invalide !");
		} 
		
	}

	public int getNumAbo(){
		return this.numAbo;
	}
	
	public boolean isAdult(){
		return (this.age >= Abonne.ageAdult);
	}
	
	public int getAge()
	{
		return this.age;
	}

}