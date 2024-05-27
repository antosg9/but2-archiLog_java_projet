package service;

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

				this.reserver(aboAndDoc[0],aboAndDoc[1]);
				//PENSER à renvoyer réponse au client !!!!
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String reserver(int numAbo, int numDoc)
	{
		return "Document réservé avec succés !";
	}

}
