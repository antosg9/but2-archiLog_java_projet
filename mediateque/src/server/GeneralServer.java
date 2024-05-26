package server;

import service.Service_Lambda;

public class GeneralServer extends Thread {
	
	public GeneralServer()
	{
		this.start();
	}

	public void run()
	{
		Server reservations = new Server(3000, Service_Lambda.class);
		Server emprunts = new Server(4000, Service_Lambda.class);
		Server retours = new Server(5000, Service_Lambda.class);
	}
}
