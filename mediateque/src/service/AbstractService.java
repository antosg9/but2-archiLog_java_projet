package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class AbstractService implements Service, Runnable{

	private Socket clientSocket=null;
	private String clientName;
	private BufferedReader socketIn;
	private PrintWriter socketOut;

	AbstractService(){}

	protected void openFlow() throws Exception
	{
		if(clientSocket==null)
			throw new Exception ("Client non connecté !");

		this.socketIn = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream())); //Flux récepteur
		this.socketOut = new PrintWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()), true); //Flux envoyeur
		String welcomeMessage = "Connexion établie ! Bienvenue client n°"+this.clientName+" !";
		socketOut.println(welcomeMessage);
	}

	protected void closeFlow() throws IOException 
	{
		this.socketOut.println("Arrêt du service");
		this.socketIn.close();		
		this.socketOut.close();
		System.out.println("Client disconenct : "+this.clientName);
		this.clientSocket.close();
	}

	protected void sendRequest(String request)
	{
		socketOut.println(request);
	}

	protected String receiveRequest() throws IOException
	{
		return this.socketIn.readLine();
	}

	public void setSocket(Socket socket)
	{
		this.clientSocket=socket;
		this.clientName=socket.getInetAddress().toString();
	}

	public void launchService() {
		new Thread(this).start();
	}

}
