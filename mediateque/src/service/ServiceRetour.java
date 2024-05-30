package service;

import java.io.IOException;

public class ServiceRetour extends AbstractService {

	@Override
	public void run() {
		try {
			super.openFlow();
			String message = "À RENDRE - Veuillez saisir \"numéro_document\"";
			
			while(true)
			{
				String response = this.pingPongReturn(message);
				
				if(response.equalsIgnoreCase("exit")) //Ârrêt du service
					return;
				else if(response.equalsIgnoreCase("error")) //Format invalide, on recommence
					continue;

				int numDoc = Integer.parseInt(response);

				this.retourner(numDoc);
				//PENSER à renvoyer réponse au client !!!!
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private String retourner(int numDoc)
	{
		return "Document retourné avec succés !";
	}
	
	private String pingPongReturn(String message) throws IOException
	{
		super.sendRequest(message);
		String request = super.receiveRequest().trim();
		String error = "error";
		
		if(request.equalsIgnoreCase("exit")) //Arrêt du service
		{
			//Fermeture des flux
			this.closeFlow();
			return request.toLowerCase();
		}
		
		if(!(request.chars().allMatch(Character::isDigit)))
		{
			this.sendRequest("Mauvais format !"); //Erreur du client
			return error; //Demande de nouvelle valeur
		}

		int numDoc = Integer.parseInt(request);
		
		if(numDoc<=0)
		{
			this.sendRequest("Mauvais nombre !"); //Erreur du client
			return error; //Demande de nouvelle valeur
		}

		return request;
	}

}