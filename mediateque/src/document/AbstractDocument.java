package document;

import abonne.Abonne;
import document.state.*;
import stockage.LogDocument;

public abstract class AbstractDocument implements Document{

	private int numDoc;
	private String titre;
	private String typeDoc;
	private	int numAbo;
	private State stateDoc;
	
	private String heureFinReservation;
	private LogDocument log = LogDocument.getInstance(); 
	
	public void setFinReservation(String finReservation)
	{
		this.heureFinReservation=finReservation;
	}
	
	public String getFinReservation()
	{
		return this.heureFinReservation;
	}
	
	public String getType()
	{
		return this.typeDoc;
	}

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
	
	public String seeState()
	{
		return this.stateDoc.getStateName();
	}

	public void setState(State state)
	{
		this.stateDoc=state;
	}
	
	public int getNumAbo()
	{
		return this.numAbo;
	}

	@Override
	public int numero() {
		return this.numDoc;
	}

	@Override
	public void reservation(Abonne ab)
	{
		if(stateDoc.getStateName().equalsIgnoreCase("Disponible"))
		{
			this.numAbo=ab.getNumAbo();
		}
		
		String event = "L'abonné numéro "+ab.getNumAbo() + stateDoc.reserver() + this.numDoc;
		log.addLog(event);
	};

	@Override
	public void emprunt(Abonne ab)
	{
		if(stateDoc.getStateName().equals("Disponible")||(stateDoc.getStateName().equals("Réservé")&&ab.getNumAbo()==numAbo))
		{
			this.numAbo=ab.getNumAbo();
		}
		
		String event = "L'abonné numéro "+ab.getNumAbo()+stateDoc.emprunter()+this.numDoc;
		log.addLog(event);
	};

	@Override
	public void retour() {
		if(stateDoc.getStateName().equals("Emprunté"))
		{
			this.numAbo=0;
		}
		
		String event = "Le document n°"+this.numDoc+stateDoc.rendre();
		log.addLog(event);
	}
	
	public String[] getTable()
	{
		String strAbo;
		if(this.numAbo==0)
			strAbo = "null";
		else
			strAbo =String.valueOf(this.numAbo);
		
		String[] tableToReturn = {String.valueOf(this.numDoc),this.titre,this.typeDoc,strAbo,this.stateDoc.getStateName()};
		return tableToReturn;
	}
}
