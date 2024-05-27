package document;

import abonne.Abonne;
import document.state.*;

public abstract class AbstractDocument implements Document{

	private int numDocument;
	private	int numAbo;
	private String titre;
	private String type; //Type de document livre dvd ou autre
	private State state;


	public AbstractDocument(int numDocument, String titre, String type, int numAbo, String stateBase)
	{
		this.numDocument=numDocument;
		this.titre=titre;
		this.type=type;
		this.numAbo=numAbo;
		
		
		//Récupération de l'état du document
		if(stateBase.equalsIgnoreCase("Disponible"))
			this.state= new DisponibleState(this);
		else if(stateBase.equalsIgnoreCase("Réservé"))
			this.state = new ReserveState(this);
		else if(stateBase.equalsIgnoreCase("Emprunté"))
			this.state = new EmprunteState(this);
	}

	public void setState(State state)
	{
		this.state=state;
	}

	@Override
	public int numero() {
		return this.numDocument;
	}

	@Override
	public void reservation(Abonne ab)
	{
		if(state.getStateName().equals("Disponible"))
		{
			state.reserver();
			this.numAbo=ab.getNumAbo();
		}
	};

	@Override
	public void emprunt(Abonne ab)
	{
		if(state.getStateName().equals("Disponible")||(state.getStateName().equals("Réservé")&&ab.getNumAbo()==numAbo))
		{
			state.emprunter();
			this.numAbo=ab.getNumAbo();
		}
	};

	@Override
	public void retour() {
		if(state.getStateName().equals("Emprunté"))
		{
			state.rendre();
			this.numAbo=0;
		}
	}

}
