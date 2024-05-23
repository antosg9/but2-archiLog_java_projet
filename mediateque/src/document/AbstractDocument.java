package document;

import main.Abonne;

public abstract class AbstractDocument implements Document{
	
	private int numeroDocument;
	private static int compteurDocument;
	private String titre;
	
	public AbstractDocument(String titre)
	{
		this.numero=compteurDocument++;
		this.titre=titre;
	}
	
	@Override
	public int getNumeroDocument() {
		return this.numeroDocument;
	}

	@Override
	public abstract void reservation(Abonne ab);

	@Override
	public abstract void emprunt(Abonne ab);

	@Override
	public void retour() {
		// TODO Auto-generated method stub
	}

}
