package server;

import service.*;

public class GeneralServer extends Thread {
	
	public GeneralServer()
	{
		this.start();
	}

	public void run()
	{
		Server reservation = new Server(3000, ServiceReservation.class);
		Server emprunt = new Server(4000, ServiceEmprunt.class);
		Server retour = new Server(5000, ServiceRetour.class);
	}
}
