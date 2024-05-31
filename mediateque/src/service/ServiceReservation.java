package service;

import abonne.Abonne;
import document.AbstractDocument;

public class ServiceReservation extends AbstractService {

	@Override
	public void run() {
		try {
			super.openFlow();
			String login = "RESERVATION - Veuillez saisir \"numéro_abonné ; numéro_document\"";

			while(true)
			{
				String response = logAndDoc(login);
				if(response.equalsIgnoreCase("exit")) //Ârrêt du service
					return;
				else if(response.equalsIgnoreCase("error")) //Format invalide, on recommence
					continue;

				int[] aboAndDoc = super.parseLogAndDoc(response);

				response = this.reserver(aboAndDoc[0],aboAndDoc[1]);
				super.sendRequest(response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String reserver(int numAbo, int numDoc) throws Exception
	{
		String response;
		AbstractDocument document = super.listeDocument.findDoc(numDoc);
		Abonne abonne = super.listeAbonne.findAbo(numAbo);
		
		if(document.seeState().equalsIgnoreCase("Disponible"))
			response = "Document réservé avec succès";
		else if(document.seeState().equalsIgnoreCase("Réservé"))
			response = "Document déjà réservé !";
		else
			response = "Document déjà emprunté!";
		
		document.reservation(abonne);

		return response;
	}
}
