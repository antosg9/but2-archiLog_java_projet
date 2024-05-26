package service;

public class ServiceEmprunt extends AbstractService {

	@Override
	public void run() {
		try {
			String request;

			while(true) 
			{
				super.sendRequest("Combien de places souhaitez vous réserver ?");
				request = super.receiveRequest();

				if(request.equalsIgnoreCase("exit"))
				{
					//Fermeture des flux
					super.closeFlow();
					return;
				}

				//int numberAsked=Integer.parseInt(request); //Récupérer un nombre
				request = "Pong"; //Ce qu'on veut renvoyer comme donnée

				super.sendRequest(request); //Envoi de la donnée
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
