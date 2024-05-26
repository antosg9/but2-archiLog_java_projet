package document;

import abonne.Abonne;

public class Dvd extends AbstractDocument{
	
	private boolean adulte;
	
	public Dvd(String titre, boolean adulte)
	{
		super(titre);
		this.adulte=adulte;
	}
	
	private boolean getAdulte() {
		if(this.adulte)
			System.out.println("Réservé aux plus de 16 ans !");
		return this.adulte;
	}
	@Override
	public void reservation(Abonne ab) {
		if(this.adulte && !(ab.isAdult()))
			System.out.println("Emprunt interdit aux mineurs !");
		// TODO Auto-generated method stub
	}

	@Override
	public void emprunt(Abonne ab) {
		// TODO Auto-generated method stub
		
	}

}
