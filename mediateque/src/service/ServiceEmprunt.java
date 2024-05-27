package service;

public class ServiceEmprunt extends AbstractService {

	@Override
	public void run() {
		try {
			super.openFlow();
			String login = "EMPRUNT - Veuillez saisir \"numéro_abonné ; numéro_document\"";

			while(true)
			{
				String response = logAndDoc(login);
				if(response.equalsIgnoreCase("exit")) //Ârrêt du service
					return;
				else if(response.equalsIgnoreCase("error")) //Format invalide, on recommence
					continue;
				
				int[] aboAndDoc = super.parseLogAndDoc(response);
				
				this.emprunter(aboAndDoc[0],aboAndDoc[1]);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String emprunter(int num, int numAbo)
	{
		return "Document emprunté avec succés !";
	}

}
