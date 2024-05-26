package document;

import abonne.Abonne;
import document.state.DisponibleState;
import document.state.State;

public abstract class AbstractDocument implements Document{

	private int numeroDocument;
	private	int numAbo;
	private String titre;
	private String type; //Type de document livre dvd ou autre
	private State state;

	private static int compteurDocument;


	public AbstractDocument(String titre, String type)
	{
		this.numeroDocument=compteurDocument++;
		this.numAbo=0;
		this.titre=titre;
		this.type=type;
		this.state= new DisponibleState(this);
	}

	public void setState(State state)
	{
		this.state=state;
	}

	@Override
	public int numero() {
		return this.numeroDocument;
	}

	@Override
	public void reservation(Abonne ab)
	{
		if(state.getStateName().equals("Disponible"))
		{
			state.reserver();
			this.numAbo=ab.getIdAbone();
		}
	};

	@Override
	public void emprunt(Abonne ab)
	{
		if(state.getStateName().equals("Disponible")||(state.getStateName().equals("Réservé")&&ab.getIdAbone()==numAbo))
		{
			state.emprunter();
			this.numAbo=ab.getIdAbone();
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
