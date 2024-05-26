package abonne;

public class Abonne{
	private String nom;
	private String dateNaiss; //Calculer l'âge à partir de la date de naissance et //ajouter un timer task qui s'éxecute tous les ans
	private int age;
	private int numero;
	
	private static final int ageAdult = 16;
	private static int compteurIdAbonne = 1; //ID différent pour chaque abonné
	
	public Abonne(String nom, String dateNaiss) throws Exception{
		this.nom = nom;
		this.dateNaiss=dateNaiss;
		this.numero = Abonne.compteurIdAbonne++;
		
		try {
			this.age = Age.getAge(this.dateNaiss);
		} catch (Exception e) {
			throw new Exception("Calcul de l'âge invalide !");
		} 
		
	}

	public int getIdAbone(){
		return this.numero;
	}
	
	public boolean isAdult(){
		return (this.age >= Abonne.ageAdult);
	}
	
	public int getAge()
	{
		return this.age;
	}

}