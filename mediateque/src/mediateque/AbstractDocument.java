package mediateque;

public abstract class AbstractDocument implements Document{
	
	private int numero;
	
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
