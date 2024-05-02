package document;

import main.Abonne;

public class Dvd extends AbstractDocument{
	
	private boolean adulte;
	
	public Dvd(int numero, String titre, boolean adulte)
	{
		super(numero, titre);
		this.adulte=adulte;
	}

	@Override
	public void reservation(Abonne ab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emprunt(Abonne ab) {
		// TODO Auto-generated method stub
		
	}

}
