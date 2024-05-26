package service;

public class ServiceReservation extends AbstractService {

	@Override
	public void run() {
		try {
			String request;

			while(true) 
			{
				super.sendRequest("Saisissez le numéro du document à réserver : ");
				request = super.receiveRequest();

				if(request.equalsIgnoreCase("exit"))
				{
					//Fermeture des flux
					super.closeFlow();
					return;
				}

				int numberAsked=Integer.parseInt(request); //Récupérer un nombre
				request = "Pong"; //Ce qu'on veut renvoyer comme donnée

				super.sendRequest(request); //Envoi de la donnée
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
