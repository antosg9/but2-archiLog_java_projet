package document;

import main.Abonne;

public abstract class AbstractDocument implements Document{
	
	private int numero;
	private String titre;
	
	public AbstractDocument(int numero, String titre)
	{
		this.numero=numero;
		this.titre=titre;
	}
	
	@Override
	public int numero() {
		return this.numero;
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
