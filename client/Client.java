package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread {

	private Socket socket;
	private String invite;
	private Scanner scanner; 

	public Client()
	{
		this.scanner = new Scanner(System.in);
		this.start();
	}

	public void run()
	{
		try {

			this.connect();

			PrintWriter socketOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String serverResponse = "Réponse du serveur "+this.socket.getPort()+ " > ";

			System.out.println(serverResponse+ socketIn.readLine()); //Écoute d'un premier message de bienvenue du serveur

			while(true) {
				System.out.println(serverResponse + socketIn.readLine());

				System.out.print(this.invite);
				String request = scanner.nextLine();

				if(request.equalsIgnoreCase("exit"))
				{
					this.exitConnection(socketOut, socketIn);
					break;
				}

				else if(!(request.chars().allMatch(Character::isDigit))) //Spécifité inscription cours
				{
					this.exitConnection(socketOut, socketIn);
					throw new Exception("Arguments invalides !");
				}

				socketOut.println(request); //Envoyer des données
				
				System.out.println(serverResponse + socketIn.readLine()); //Écoute réponse de la requête
			}

		} catch (Exception e) { //Permet de voir les exceptions levées dans connect()
			e.printStackTrace();
		}
	}

	private void connect() throws Exception
	{
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

		this.invite= protocol+":"+host+":"+port+" > ";
	}

	private void exitConnection(PrintWriter socketOut,BufferedReader socketIn)
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
