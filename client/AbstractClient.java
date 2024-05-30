package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public abstract class AbstractClient implements Runnable{
	
	private Socket socket;
	private String inviteClient;
	private String inviteServeur;
	
	protected void connect() throws Exception
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("BTTP:host:port > ");
		String[] subInput =(scanner.nextLine().trim()).split(":");

		if(subInput.length>3)
			throw new Exception ("Trop d'arguments");
		else if(subInput.length<3)
			throw new Exception ("Pas assez d'arguments");

		String protocol = subInput[0].toUpperCase();
		String host = subInput[1];
		String port = subInput[2];

		if(!protocol.equals("BTTP"))
			throw new Exception("Protocole incorrect");

		else if(!(port.chars().allMatch(Character::isDigit)) || !(port.length()==4))
			throw new Exception("Port invalide");

		int numPort = Integer.parseInt(port);

		try {
			this.socket = new Socket(host,numPort);
		}catch(UnknownHostException e){
			throw new Exception("java.net.UnknownHostException: "+host);
		}

		this.inviteServeur="Réponse du serveur "+this.socket.getPort()+ " > ";
		this.inviteClient= protocol+":"+host+":"+port+" > ";
	}
	
	protected String getInviteServeur()
	{
		return this.inviteServeur;
	}
	
	protected String getInviteClient()
	{
		return this.inviteClient;
	}
	
	protected Socket getSocket()
	{
		return this.socket;
	}

	protected void exitConnection(PrintWriter socketOut,BufferedReader socketIn)
	{
		socketOut.println("exit");

		try {
			System.out.println(socketIn.readLine());

			//Fermeture des flux
			socketOut.close();
			socketIn.close();
			this.socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
