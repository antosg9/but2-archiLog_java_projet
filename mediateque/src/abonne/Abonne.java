package abonne;

public class Abonne{
	private String nom;
	private int age;
	private int numero;
	private String dateNaiss; //Calculer l'âge à partir de la date de naissance et //ajouter un timer task qui s'éxecute tous les ans
	
	private static final int ageAdult = 16;
	private static int compteurIdAbonne = 1; //ID différent pour chaque abonné
	
	private String emprunts[]; //Tableau pour stocker les emprunts ?

	public Abonne(String nom, int age){
		this.nom = nom;
		this.age = age; //A terme le constructeur sera nom et dateNaiss
		this.numero = Abonne.compteurIdAbonne++;
	}

	public int getIdAbone(){
		return this.numero;
	}
	
	public boolean isAdult(){
		return (this.age >= Abonne.ageAdult);
	}

}