package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import stockage.ListeAbonne;
import stockage.ListeDocument;

public abstract class AbstractService implements Service, Runnable{

	private Socket clientSocket=null;
	private String clientName;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
	protected ListeDocument listeDocument = ListeDocument.getInstance();
	protected ListeAbonne listeAbonne = ListeAbonne.getInstance();

	AbstractService(){}

	public void setSocket(Socket socket)
	{
		this.clientSocket=socket;
		this.clientName=socket.getInetAddress().toString();
	}

	public void launchService() {
		new Thread(this).start();
	}

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
		System.out.println("Client disconnected : "+this.clientName);
		this.clientSocket.close();
	}

	protected void sendRequest(String request)
	{
		socketOut.println(request);
	}

	protected String receiveRequest() throws IOException
	{
		String request = this.socketIn.readLine();

		if(request.equalsIgnoreCase("exit"))
			this.closeFlow();

		return request;
	}

	protected String logAndDoc(String message) throws IOException
	{
		this.sendRequest(message);
		String request = this.receiveRequest().trim();
		String error="error";

		if(request.equalsIgnoreCase("exit")) //Arrêt du service
		{
			//Fermeture des flux
			this.closeFlow();
			return request.toLowerCase();
		}

		String[] subInput =request.split(";");

		if(subInput.length>2)
		{
			this.sendRequest("Trop d'arguments !"); //Erreur du client
			return error; //Demande de nouvelle valeur
		}
		else if(subInput.length<2)
		{
			this.sendRequest("Pas assez d'arguments !"); //Erreur du client
			return error; //Demande de nouvelle valeur
		}

		String abo = subInput[0];
		String doc = subInput[1];

		if(!(abo.chars().allMatch(Character::isDigit)) || !(doc.chars().allMatch(Character::isDigit)))
		{
			this.sendRequest("Mauvais format !"); //Erreur du client
			return error; //Demande de nouvelle valeur
		}

		int numAbo = Integer.parseInt(abo);
		int numDoc = Integer.parseInt(doc);
		
		if(numAbo<=0 || numDoc<=0)
		{
			this.sendRequest("Mauvais nombre !"); //Erreur du client
			return error; //Demande de nouvelle valeur
		}

		return request;
	}

	//Retourne un tableau de int avec à l'index 0 le numéro Abonné et à l'index 1 le numéro document
	protected int[] parseLogAndDoc(String s)
	{
		String subInput[] = s.split(";");
		return new int[] {Integer.parseInt(subInput[0]),Integer.parseInt(subInput[1])};
	}
}
