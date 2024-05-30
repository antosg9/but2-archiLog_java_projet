package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import service.Service;

public class Server extends Thread {

	private int numPort;
	private Class<? extends Service> service;
	private List<String> logs;
	private boolean running;

	public Server(int numPort, Class<? extends Service> service)
	{
		this.numPort=numPort;
		this.service=service;
		this.logs=Collections.synchronizedList(new LinkedList<String>());
		running=true;
		this.start();
	}

	public void run()
	{
		try {
			ServerSocket serverSocket = new ServerSocket(this.numPort);

			while(this.running) //trouver une condition d'arrêt du serveur
			{
				Socket clientSocket = serverSocket.accept();
				logs.add(this.getTime()+" : New client connected : "+ clientSocket.getInetAddress());
				Service s = this.service.newInstance();
				s.setSocket(clientSocket);
				s.launchService();
			}

			//Lorsque la condition d'arrêt sera trouvée
			serverSocket.close();
			System.out.println("Server closed");

		} catch (IOException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}


	public void closeServer()
	{
		this.running=false;
	}

	public LinkedList<String> getLogs()
	{
		return new LinkedList<String>(this.logs);
	}

	protected String getTime()
	{
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

	}
}