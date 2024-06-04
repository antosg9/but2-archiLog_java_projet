package document;

import abonne.Abonne;
import stockage.LogDocument;

public class Dvd extends AbstractDocument{

	private boolean adulte; //True interdit aux mineurs

	public Dvd(int numDoc, String titre, String typeDoc, int numAbo, String stateBase, boolean adulte)
	{
		super(numDoc, titre, Dvd.class.getSimpleName(), numAbo, stateBase);
		this.adulte=adulte;
	}

	public boolean isAdult(Abonne ab)
	{
		if(this.adulte && !ab.isAdult())
		{
			LogDocument.getInstance().addLog("L'abonné n°"+ab.getNumAbo()+" n'a pas l'âge pour le DVD n°"+this.numero());
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

	@Override
	public String[] getTable()
	{
		String[] tableToReturn = new String[super.getTable().length+1];

		for(int i=0; i<tableToReturn.length;i++)
		{
			if(i!=tableToReturn.length-1)
				tableToReturn[i]=super.getTable()[i];
			else
			{
				if(this.adulte)
					tableToReturn[i]="1";
				else
					tableToReturn[i]="0";
			}
		}

		return tableToReturn;
	}

}
