package service;

import abonne.Abonne;
import document.AbstractDocument;

public class ServiceEmprunt extends AbstractService {

	@Override
	public void run() {
		try {
			super.openFlow();
			String login = "EMPRUNT - Veuillez saisir \"numéro_abonné : numéro_document\"";

			while(true)
			{
				String response = logAndDoc(login);
				if(response.equalsIgnoreCase("exit")) //Ârrêt du service
					return;
				else if(response.equalsIgnoreCase("error")) //Format invalide, on recommence
					continue;

				int[] aboAndDoc = super.parseLogAndDoc(response);
				response = this.emprunter(aboAndDoc[0],aboAndDoc[1]);
				super.sendRequest(response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String emprunter(int numDoc, int numAbo) throws Exception
	{
		String response;
		AbstractDocument document = super.listeDocument.findDoc(numDoc);
		Abonne abonne = super.listeAbonne.findAbo(numAbo);

		if(document.seeState().equalsIgnoreCase("Disponible")||(document.seeState().equalsIgnoreCase("Réservé")&&(document.getNumAbo()==abonne.getNumAbo())))
			response = "Document emprunté avec succès";

		else
			response = "Document déjà emprunté !";

		document.emprunt(abonne);

		return response;
	}

}
