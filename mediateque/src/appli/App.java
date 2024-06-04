package appli;

import java.util.Scanner;

import server.GeneralServer;
import stockage.ListeAbonne;
import stockage.ListeDocument;
import stockage.LogDocument;

public class App {

	public static void main(String[] args) throws Exception {
		GeneralServer services = new GeneralServer(); //Démarrage des serveurs
		
		Scanner scanner = new Scanner(System.in);

		while(true)
		{
			String response = scanner.nextLine().trim();

			if(response.equalsIgnoreCase("log"))
				LogDocument.getInstance().seeLog();
			else if(response.equalsIgnoreCase("shutdown"))
			{
				ListeAbonne.getInstance().updateData();
				ListeDocument.getInstance().updateData();
				Thread.sleep(3000);
				System.out.println("Vous pouvez éteindre le serveur");
				break;
			}
			else if(response.equalsIgnoreCase("help"))
			{
				System.out.println("log	 Pour voir les logs");
				System.out.println("shutdown Pour arrêter le serveur");
				System.out.println("help	 Pour voir les commandes possible");
			}
			else
				System.out.println("Tapez help pour plus d'informations");
		}
		return;
	}
}