package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client extends AbstractClient {

	public Client()
	{
		new Thread(this).start();
	}

	@Override
	public void run()
	{
		try {

			super.connect();
			
			Scanner scanner = new Scanner(System.in);
			String inviteServeur = super.getInviteServeur();
			String inviteClient = super.getInviteClient();
			
			PrintWriter socketOut = new PrintWriter(new OutputStreamWriter(this.getSocket().getOutputStream()), true);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(this.getSocket().getInputStream()));
			

			System.out.println(inviteServeur+ socketIn.readLine()); //Écoute d'un premier message de bienvenue du serveur

			while(true) {
				String serverResponse = socketIn.readLine();
				System.out.println(inviteServeur + serverResponse);

				System.out.print(inviteClient);
				String request = scanner.nextLine();

				if(request.equalsIgnoreCase("exit"))
				{
					this.exitConnection(socketOut, socketIn);
					scanner.close();
					break;
				}

				socketOut.println(request); //Envoyer des données
				serverResponse = socketIn.readLine();

				System.out.println(inviteServeur + serverResponse); //Écoute réponse de la requête
			}

		} catch (Exception e) { //Permet de voir les exceptions levées dans connect()
			e.printStackTrace();
		}
	}


}
