package document;

import main.Abonne;

public class Dvd extends AbstractDocument{
	
	private boolean adulte;
	
	public Dvd(String titre, boolean adulte)
	{
		super(titre);
		this.adulte=adulte;
	}
	
	private boolean adulte() {
		if(adulte)
			System.out.println("Réservé aux plus de 18 ans !");
		return adulte;
	}
	@Override
	public void reservation(Abonne ab) {
		if(adulte())
			return;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emprunt(Abonne ab) {
		// TODO Auto-generated method stub
		
	}

}
