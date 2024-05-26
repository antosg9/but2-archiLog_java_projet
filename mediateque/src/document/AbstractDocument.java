package document;

import abonne.Abonne;

public abstract class AbstractDocument implements Document{
	
	private int numeroDocument;
	private static int compteurDocument;
	private String titre;
	private String type; //Type de document livre dvd ou autre
	
	public AbstractDocument(String titre, String type)
	{
		this.numeroDocument=compteurDocument++;;
		this.titre=titre;
		this.type=type;
	}
	
	@Override
	public int numero() {
		return this.numeroDocument;
	}

	@Override
	public abstract void reservation(Abonne ab);

	@Override
	public abstract void emprunt(Abonne ab);

	@Override
	public void retour() {
		// Même méthode pour tout le monde
	}

}
