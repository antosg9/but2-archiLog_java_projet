package document;

import abonne.Abonne;
import document.state.*;

public abstract class AbstractDocument implements Document{

	private int numDoc;
	private	int numAbo;
	private String titre;
	private String typeDoc;
	private State stateDoc;

	public AbstractDocument(int numDoc, String titre, String typeDoc, int numAbo, String stateBase)
	{
		this.numDoc=numDoc;
		this.titre=titre;
		this.typeDoc=typeDoc;
		this.numAbo=numAbo;

		//Récupération de l'état du document
		if(stateBase.equalsIgnoreCase("Disponible"))
			this.stateDoc= new DisponibleState(this);
		else if(stateBase.equalsIgnoreCase("Réservé"))
			this.stateDoc = new ReserveState(this);
		else if(stateBase.equalsIgnoreCase("Emprunté"))
			this.stateDoc = new EmprunteState(this);
	}

	public void setState(State state)
	{
		this.stateDoc=state;
	}

	@Override
	public int numero() {
		return this.numDoc;
	}

	@Override
	public void reservation(Abonne ab)
	{
		System.out.println(stateDoc.reserver());
		if(stateDoc.getStateName().equals("Disponible"))
		{
			this.numAbo=ab.getNumAbo();
		}
	};

	@Override
	public void emprunt(Abonne ab)
	{
		System.out.println(stateDoc.emprunter());
		if(stateDoc.getStateName().equals("Disponible")||(stateDoc.getStateName().equals("Réservé")&&ab.getNumAbo()==numAbo))
		{
			this.numAbo=ab.getNumAbo();
		}
	};

	@Override
	public void retour() {
		System.out.println(stateDoc.rendre());
		if(stateDoc.getStateName().equals("Emprunté"))
		{
			this.numAbo=0;
		}
	}
}
