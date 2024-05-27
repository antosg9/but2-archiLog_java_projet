package document;

import abonne.Abonne;

public class Dvd extends AbstractDocument{
	
	private boolean adulte; //True interdit aux mineurs
	
	public Dvd(int numDocument, String titre, String type, int numAbo, String stateBase, boolean adulte)
	{
		super(numDocument, titre, Dvd.class.getSimpleName(), numAbo, stateBase);
		this.adulte=adulte;
	}
	
	private boolean isAdult(Abonne ab)
	{
		if(this.adulte && !ab.isAdult())
		{
			System.out.println("Vous n’avez pas l’âge pour ce DVD !");
			return false;
		}
		
		return true;
	}
	
	@Override
	public void reservation(Abonne ab) {
		if(isAdult(ab))
			super.reservation(ab);
	}

	@Override
	public void emprunt(Abonne ab) {
		if(isAdult(ab))
			super.emprunt(ab);
		
	}

}
